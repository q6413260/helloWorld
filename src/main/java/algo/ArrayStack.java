package algo;

/**基于数组实现的栈，入栈、出栈
 * Created by xiaoming on 08/12/2018.
 */
public class ArrayStack {
    private String[] items;
    private int count;
    private int len;

    public ArrayStack(int len){
        items = new String[len];
        this.len = len;
        this.count = 0;
    }

    public boolean push(String item){
        if(count == len){
            return false;
        }

        items[count++] = item;
        return true;
    }

    public String pop(){
        if(count == 0){
            return null;
        }

        String item = items[count-- - 1];
        items[count] = null;
        return item;
    }

    public static void main(String[] args) {
        int count = 5;
        int a = count---1;
        System.out.println(a);
        System.out.println(count);
    }
}
