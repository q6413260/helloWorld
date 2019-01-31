package algo;

/**动态规划
 * Created by xiaoming on 30/01/2019.
 */
public class DynamicProgramming {
    /**
     * 动态规划解决0-1背包，并打印一种最优放法
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

        int max=0;

        for(int sum=limit; sum>=0; sum--){
            if(result[n-1][sum]){
                max = sum;
                break;
            }
        }

        int tmp = max;

        for(int i=n-1; i>=0; i--){
            if(tmp-weight[i]>=0 && result[i-1][tmp-weight[i]]){
                tmp = tmp-weight[i];
                System.out.println(String.format("装序号为%s的物品",i));
            }
        }

//        for(int i=n-1; i>=1; i--){
//            if(tmp-weight[i]>=0 && result[i-1][tmp]){
//                if(i==1){
//                    System.out.println(String.format("装序号为%s的物品",i-1));
//                }
//            }else{
//                tmp = tmp-weight[i];
//                System.out.println(String.format("装序号为%s的物品",i));
//            }
//        }

        return max;
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

    /**       5
     *       7 8
     *      2 3 4
     *     4 9 6 1
     *    2 7 9 4 5
     * 求杨辉三角最高层到最底层最短路径
     * @param data
     * @return
     */
    public int minDis(int[][] data){
        int rowCount = data.length;
        int columnCount = data[rowCount-1].length;
        int [][] status = new int[rowCount][columnCount];
        status[0][0] = 5;

        for(int i=1; i<rowCount; i++){
            int currentColumnCount = data[i].length;
            for(int j=0; j<currentColumnCount; j++){
                if(j==0){
                    status[i][j] = data[i][j] + status[i-1][j];
                }else if(j==i){
                    status[i][j] = data[i][j] + status[i-1][j-1];
                }else{
                    status[i][j] = data[i][j] + Math.min(status[i-1][j-1], status[i-1][j]);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i=0; i<columnCount; i++){
            min = Math.min(min, status[rowCount-1][i]);
        }

        return min;
    }

    /**
     * 假设value按照从小到大排列
     * 利用递推状态转移方程加上备忘录
     * @param w
     * @param value
     * @return
     */
    private int mem[];
    public int countSmallChange(int w, int[] value){
        if(mem == null){
            int length = Math.max(w, value[value.length-1]);
            mem = new int[length+1];
            for(int i=0; i<value.length; i++){
                mem[value[i]] = 1;
            }
        }
        if(w<value[0]){
            return Integer.MAX_VALUE;
        }

        if(mem[w]>0){
            return mem[w];
        }

        int min = Integer.MAX_VALUE;
        for(int i=value.length-1; i>=0; i--){
            int lastMinCount = countSmallChange(w-value[i], value);
            min = Math.min(min, lastMinCount);
        }
        mem[w] = min+1;

        return min+1;
    }

    /**
     * 利用状态表求解
     * @param w
     * @param value
     * @return
     */
    public int countSmallChange2(int w, int[] value){
        int[] status = new int[w+1];
        for(int i=0 ;i<value.length; i++){
            if(value[i]<=w){
                if(value[i] == w){
                    return 1;
                }
                status[value[i]] = value[i];
            }
        }

        int count = (int)Math.ceil((double)w/(double)value[0]);

        for(int i=1; i<count; i++){
            int[] temp = new int[w+1];
            for(int entry : status){
                if(entry == 0){
                    continue;
                }
                for(int j=0; j<value.length; j++){
                    if(entry+value[j] == w){
                        return i+1;
                    }if(entry+value[j] > w){
                        break;
                    }else{
                        temp[entry+value[j]] = entry+value[j];
                    }
                }
            }
            status = temp;
        }

        return 0;
    }

    public static void main(String[] args) {
        int[] weight = {2,2,4,6,3};
        int[] value = {3,4,8,9,6};
        DynamicProgramming dynamicProgramming = new DynamicProgramming();
        System.out.println(dynamicProgramming.knapsack(weight, 9));
        System.out.println(dynamicProgramming.knapsack2(weight, 9));
        System.out.println(dynamicProgramming.knapsack3(weight, value, 9));

        int[][] yanghui = new int[][]{{5},{7,8},{2,3,4},{4,9,6,1},{2,7,9,4,5}};
        System.out.println(dynamicProgramming.minDis(yanghui));
        System.out.println(dynamicProgramming.countSmallChange2(10,new int[]{1,3,5,10}));
    }
}
