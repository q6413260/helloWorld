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

    public static void main(String[] args) {
        int[] array = new int[]{3,5,5,7,9};
        System.out.println(BinarySearch.lastNoMoreThan(array, 5));
        System.out.println(BinarySearch.sqrt(5, 0.000001));
    }
}
