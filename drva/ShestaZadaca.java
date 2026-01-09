import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.NoSuchElementException;


interface Stack<E> {

    // Elementi na stekot se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    	// Vrakja true ako i samo ako stekot e prazen.

    public E peek ();
    	// Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:

    public void clear ();
    	// Go prazni stekot.

    public void push (E x);
    	// Go dodava x na vrvot na stekot.

    public E pop ();
    	// Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}

class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack (int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }


    public boolean isEmpty () {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }


    public E peek () {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth-1];
    }


    public void clear () {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++)  elems[i] = null;
        depth = 0;
    }


    public void push (E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }


    public E pop () {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}




class BNode<E> {
    
    public E info;
    public BNode<E> left;
    public BNode<E> right;

    static int LEFT = 1;
    static int RIGHT = 2;

    public BNode<E> parent;
    public BNode(E info, BNode<E> parent) {
        this.parent = parent;
        this.info = info;
        left = null;
        right = null;
    }
    public BNode(E info) {
        this.parent = null;
        this.info = info;
        left = null;
        right = null;
    }
    
    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }
    
}


class BTree<E> {
    
    public BNode<E> root;
    
    public BTree() {
        root = null;
    }
    
    public BTree(E info) {
        root = new BNode<E>(info);
    }
    
    public void makeRoot(E elem) {
        root = new BNode<E>(elem);
    }
    
    public BNode<E> addChild(BNode<E> node, int where, E elem) {
        
        BNode<E> tmp = new BNode<E>(elem, node);
        
        if (where == BNode.LEFT) {
            if (node.left != null)  // veke postoi element
                return null;
            node.left = tmp;
        } else {
            if (node.right != null) // veke postoi element
                return null;
            node.right = tmp;
        }
        
        return tmp;
    }
    
    public void inorder() {
        System.out.print("INORDER: ");
        inorderR(root);
        System.out.println();
    }
    
    public void inorderR(BNode<E> n) {
        if (n != null) {
            inorderR(n.left);
            System.out.print(n.info.toString()+" ");
            inorderR(n.right);
        }
    }

    public void preorder() {
        System.out.print("PREORDER: ");
        preorderR(root);
        System.out.println();
    }
    
    public void preorderR(BNode<E> n) {
        if (n != null) {
            System.out.print(n.info.toString()+" ");
            preorderR(n.left);
            preorderR(n.right);
        }
    }
    
    public void postorder() {
        System.out.print("POSTORDER: ");
        postorderR(root);
        System.out.println();
    }
    
    public void postorderR(BNode<E> n) {
        if (n != null) {
            postorderR(n.left);
            postorderR(n.right);
            System.out.print(n.info.toString()+" ");
        }
    }
    
    public void inorderNonRecursive() {
        ArrayStack<BNode<E>> s = new ArrayStack<BNode<E>>(100);
        BNode<E> p = root;
        System.out.print("INORDER (nonrecursive): ");
        
        while (true) {
            // pridvizuvanje do kraj vo leva nasoka pri sto site koreni
            // na potstebla se dodavaat vo magacin za podocnezna obrabotka
            while (p != null) {
                s.push(p);
                p = p.left;
            }
            
            // ako magacinot e prazen znaci deka stebloto e celosno izminato
            if (s.isEmpty())
                break;

            p = s.peek();
            // pecatenje (obrabotka) na jazelot na vrvot od magacinot
            System.out.print(p.info.toString()+" ");
            // brisenje na obraboteniot jazel od magacinot
            s.pop();
            // pridvizuvanje vo desno od obraboteniot jazel i povtoruvanje na
            // postapkata za desnoto potsteblo na jazelot
            p = p.right;
            
        }
        System.out.println();
        
    }
    
    int insideNodesR(BNode<E> node) {
        if (node == null)
            return 0;

        if ((node.left == null)&&(node.right == null))
            return 0;

        return insideNodesR(node.left) + insideNodesR(node.right) + 1;
    }

    public int insideNodes() {
        return insideNodesR(root);
    }

    int leavesR(BNode<E> node) {
        if (node != null) {
            if ((node.left == null)&&(node.right == null))
                return 1;
            else
                return (leavesR(node.left) + leavesR(node.right));
        } else {
            return 0;
        }
    }

    public int leaves() {
        return leavesR(root);
    }

    int depthR(BNode<E> node) {
        if (node == null)
            return 0;
        if ((node.left == null)&&(node.right == null))
            return 0;
        return (1 + Math.max(depthR(node.left), depthR(node.right)));
    }

    public int depth() {
        return depthR(root);
    }

    void mirrorR(BNode<E> node) {
        BNode<E> tmp;

        if (node == null)
            return;

        // simetricno preslikuvanje na levoto i desnoto potsteblo
        mirrorR(node.left);
        mirrorR(node.right);

        // smena na ulogite na pokazuvacite na momentalniot jazel
        tmp = node.left;
        node.left = node.right;
        node.right = tmp;

    }

    public void mirror() {
        mirrorR(root);
    }
    
}





public class ShestaZadaca {
    public static void main(String[] args){
        BTree<Integer> tree = new BTree<>();
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt() + scan.nextInt();
        scan.nextLine();
        boolean flag = true;
        for(int i = 0; i<n; i++){
            String[] input = scan.nextLine().split(" ");
            if(flag){
                tree.makeRoot(Integer.parseInt(input[1]));
                flag = false;
            }
            else if(input[0].equals("insert")) addChild(tree, Integer.parseInt(input[1]), tree.root);
            else{
                System.out.println(depthNode(tree.root, Integer.parseInt(input[1])));
            }
        }
    }
    public static int depthNode(BNode<Integer> curr, int goal){
        if(curr.info == goal) return 1;
        int total = 1;
        if(goal > curr.info) return total + depthNode(curr.right, goal);
        else return total + depthNode(curr.left, goal);
    }
    public static BNode<Integer> findNode(BTree<Integer> tree, BNode<Integer> curr, int find){
        if(curr.info == find) return curr;
        else if(find > curr.info) return findNode(tree,curr.right,find);
        else return findNode(tree,curr.left,find);
    }
    public static int countNodes(BTree<Integer> tree, BNode<Integer> curr, int num){
        if(curr == null) return 0;
        int total = 0;
        if(curr.info > num) total++;
        total += countNodes(tree, curr.left, num);
        total += countNodes(tree, curr.right, num);
        return total;
    }
    public static void inOrder(BTree<Integer> tree, BNode<Integer> curr){
        if(curr == null) return;
        inOrder(tree, curr.left);
        System.out.println(curr.info);
        inOrder(tree,curr.right);
    }
    public static void addChild(BTree<Integer> tree, int child, BNode<Integer> node){
        if(child <= node.info){
            if(node.left == null){
                tree.addChild(node, 1, child);
            }else addChild(tree, child, node.left);
        }else{
            if(node.right == null){
                tree.addChild(node, 2, child);
            }else addChild(tree, child, node.right);
        }
    }
}
