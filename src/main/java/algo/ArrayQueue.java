package algo;

/**基于数组实现的链表，入队和出队
 * Created by xiaoming on 08/12/2018.
 */
public class ArrayQueue {
    private String[] items;
    private int len;
    private int head = 0;
    private int tail = 0;

    public ArrayQueue(int len){
        items = new String[len];
        this.len = len;
    }

    public boolean enqueue(String item){
        if(tail == len){
            return false;
        }

        items[tail++] = item;
        return true;
    }

    public String dequeue(){
        if(head == tail){
            return null;
        }

        String item = items[head++];
        items[head-1] = null;
        return item;
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        System.out.println(queue.enqueue("1"));
        System.out.println(queue.enqueue("2"));
        System.out.println(queue.enqueue("3"));
        System.out.println(queue.enqueue("4"));
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.enqueue("5"));
    }
}
