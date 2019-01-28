package algo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yeming on 2019/1/25.
 */
public class StringMatcher {
    public int bmSearch(char[] source, int sourceCount, char[] target, int targetCount){
        if(sourceCount<targetCount){
            return -1;
        }

        int[] bc = generateBC(target, targetCount);
        int[] suffix = new int[targetCount];
        boolean[] prefix = new boolean[targetCount];
        generateGS(target, targetCount, suffix, prefix);

        int i=0;
        while (i<=sourceCount-targetCount){
            int j=targetCount-1;
            for(;j>=0;j--){
                if(target[j] != source[i+j]){
                    break;
                }
            }
            if(j == -1){
                return i;
            }

            int rightMoveByBC = j-bc[(int)source[i+j]];
            int rightMoveByGS = getRightMoveByGS(j, targetCount, suffix, prefix);
            i = i + Math.max(rightMoveByBC, rightMoveByGS);
        }

        return -1;
    }

    private int getRightMoveByGS(int j, int targetCount, int[] suffix, boolean[] prefix) {
        int goodSuffixCount = targetCount-j-1;
        if(goodSuffixCount <= 0){
            return 0;
        }

        if(suffix[goodSuffixCount] != -1){
            return j-suffix[goodSuffixCount]+1;
        }

        for(int r=j+2;r<=targetCount-1; r++){
            int childSuffixCount = targetCount-r;
            if(prefix[childSuffixCount]){
                return targetCount-childSuffixCount;
            }
        }

        return targetCount;
    }

    private void generateGS(char[] target, int targetCount, int[] suffix, boolean[] prefix) {
        for(int i=0; i<targetCount; i++){
            suffix[i] = -1;
            prefix[i] = false;
        }

        for(int i=0; i<targetCount-1; i++){
            int suffixCount = 0;

            int j=i;
            for(;target[j]==target[targetCount-suffixCount-1] && j>=0;j--){
                suffix[++suffixCount] = j;
            }

            if(j==-1){
                prefix[suffixCount] = true;
            }
        }
    }

    private int[] generateBC(char[] target, int targetCount) {
        int[] bc = new int[256];
        for(int i=0; i<bc.length; i++){
            bc[i] = -1;
        }

        for(int i=0; i<targetCount; i++){
            bc[(int)target[i]] = i;
        }
        return bc;
    }

    public int bfSearch(char[] source, char[] target){
        int targetCount = target.length;
        int sourceCount = source.length;

        for(int i=0; i<= sourceCount-targetCount; i++){
            int j= 0;
            for(; j<targetCount; j++){
                if(target[j] != source[i+j]){
                    break;
                }
            }
            if(j == targetCount){
                return i;
            }
        }

        return -1;
    }

    public int kmpSearch(char[] source, char[] target){
        int sourceCount = source.length;
        int targetCount = target.length;
        int[] next = generateNext(target, targetCount);
        int j=0;

        for(int i=0; i<sourceCount; i++){
            while (j>=1 && source[i] != target[j]){
                j = next[j-1] + 1;
            }

            if(source[i] == target[j]){
                j++;
            }

            if(j == targetCount){
                return i-j+1;
            }
        }
        return -1;
    }

    private int[] generateNext(char[] target, int targetCount) {
        int[] next = new int[targetCount];
        next[0] = -1;
        int k=-1;

        for(int i=1; i<targetCount; i++){
            while (k!=-1 && target[k+1] != target[i]){
                k = next[k];
            }

            if(target[k+1] == target[i]){
                k++;
            }

            next[i] = k;
        }

        return next;
    }

    public static void main(String[] args) {
        StringMatcher stringMatcher = new StringMatcher();
        char[] source = new char[]{'c','a','c','c','a','b'};
        char[] target = new char[]{'c','a','a'};
//        System.out.println(stringMatcher.bmSearch(source, 6, target, 3));
        System.out.println(stringMatcher.kmpSearch(source, target));
    }
}

class Trie{
    private AcNode root = new AcNode('/');

    public void insert(char[] chars){
        AcNode p = root;

        for(int i=0; i<chars.length; i++){
            int idx = chars[i]- 'a';
            AcNode child = p.children[idx];

            if(child == null){
                AcNode newNode = new AcNode(chars[i]);
                p.children[idx] = newNode;
            }

            p = p.children[idx];
        }

        p.length = chars.length;
        p.isEnding = true;
    }

    public boolean find(char[] target){
        AcNode p = root;

        for(int i=0; i<target.length; i++){
            int idx = target[i] - 'a';
            AcNode child = p.children[idx];

            if(child == null){
                return false;
            }

            p = p.children[idx];
        }

        return p.isEnding;
    }

    public void buildFailurePointer(){
        root.failNode = null;
        Queue<AcNode> queue = new LinkedList<AcNode>();
        queue.add(root);

        while (queue.size() !=0){
            AcNode p = queue.poll();
            AcNode[] children = p.children;
            for(int i=0; i<children.length; i++){
                AcNode child = children[i];

                if(child == null){
                    continue;
                }

                if(p == root){
                    child.failNode = root;
                }else{
                    AcNode failNode = p.failNode;
                    int idx = child.data - 'a';
                    while (failNode != null){
                        if(failNode.children[idx] != null){
                            child.failNode = failNode.children[idx];
                            break;
                        }
                        failNode = failNode.failNode;
                    }

                    if(failNode == null){
                        child.failNode = root;
                    }
                }

                queue.add(child);
            }

        }
    }

    /**
     * 利用AC自动机匹配字符串
     * @param text
     */
    public void match(char[] text) { // text 是主串
        int n = text.length;
        AcNode p = root;
        for (int i = 0; i < n; ++i) {
            int idx = text[i] - 'a';
            while (p.children[idx] == null && p != root) {
                p = p.failNode; // 失败指针发挥作用的地方
            }
            p = p.children[idx];
            if (p == null) p = root; // 如果没有匹配的，从 root 开始重新匹配
            AcNode tmp = p;
            while (tmp != root) { // 打印出可以匹配的模式串
                if (tmp.isEnding) {
                    int pos = i-tmp.length+1;
                    System.out.println(" 匹配起始下标 " + pos + "; 长度 " + tmp.length);
                }
                tmp = tmp.failNode;
            }
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("abcd".toCharArray());
        trie.insert("bc".toCharArray());
        trie.insert("bcd".toCharArray());
        trie.insert("c".toCharArray());
        trie.buildFailurePointer();
        trie.match("abcd".toCharArray());
    }

    class AcNode{
        private char data;
        private AcNode[] children = new AcNode[26];
        private boolean isEnding = false;
        private int length;
        private AcNode failNode;

        AcNode(char data){
            this.data = data;
        }
    }
}

