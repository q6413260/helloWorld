package algo;

/**二叉查找树增删查、遍历(先序、中序、后序)
 * Created by yeming on 2019/1/22.
 */
public class BinarySearchTree {
    private Node tree;

    private final static class Node{
        private int val;
        private Node left;
        private Node right;

        Node(int val){
            this.val= val;
        }
    }

    public void preOrder(Node tree){
        if(tree == null){
            return;
        }
        System.out.println(tree.val);
        preOrder(tree.left);
        preOrder(tree.right);
    }

    public void inOrder(Node tree){
        if(tree == null){
            return;
        }

        inOrder(tree.left);
        System.out.println(tree.val);
        inOrder(tree.right);
    }

    public void postOrder(Node tree){
        if(tree == null){
            return;
        }

        postOrder(tree.left);
        postOrder(tree.right);
        System.out.println(tree.val);
    }

    public Node find(int val){
        Node p = tree;
        while (p != null){
            if(p.val < val){
                p = p.right;
            }else if(p.val > val){
                p = p.left;
            }else{
                return p;
            }
        }

        return null;
    }

    public Node findMin(){
        Node p = tree;
        while (p != null){
            if(p.left == null){
                return p;
            }else{
                p = p.left;
            }
        }

        return p;
    }

    public Node findMax(){
        Node p = tree;
        while (p != null){
            if(p.right == null){
                return p;
            }else{
                p = p.right;
            }
        }

        return p;
    }

    /**
     * 不考虑重复数字
     * @param val
     */
    public void insert(int val){
        Node newNode = new Node(val);
        if(tree == null){
            tree = newNode;
            return;
        }
        Node p = tree;

        while (p != null){
            if(p.val < val){
                if(p.right == null){
                    p.right = newNode;
                    return;
                }else{
                    p = p.right;
                }
            }else{
                if(p.left == null){
                    p.left = newNode;
                    return;
                }else{
                    p = p.left;
                }
            }
        }
    }

    public void delete(int val){
        Node p = tree;
        Node parent = null;

        while (p != null){
            if(p.val < val){
                parent = p;
                p = p.right;
            }else if(p.val > val){
                parent = p;
                p = p.left;
            }else{
                break;
            }
        }

        if(p == null){
            return;
        }

        //被删除的节点左右子节点都存在
        if(p.left != null && p.right != null){
            //找到右子树的最小节点
            Node min = p.right;
            //最小节点的父节点
            Node pmin = p;

            while (min.left != null){
                pmin = min;
                min = min.left;
            }

            //用最小节点的值替换删除节点的值
            p.val = min.val;

            p = min;
            parent = pmin;
        }

        //被删除的节点只有左子节点或者只有右子节点或者都没有
        Node child;
        if(p.left != null){
            child = p.left;
        }else if(p.right != null){
            child = p.right;
        }else{
            child = null;
        }

        //被删除的节点是根节点
        if(parent == null){
            tree = child;
        }else if(parent.left == p){
            parent.left = child;
        }else{
            parent.right = child;
        }
    }

    public int height(Node tree){
        if(tree == null){
            return 0;
        }
        if(tree.left == null && tree.right == null){
            return 0;
        }

        return Math.max(height(tree.left), height(tree.right))+1;
    }

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(33);
        binarySearchTree.insert(16);
        binarySearchTree.insert(50);
        binarySearchTree.insert(13);
        binarySearchTree.insert(12);
        binarySearchTree.insert(11);
        binarySearchTree.insert(51);
        binarySearchTree.insert(52);
        binarySearchTree.insert(53);
        binarySearchTree.insert(54);
        System.out.println(binarySearchTree.height(binarySearchTree.tree));
    }
}
