package algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaoming on 29/01/2019.
 */
public class Backtracking {
    private int maxW = Integer.MIN_VALUE; // 存储背包中物品总重量的最大值
    // 下标表示物品序号，值表示是否放进背包:1放，0不放
    private int[] currentAnswer;
    //存储所有解(map key表示放进去的重量，value表示对应重量的物品放法)，
    //最终所有最优解为bestAnswerMap.get(maxW)
    private Map<Integer, List<int[]>> bestAnswerMap = new HashMap();

    // cw 表示当前已经装进去的物品的重量和；i 表示考察到哪个物品了；
    // w 背包重量；items 表示每个物品的重量；n 表示物品个数
    // 假设背包可承受重量 100，物品个数 10，物品重量存储在数组 a 中，那可以这样调用函数：
    // f(0, 0, a, 10, 100)
    public void f(int i, int cw, int[] items, int n, int w) {
        if(currentAnswer == null){
            currentAnswer = new int[n];
        }

        if (cw == w || i == n) { // cw==w 表示装满了 ;i==n 表示已经考察完所有的物品
            if (cw >= maxW) {
                maxW = cw;
                int[] bestAnswer = new int[currentAnswer.length];
                for(int j=0; j<currentAnswer.length; j++){
                    bestAnswer[j] = currentAnswer[j];
                }
                if(bestAnswerMap.containsKey(cw)){
                    bestAnswerMap.get(cw).add(bestAnswer);
                }else{
                    List<int[]> list = new ArrayList<int[]>();
                    list.add(bestAnswer);
                    bestAnswerMap.put(cw, list);
                }
            }
            return;
        }

        currentAnswer[i] = 0;
        f(i+1, cw, items, n, w);
        if (cw + items[i] <= w) {// 已经超过可以背包承受的重量的时候，就不要再装了
            currentAnswer[i] = 1;
            f(i+1,cw + items[i], items, n, w);
        }
    }

    //全局或成员变量, 下标表示行, 值表示 queen 存储在哪
    private int[] result = new int[8];

    public void cal8queens(int row){
        if(row == 8){
            printResult();
            return;
        }

        for(int column=0; column<8; column++){
            if(isOk(row, column)){
                result[row] = column;
                cal8queens(row+1);
            }
        }
    }

    private boolean isOk(int row, int column) {
        int leftup = column-1;
        int rightup = column+1;

        // 逐行往上考察每一行
        for(row-- ;row>=0; row--){
            //上一行相同列有queue
            if(result[row] == column){
                return false;
            }
            //左斜上对角有queue
            if(leftup >=0 && result[row] == leftup){
                return false;
            }

            //右斜上对角有queue
            if(rightup <8 && result[row] == rightup){
                return false;
            }

            leftup--;
            rightup++;
        }

        return true;
    }

    private void printResult() {
        for(int row=0; row<8; row++){
            for(int column=0; column<8; column++){
                if(result[row] == column){
                    System.out.print("Q ");
                }else{
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println("--------------");
    }

    public static void main(String[] args) {
        Backtracking backtracking = new Backtracking();
        int[] items = new int[]{20,10,5,8,9,31,30,13,16,19};
        backtracking.f(0, 0, items, 10, 100);
        for(int[] answer : backtracking.bestAnswerMap.get(backtracking.maxW)){
            for(int i=0; i<answer.length; i++){
                System.out.print(answer[i] + " ");
            }
            System.out.println();
        }

//        backtracking.cal8queens(0);
    }
}


class Pattern {
    private boolean matched = false;
    private char[] pattern; // 正则表达式
    private int plen; // 正则表达式长度

    public Pattern(char[] pattern, int plen) {
        this.pattern = pattern;
        this.plen = plen;
    }

    public boolean match(char[] text, int tlen) { // 文本串及长度
        matched = false;
        rmatch(0, 0, text, tlen);
        return matched;
    }

    /**
     * pattern完全匹配文本串
     * “*”匹配任意多个（大于等于 0 个）任意字符，“?”匹配零个或者一个任意字符
     * 注意上面规则和常规正则表达式*,?匹配前面一个字符不一样
     * @param ti
     * @param pj
     * @param text
     * @param tlen
     */
    private void rmatch(int ti, int pj, char[] text, int tlen) {
        if (matched) return; // 如果已经匹配了，就不要继续递归了
        if (pj == plen) { // 正则表达式到结尾了
            if (ti == tlen) matched = true; // 文本串也到结尾了
            return;
        }
        if (pattern[pj] == '*') { // * 匹配任意个字符
            for (int k = 0; k <= tlen-ti; ++k) {
                rmatch(ti+k, pj+1, text, tlen);
            }
        } else if (pattern[pj] == '?') { // ? 匹配 0 个或者 1 个字符
            rmatch(ti, pj+1, text, tlen);
            rmatch(ti+1, pj+1, text, tlen);
        } else if (ti < tlen && pattern[pj] == text[ti]) { // 纯字符匹配才行
            rmatch(ti+1, pj+1, text, tlen);
        }
    }

    public static void main(String[] args) {
        Pattern pattern = new Pattern("ab*c".toCharArray(), 4);
        System.out.println(pattern.match("abcd".toCharArray(), 4));
    }
}

