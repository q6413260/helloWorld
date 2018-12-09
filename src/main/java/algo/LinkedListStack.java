package algo;

/**
 * Created by xiaoming on 08/12/2018.
 */
public class LinkedListStack {
    private final class Node{
        private String data;
        private Node next;

        Node(String data, Node next){
            this.data = data;
            this.next = next;
        }
    }

    private int count;
    private int len;
    private Node top;

    public LinkedListStack(int len){
        this.len = len;
        this.count = 0;
    }

    public boolean push(String item){
        if(count == len){
            return false;
        }

        Node node = new Node(item, top);
        if(top == null){
            top = node;
        }else{
            node.next = top;
            top = node;
        }
        count ++;
        return true;
    }

    public String pop(){
        if(count == 0){
            return null;
        }

        String data = top.data;
        top = top.next;
        count --;
        return data;
    }

    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack(5);
        System.out.println(stack.pop());

        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        stack.push("5");
        stack.push("6");

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
