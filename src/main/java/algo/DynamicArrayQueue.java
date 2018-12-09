package algo;

/**入队队列满了自动迁移数据到空闲位置
 * Created by xiaoming on 08/12/2018.
 */
public class DynamicArrayQueue {
    private String[] items;
    private int len;
    private int head;
    private int tail;

    public DynamicArrayQueue(int len){
        this.items = new String[len];
        this.len = len;
    }

    //队列len==tail时候，判断head是否大于0，若head>0，则items整体向左移动head位数
    public boolean enqueue(String item){
        if(len == tail){
            if(head == 0){
                return false;
            }

            for(int i=head; i<tail; i++){
                items[i-head] = items[i];
            }

            tail = tail - head;
            head = 0;
            items[tail++] = item;
            return true;
        }

        items[tail ++] = item;
        return true;
    }

    public String dequeue(){
        if(head == tail){
            return null;
        }

        String item = items[head];
        items[head] = null;
        head ++;
        return item;
    }

    public static void main(String[] args) {
        DynamicArrayQueue queue = new DynamicArrayQueue(3);
        System.out.println(queue.enqueue("1"));
        System.out.println(queue.enqueue("2"));
        System.out.println(queue.enqueue("3"));
        System.out.println(queue.enqueue("4"));
        System.out.println(queue.dequeue());
        System.out.println(queue.enqueue("4"));
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.enqueue("5"));
        System.out.println(queue.enqueue("6"));
        System.out.println(queue.enqueue("7"));
        System.out.println(queue.enqueue("8"));
    }
}
