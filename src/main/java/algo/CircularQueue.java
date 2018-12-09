package algo;

/**
 * Created by xiaoming on 08/12/2018.
 */
public class CircularQueue {
    private String[] items;
    private int len;
    private int head;
    private int tail;

    public CircularQueue(int len){
        this.len = len;
        this.items = new String[len];
    }

    public boolean enqueue(String item){
        if((tail+1)%len == head){
            return false;
        }

        items[tail] = item;
        tail = (tail+1)%len;
        return true;
    }

    public String dequeue(){
        if(head == tail){
            return null;
        }

        String item = items[head];
        head = (head+1)%len;
        return item;
    }
}
