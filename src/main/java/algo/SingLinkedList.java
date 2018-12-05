package algo;

/**1,链表的查询、插入、删除
 * Created by yeming on 2018/12/5.
 */
public class SingLinkedList {
    private Node head;

    private final static class Node{
        private int data;
        private Node next;

        Node(int data, Node next){
            this.data = data;
            this.next = next;
        }
    }

    public Node findByVal(int val){
        Node p = head;
        while (p != null && p.data != val){
            p = p.next;
        }

        return p;
    }

    public Node findByNode(Node node){
        Node p = head;
        while (p != node){
            p = p.next;
        }

        return p;
    }

    public void insert(Node node){
        if(head == null){
            head = node;
        }else{
            Node p = head;
            while (p.next != null){
                p = p.next;
            }

            node.next = p.next;
            p.next = node;
        }
    }

    public void insertAfter(Node exist, Node node){
        Node p = head;
        if(p == null){
            head = node;
        }else{
            node.next = exist.next;
            exist.next = node;
        }
    }

    public void insertBefor(Node exist, Node node){
        if(head == null){
            head = node;
        }else{
            Node p = head;

            if(head == exist){
                node.next = head;
                head = node;
                return;
            }

            while (p.next != exist){
                p = p.next;
            }

            node.next = exist;
            p.next = node;
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1, null);
        Node node2 = new Node(2, null);
        Node node3 = new Node(3, null);
        node1.next = node2;
        node2.next = node3;

        SingLinkedList list = new SingLinkedList();
        list.head = node1;
        list.insertBefor(node1, new Node(4, null));
    }
}