package algo;

/**
 * Created by xiaoming on 29/01/2019.
 */
public class Backtracking {
    private int maxW = Integer.MIN_VALUE; // 存储背包中物品总重量的最大值
    // cw 表示当前已经装进去的物品的重量和；i 表示考察到哪个物品了；
    // w 背包重量；items 表示每个物品的重量；n 表示物品个数
    // 假设背包可承受重量 100，物品个数 10，物品重量存储在数组 a 中，那可以这样调用函数：
    // f(0, 0, a, 10, 100)
    private int[] bestAnswer;// 解

    public void f(int i, int cw, int[] items, int n, int w) {
        if(bestAnswer == null){
            bestAnswer = new int[n];
        }

        if (cw == w || i == n) { // cw==w 表示装满了 ;i==n 表示已经考察完所有的物品
            if (cw > maxW) maxW = cw;
            return;
        }
        bestAnswer[i] = 0;
        f(i+1, cw, items, n, w);
        if (cw + items[i] <= w) {// 已经超过可以背包承受的重量的时候，就不要再装了
            bestAnswer[i] = 1;
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
        System.out.println(backtracking.bestAnswer);
        int sum = 0;
        for(int i=0; i<backtracking.bestAnswer.length; i++){
            if(backtracking.bestAnswer[i] == 1){
                sum += items[i];
                System.out.println("装进去的物品序号:" + i);
            }
        }

        System.out.println("总重量:" + sum);

        backtracking.cal8queens(0);
    }
}
