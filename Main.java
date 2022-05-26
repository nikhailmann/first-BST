/**
 * Use the debugger to inspect the tree to confirm its
 * structure!
 * Try rearranging the order of insertion to see
 * how it affects tree structure.
 */
public class Main
{
    public static void main(String [] args) {
        BST tree = new BST();
        
        tree.insert("Hammerhead");
        tree.insert("Flounder");
        tree.insert("Kingfisher");
        tree.insert("Lumine");
        tree.insert("Ermine");
        tree.insert("Elephant");
        tree.insert("Cat");
        tree.insert("Aardvark");
        tree.insert("Dolphin");

        // tree.insert("1");
        // tree.insert("5");
        // tree.insert("3");
        // tree.insert("7");
        // tree.insert("2");
        // tree.insert("6");
        // //tree.insert("4");
        // //tree.insert("5");

        tree.inOrderPrint();
        System.out.println();
        tree.balance();
        tree.printTree();
    }
}