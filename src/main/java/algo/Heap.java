package algo;

/**堆的插入和删除头结点，堆排序
 * Created by yeming on 2019/1/23.
 */
public class Heap {
    private int[] data;
    private int count;
    private int capacity;

    Heap(int capacity){
        this.capacity = capacity;
        this.data = new int[capacity+1];
        this.count = 0;
    }

    public void insert(int val){
        if(count >= capacity){
            return;
        }
        count ++;

        data[count] = val;
        int index = count;
        while (data[index] > data[index/2] && index>=2){
            swap(data, index, index/2);
            index = index/2;
        }
    }

    public void deleteHead(){
        if(count < 1) {
            return;
        }

        data[1] = data[count];
        count--;
        heapify(data, count, 1);
    }

    /**
     * 从上往下堆化
     * @param data
     * @param count
     * @param i
     */
    private static void heapify(int[] data, int count, int i) {
        while (2*i +1 <= count){
            int childMaxIndex,max;
            if(data[2*i] > data[2*i +1]){
                max = data[2*i];
                childMaxIndex = 2*i;
            }else{
                max = data[2*i+1];
                childMaxIndex = 2*i +1;
            }

            if(data[i] > max){
                break;
            }else{
                swap(data, i, childMaxIndex);
                i = childMaxIndex;
            }
        }
    }

    /**
     * 交互数组data中处于a,b位置的元素
     * @param data
     * @param a
     * @param b
     */
    private static void swap(int[] data, int a, int b) {
        int c = data[a];
        data[a] = data[b];
        data[b] = c;
    }

    public void printAll(){
        for(int i=1; i<=count; i++){
            System.out.print(data[i]+",");
        }
        System.out.println();
    }

    /**
     * 数组 a 中的数据从下标从1开始。
     * @param a
     */
    public static void sort(int[]a){
        buildHeap(a);

        int count = a.length-1;

        while (count >=2){
            swap(a, 1, count);
            heapify(a, --count, 1);
        }
    }

    private static void buildHeap(int[] a) {
        int length = a.length-1;
        for(int i=length/2; i>=1; i--){
            heapify(a, length, i);
        }
    }

    public static void main(String[] args) {
        Heap heap = new Heap(10);
        heap.insert(30);
        heap.insert(50);
        heap.insert(40);
        heap.insert(20);
        heap.insert(25);
        heap.insert(35);
        heap.insert(10);
        heap.printAll();
        heap.deleteHead();
        heap.printAll();
        heap.deleteHead();
        heap.printAll();

        int[] a = new int[]{0, 50,40, 30, 35, 20, 10, 25};
        Heap.sort(a);
    }
}
