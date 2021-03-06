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

    private final static class LruCache{
        private SingLinkedList list = new SingLinkedList();
        private int size = 0;
        private int capacity;

        LruCache(int capacity){
            this.capacity = capacity;
        }

        public Node access(int val){
            Node node = list.findByVal(val);

            if(node == null){
                list.insertFirst(node);
                if(size < capacity){
                    size ++;
                }else{
                    list.deleteLast();
                }
            }else{
                list.deleteNode(node);
                list.insertFirst(node);
            }
            return node;
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

    public void insertFirst(Node node){
        if(head == null){
            head = node;
        }else{
            node.next = head;
            head = node;
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

    public void deleteNode(Node node){
        if(head == node){
            head = node.next;
            return;
        }

        Node p = head;
        while (p.next != node){
            p = p.next;
        }

        if(p.next == null){
            p.next = null;
        }else{
            p.next = p.next.next;
        }
    }

    public void deleteLast(){
        Node p = head;
        Node q = null;

        while (p.next != null){
            q = p;
            p = p.next;
        }

        q.next = null;
    }

    public void printAll(){
        Node p = head;
        while (p != null){
            System.out.println(p.data);
            p = p.next;
        }
    }

    public boolean isPalindrome(){
        Node slow = head;
        Node fast = head;

        while (slow != null && fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        Node right = null;
        if(fast == null){
            right = slow;
        }else if(fast.next == null){
            right = slow.next;
        }

        Node reverse = SingLinkedList.reverse(right);
        Node tmep = head;
        while(reverse != null){
            if(reverse.data != tmep.data){
                return false;
            }
            reverse = reverse.next;
            tmep = tmep.next;
        }

        return true;
    }

    //需要o(n)的空间复杂度
    public void reverse(){
        Node p = head;

        Node prev = new Node(head.data, null);
        while (p != null && p.next != null){
            p = p.next;
            head = new Node(p.data, prev);
            prev = head;
        }
    }

    //不需要o(n)的空间复杂度
    public void reverse2(){
        Node p = head;
        Node prev = null;

        while (p != null){
            head = p;
            p = p.next;
            head.next = prev;
            prev = head;
        }
    }

    //将Node反转
    public static Node reverse(Node node){
        Node prev = null;
        Node newNode = null;
        while (node != null){
            newNode = node;
            node = node.next;
            newNode.next = prev;
            prev = newNode;
        }

        return newNode;
    }

    //将Node反转，递归方法
    public static Node reverse2(Node prev, Node node){
        if(prev == null && node == null){
            return null;
        }
        if(node.next != null){
            Node temp = node;
            node = node.next;
            temp.next = prev;
            return reverse2(temp, node);
        }else{
            node.next = prev;
            return node;
        }
    }

    //删除单链表倒数节点，使用快慢指针
    // lastIndex 从1开始
    public void delete(int lastIndex){
        Node slow = head;
        Node fast = head;
        while (lastIndex > 0 && fast != null){
            fast = fast.next;
            lastIndex --;
        }

        if(lastIndex > 0){
            return;
        }

        if(fast == null){
            head = head.next;
            return;
        }

        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
    }

    //判断链表是否存在环
    public boolean isAnnular(){
        Node slow = head;
        Node fast = head;
        while (slow != null && fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }

    //两个有序链表合并
    public static Node merge(Node node1, Node node2){
        Node head = new Node(0, null);
        Node p = head;
        Node next;
        while (node1 != null && node2 != null){
            if(node1.data <= node2.data){
                next = node1;
                node1 = node1.next;
            }else{
                next = node2;
                node2 = node2.next;
            }
            p.next = next;
            p = p.next;
        }

        if(node1 == null){
            p.next = node2;
        }
        if(node2 == null){
            p.next = node1;
        }

        return head.next;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1, null);
        Node node2 = new Node(4, null);
        Node node3 = new Node(7, null);
        node1.next = node2;
        node2.next = node3;

        Node node4 = new Node(2, null);
        Node node5 = new Node(6, null);
        node4.next = node5;

        Node node = SingLinkedList.merge(node1, node4);
    }
}
