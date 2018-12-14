package algo;

/**冒泡、插入、选择
 * Created by yeming on 2018/12/10.
 */
public class Sorts {
    public static void bubbleSort(int a[]){
        for(int i=1; i< a.length; i++){
            boolean flag = false;
            for(int j=0; j<a.length-i; j++){
                if(a[j] > a[j+1]){
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                    flag = true;
                }
            }
            if(!flag){
                break;
            }
        }
    }

    public static void insertSort(int a[]){
        for(int i=1; i<a.length; i++){
            int val = a[i];

            //找到要插入的位置
            int j = i-1;
            for(; j>=0; j--){
                if(a[j] > val){
                    a[j+1] = a[j];
                }else{
                    break;
                }
            }
            a[j+1] = val;
        }
    }

    public static void selectSort(int a[]){
        for(int i=0; i< a.length-1; i++){
            int val = a[i];
            int minIndex = i;
            int min = val;
            for(int j=i+1; j<a.length; j++){
                if(a[j] < min){
                    min = a[j];
                    minIndex = j;
                }
            }
            if(min != a[i]){
                a[i] = min;
                a[minIndex] = val;
            }
        }
    }

    public static void printAll(int a[]){
        for(int i=0; i<a.length; i++){
            System.out.println(a[i]);
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{4,3,6,2,1};
        Sorts.selectSort(a);
        Sorts.printAll(a);
    }
}
