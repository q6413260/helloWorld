package algo;

/**
 * Created by xiaoming on 08/12/2018.
 */
public class LinkedListQueue {
    private final static class Node{
        private String data;
        private Node next;

        Node(String data, Node next){
            this.data = data;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;

    public LinkedListQueue(){
        head = tail = new Node(null, null);
    }

    public void enqueue(String item){
        Node newNode = new Node(item, null);
        tail.next = newNode;
        tail = tail.next;
    }

    public String dequeue(){
        if(head == tail){
            return null;
        }

        //过滤初始化头节点
        head = head.next;
        return head.data;
    }

    public void printAll(){
        Node p = head;
        while (p != null && p != tail){
            System.out.println(p.next.data);
            p = p.next;
        }
    }

    public static void main(String[] args) {
        LinkedListQueue queue = new LinkedListQueue();
        queue.enqueue("1");
        queue.enqueue("2");
        queue.printAll();
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.printAll();
//        queue.dequeue();
//        queue.printAll();
    }
}
