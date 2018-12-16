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

    public static void mergeSort(int a[]){
        mergeSort(a, 0, a.length-1);
    }

    private static void mergeSort(int[] a, int p, int q) {
        if(p>=q){
            return;
        }
        int mid = (q-p)/2 + p;
        mergeSort(a, p, mid);
        mergeSort(a, mid+1, q);
        merge(a, p, mid, q);
    }

    private static void merge(int[] a, int p, int mid, int q) {
        int[] result = new int[q-p+1];
        int i=p, j=mid+1, k=0;
        while (i<=mid && j<=q){
            if(a[i] < a[j]){
                result[k++] = a[i++];
            }else{
                result[k++] = a[j++];
            }
        }

        if(i>mid){
            while (j<=q){
                result[k++] = a[j++];
            }
        }

        if(j>q){
            while (i<=mid){
                result[k++] = a[i++];
            }
        }

        for(int l=0; l<result.length; l++){
            a[p++] = result[l];
        }
    }

    public static void quickSort(int[] a){
        quickSort(a, 0, a.length-1);
    }

    private static void quickSort(int[] a, int p, int q) {
        if(p>=q){
            return;
        }
        int mid = partition(a, p, q);
        quickSort(a, p, mid-1);
        quickSort(a, mid+1, q);
    }

    private static int partition(int[] a, int p, int q) {
        int base = a[q];
        int j=p;
        for(int i=p; i<=q; i++){
            if(a[i] <= base){
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
                j++;
            }
        }

        return j-1;
    }

    //o(n)复杂度找到第k大元素
    public static int nMax(int[] a, int k){
        if(k>a.length || k<=0){
            //0代表不存在
            return 0;
        }

        int index = nMax(a, k, 0, a.length-1);
        return a[index];
    }

    private static int nMax(int[] a, int k, int p, int q) {
        int mid = partitionNmax(a, p, q);
        if(mid + 1 == k){
            return mid;
        }else if(mid + 1 < k){
            return nMax(a, k, mid+1, q);
        }else{
            return nMax(a, k, p, mid-1);
        }
    }

    private static int partitionNmax(int[] a, int p, int q) {
        int i=p, base = a[q];

        for(int j=p; j<=q; j++){
            if(a[j] >= base){
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
                i++;
            }
        }
        return i-1;
    }

    public static void printAll(int a[]){
        for(int i=0; i<a.length; i++){
            System.out.println(a[i]);
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{3,4,7,8,6,5,6};
        System.out.println(Sorts.nMax(a, 4));
    }
}
