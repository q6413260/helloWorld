package algo;

/**动态规划
 * Created by xiaoming on 30/01/2019.
 */
public class DynamicProgramming {
    /**
     * 动态规划解决0-1背包
     * @param weight 物品重量
     * @param limit 背包最大容量
     * @return 背包装进去物品最大重量
     */
    public int knapsack(int[] weight, int limit){
        int n = weight.length;
        boolean[][] result = new boolean[n][limit+1];

        //先考察第一个物品
        result[0][0] = true; //第一个物品放进去
        result[0][weight[0]] = true; //第一个物品不放进去

        //接着考察剩下物品
        for(int i=1; i<n; i++){
            //当前考察物品不放进去
            for(int j=0; j<= limit; j++){
                if(result[i-1][j]){
                    result[i][j] = true;
                }
            }
            //当前考察物品放进去
            for(int j=0; j+weight[i] <= limit; j++){
                if(result[i-1][j]){
                    result[i][j+weight[i]] = true;
                }
            }
        }

        for(int sum=limit; sum>=0; sum--){
            if(result[n-1][sum]){
                return sum;
            }
        }

        return 0;
    }

    /**
     * knapsack方法额外使用了n*(limit+1)的二维数组，
     * knapsack2方法只需要使用limit+1的一维数组
     * @param weight
     * @param limit
     * @return
     */
    public int knapsack2(int[] weight, int limit){
        int n= weight.length;
        boolean[] result = new boolean[limit+1];
        result[0] = true;
        result[weight[0]] = true;

        for(int i=1; i<n; i++){
            //此时必须从大到小遍历，负责会重复计算
            for(int j=limit-weight[i]; j>=0; j--){
                if(result[j]){
                    result[j+weight[i]] = true;
                }
            }
        }

        for(int sum=limit; sum>=0; sum--){
            if(result[sum]){
                return sum;
            }
        }

        return 0;
    }

    /**
     *
     * @param weight 物品重量
     * @param value  物品价值
     * @param limit  背包最大容量
     * @return
     */
    public int knapsack3(int[] weight, int[] value, int limit){
        int n=weight.length;
        int[][] result = new int[n][limit+1];
        for(int i=0; i<n; i++){
            for(int j=0; j<=limit; j++){
                result[i][j] = -1;
            }
        }


        result[0][0] = 0;
        result[0][weight[0]] = value[0];

        for(int i=1; i<n; i++){
            //不装
            for(int j=0; j<=limit; j++){
                if(result[i-1][j] > -1){
                    result[i][j] = result[i-1][j];
                }
            }

            //装
            for(int j=0; j+weight[i]<=limit; j++){
                if(result[i-1][j] > -1){
                    int v = result[i-1][j] + value[i];
                    if(v > result[i][j+weight[i]]){
                        result[i][j+weight[i]] = v;
                    }
                }
            }
        }

        int maxValue = 0;
        for(int j=0; j<=limit; j++){
            maxValue = Math.max(result[n-1][j], maxValue);
        }

        return maxValue;
    }

    public static void main(String[] args) {
        int[] weight = {2,2,4,6,3};
        int[] value = {3,4,8,9,6};
        DynamicProgramming dynamicProgramming = new DynamicProgramming();
        System.out.println(dynamicProgramming.knapsack(weight, 9));
        System.out.println(dynamicProgramming.knapsack2(weight, 9));
        System.out.println(dynamicProgramming.knapsack3(weight, value, 9));
    }
}
