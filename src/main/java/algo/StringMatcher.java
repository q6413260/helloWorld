package algo;

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

            int rightMove = j-bc[(int)source[i+j]];
            int goodSufficCount = targetCount-j-1;


        }

        return -1;
    }

    private void generateGS(char[] target, int targetCount, int[] suffix, boolean[] prefix) {
        for(int i=0; i<targetCount; i++){
            suffix[i] = -1;
            prefix[i] = false;
        }

        for(int i=0; i<targetCount; i++){
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
}
