package ua.nure.usik.practice6.part5;

public class Part5 {
    public static void main(String[] s){
        Tree<Integer> tree = new Tree<>();
        tree.add(new Integer[]{3, 1, 5, 0, 2, 4});
        System.out.println(tree.add(6));
        System.out.println(tree.add(6));
        System.out.println("~~~~~~~");
        tree.print();
        System.out.println("~~~~~~~");
        System.out.println(tree.remove(5));
        System.out.println(tree.remove(5));
        System.out.println("~~~~~~~");
        tree.print();
    }
}
