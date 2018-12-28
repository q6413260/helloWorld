package algo;

/**
 * Created by xiaoming on 23/12/2018.
 */
public class BinarySearch {
    public static int indexOf(int[] array, int data){
        int low = 0;
        int high = array.length-1;

        while (low <= high){
            int mid = (high - low)/2 + low;

            if(array[mid] == data){
                return mid;
            }else if(array[mid] < data){
                low = mid + 1;
            }else{
                high = mid -1;
            }
        }
        return -1;
    }

    public static int indexOf2(int[] array, int data){
        return indexOf2(array, 0, array.length-1, data);
    }

    private static int indexOf2(int[] array, int low, int high, int data) {
        if(low>high){
            return -1;
        }
        int mid = (high - low)/2 + low;
        if(array[mid] == data){
            return mid;
        }else if(array[mid] < data){
            return indexOf2(array, mid+1, high, data);
        }else{
            return indexOf2(array, low, mid-1, data);
        }
    }

    public static double sqrt(double a, double precision){
        double min,max;
        if(a>=0 && a<1){
            min = a;
            max = 1;
        }else if(a==1){
            return 1;
        }else{
            min = 1;
            max = a;
        }
        double mid = (max-min)/2 + min;
        while (mid * mid != a){
            if(mid * mid > a){
                if(mid * mid -a > precision){
                    max = mid;
                }else {
                    return mid;
                }
            }else{
                if(a - mid * mid > precision){
                    min = mid;
                }else{
                    return mid;
                }
            }
            mid = (max-min)/2 + min;
        }
        return mid;
    }

    public static int firstIndexOf(int[] array, int data){
        int low=0, high= array.length-1;
        int mid;

        while (low <= high){
            mid = (high-low)/2 + low;

            if(array[mid] < data){
                low = mid+1;
            }else if(array[mid] > data){
                high = mid-1;
            }else{
                if(mid==0 || array[mid-1] != data){
                    return mid;
                }else{
                    high = mid-1;
                }
            }
        }
        return -1;
    }

    public static int lastIndexOf(int[] array, int data){
        int low=0, high = array.length -1;
        int mid;

        while (low <= high){
            mid = (high-low)/2 + low;
            if(array[mid] < data){
                low = mid + 1;
            }else if(array[mid] > data){
                high = mid - 1;
            }else{
                if(mid== array.length-1 || array[mid+1] != data){
                    return mid;
                }else{
                    low = mid+1;
                }
            }
        }

        return -1;
    }

    public static int firstNoLessThan(int[] array, int data){
        int low = 0, high = array.length -1;
        int mid;

        while (low <= high){
            mid = (high-low)/2 + low;
            if(array[mid] < data){
                low = mid+1;
            }else{
                if(mid==0 || array[mid-1] < data){
                    return mid;
                }else{
                    high = mid-1;
                }
            }
        }
        return -1;
    }

    public static int lastNoMoreThan(int[] array, int data){
        int low =0, high = array.length -1;
        int mid;

        while (low <= high){
            mid = (high-low)/2 + low;
            if(array[mid] > data){
                high = mid-1;
            }else{
                if(mid==array.length-1 || array[mid+1] > data){
                    return mid;
                }else{
                    low = mid+1;
                }
            }
        }

        return -1;
    }

    /**o(n)时间复杂度
     * 先找到循环数组的分界点，将数组分成两个有序数组，分别二分查找
     * @param array
     * @param data
     * @return
     */
    public static int loopBinarySearch(int[] array, int data){
        int pivot = 0;
        for(int i=0; i<array.length-1; i++){
            if(array[i] > array[i+1]){
                pivot = i;
                break;
            }
        }

        int index = BinarySearch.indexOf2(array, 0, pivot, data);
        if(index == -1){
            index = BinarySearch.indexOf2(array, pivot, array.length-1, data);
        }

        return index;
    }

    /**o(n)时间复杂度
     * 先找到分界点，然后数组所有元素索引加上index(超过数据长度取模)，变成有序数组，
     * 二分查找找到元素data，然后索引减去index
     * @param array
     * @param data
     * @return
     */
    public static int loopBinarySearch2(int[] array, int data){
        int pivot = 0;
        for(int i=0; i<array.length-1; i++){
            if(array[i] > array[i+1]){
                pivot = i;
                break;
            }
        }

        int step = array.length-1-pivot;
        int[] newArray = new int[array.length];
        for(int i=0; i<array.length; i++){
            newArray[(i+step)%array.length] = array[i];
        }

        int index = BinarySearch.indexOf(newArray, data);
        if(index != -1){
            if(index >= step){
                return index-step;
            }else{
                return index+array.length-step;
            }
        }
        return index;
    }

    public static int loopBinarySearch3(int[] array, int data){
        int low=0, high = array.length-1;
        int mid = (high-low)/2 + low;

        while (low <= high){

        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = new int[]{9,10,11,1,2,3,4,5};
        System.out.println(BinarySearch.loopBinarySearch2(array, 111));
        System.out.println(BinarySearch.sqrt(5, 0.000001));
    }
}
