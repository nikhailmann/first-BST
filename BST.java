import java.util.*;
/**
 * BST.java
 * This class creates a Binary Search Tree and has various functions for it 
 * @author Nikhail Mann
 */
public class BST <T>
{
    BSTNode root;

    public void insert(T insertMe) {
        BSTNode insertNode = new BSTNode(insertMe);
        if (root == null){
            root = insertNode;
            return;
        }
        BSTNode curr = root;
        BSTNode next = root;
        boolean left = true;
        while(next != null){
            curr = next;
            if (insertNode.compareTo(curr) <= 0){
                next = curr.getLeft();
                left = true;
            } else {
                next = curr.getRight();
                left = false;
            }
        }
        if (left) {
            curr.setLeft(insertNode);
        } else {
            curr.setRight(insertNode);
        }
    }

    public boolean exists(T checkMe){
        BSTNode checkNode = new BSTNode(checkMe);
        BSTNode curr = root;
        while(curr != null){
            if (checkNode.compareTo(curr) < 0){
                curr = curr.getLeft();
            } else if (checkNode.compareTo(curr) > 0){
                curr = curr.getRight();
            } else {
                return true;
            }
        }
        return false;
    }

    public void inOrderPrint() {
        inOrderPrint(root);
    }

    public void inOrderPrint(BSTNode n){
        if (n == null) return;
        inOrderPrint(n.getLeft());
        System.out.println(n.get());
        inOrderPrint(n.getRight());
    }

    /**
     * The printTree method prints a breadth-first traversal of the BST using the queue approach
     */
    public void printTree(){
        if (root == null) {
            return;
        } else {
            ArrayList<BSTNode> queue = new ArrayList<BSTNode>();
            queue.add(root);
            while (!queue.isEmpty()) {
                System.out.println(queue.get(0).get());
                if (queue.get(0).getLeft() != null) {
                    queue.add(queue.get(0).getLeft());
                }
                if (queue.get(0).getRight() != null) {
                    queue.add(queue.get(0).getRight());
                }
                queue.remove(0);
            }
        }
    }

    /**
     * The balance method balances the BST by following the balancing algorithm
     */
    public void balance(){
        ArrayList<BSTNode> list = new ArrayList<BSTNode>();
        putInOrder(root, list);
        int midOfArray = list.size()/2;
        BSTNode balanced_parent = list.get(midOfArray);
        root = balanced_parent;
        balance_r(root, list);
    }

    /**
     * The balance_r method recursively creates the balanced BST
     * @param parent - the BSTNode acting as the parent branch for each recursion
     * @param list - ArrayList of the data in the BST
     */
    public void balance_r(BSTNode parent, ArrayList list){
        if (parent == null) return;

        //Prevents infinite loops, most likely by disconnecting leafs (not exactly sure)
        if (list.size() <= 1) {
            parent.setRight(null);
            parent.setLeft(null);
            return;
        }
        //splits ArrayList into two halfs 
        int midOfArray = list.size() / 2;
        ArrayList<BSTNode> lesser_half = new ArrayList<BSTNode>(list.subList(0, midOfArray));
        ArrayList<BSTNode> greater_half = new ArrayList<BSTNode>(list.subList((midOfArray + 1), list.size()));

        //designates the left/right child branches for each recursion and calls them
        //left half recursions
        if(lesser_half.isEmpty()) {
            parent.setLeft(null);
            return;
        } else {
            BSTNode left_child = lesser_half.get(lesser_half.size() / 2);
            parent.setLeft(left_child);
            balance_r(left_child, lesser_half);
        }
        
        //right half recursions
        if(greater_half.isEmpty()) {
            parent.setRight(null);
            return;
        } else {
            BSTNode right_child = greater_half.get(greater_half.size() / 2);
            parent.setRight(right_child);
            balance_r(right_child, greater_half);
        }
    }

    /**
     * The putInOrder method recursively Sorts the ArrayList (Essentially uses the code from the printInOrder method above)
     * @param num - the BSTNode acting as the parent branch for each recursion
     * @param list - ArrayList of the data in the BST
     */
    public void putInOrder(BSTNode num, ArrayList list){
        if (num == null) {
            return;
        } 
        
        putInOrder(num.getLeft(), list);
        list.add(num);
        putInOrder(num.getRight(), list);
    }

    public class BSTNode implements Comparable
    {
        T value;
        BSTNode left;
        BSTNode right;

        public BSTNode(T val) {
            value = val;
        }

        public T get() {
            return value;
        }

        public void set(T val) {
            value = val;
        }

        public BSTNode getLeft() {
            return left;
        }

        public void setLeft(BSTNode n) {
            left = n;
        }

        public BSTNode getRight() {
            return right;
        }

        public void setRight(BSTNode n) {
            right = n;
        }

        public int compareTo(Object o) {
            BSTNode n = (BSTNode) o;
            return ((Comparable)value).compareTo((Comparable) n.get());
        }
    }
}