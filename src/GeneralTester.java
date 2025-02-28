package src;


public class GeneralTester {
    public static void main(String[] args) {
        int[] testElements = {3, 8};
        Atom a = new Atom(testElements[1]);
        a.ionise(-2);
        System.out.println(a);
    }

}
