package algo;

/**分治算法
 * Created by yeming on 2019/1/29.
 */
public class DivideAndConquer {
    private int inversionNum;

    /**
     * 利用分治思想求数组的逆序数
     * @param data
     * @return
     */
    public int countInversionNum(int[] data){
        inversionNum = 0;
        int length = data.length;
        mergeAndSort(data, 0, length-1);
        return inversionNum;
    }

    private void mergeAndSort(int[] data, int p, int q) {
        if(p>=q){
            return;
        }

        int mid = (q-p)/2 + p;
        mergeAndSort(data, p, mid);
        mergeAndSort(data, mid+1, q);
        merge(data, p, mid, q);
    }

    private void merge(int[] data, int p, int mid, int q) {
        int[] tmp = new int[q-p+1];

        int i=p, j=mid+1;
        int k=0;
        while (i<=mid && j<=q){
            if(data[i] <= data[j]){
                tmp[k++] = data[i++];
            }else{
                tmp[k++] = data[j++];
                inversionNum += mid-i+1;
            }
        }

        while (i<=mid){
            tmp[k++] = data[i++];
        }

        while (j<=q){
            tmp[k++] = data[j++];
        }

        for(int l=0; l<tmp.length; l++){
            data[p+l] = tmp[l];
        }
    }

    public static void main(String[] args) {
        DivideAndConquer divideAndConquer = new DivideAndConquer();
        System.out.println(divideAndConquer.countInversionNum(new int[]{1,5,6,2,3,4}));
    }
}
