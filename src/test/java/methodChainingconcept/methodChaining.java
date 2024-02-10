package methodChainingconcept;

public class methodChaining {
    public static void main(String[] args) {
        a1().a2().a3();
    }

    public static methodChaining a1() {
        System.out.println("this is method a1");
        return new methodChaining();
    }

    public methodChaining a2() {
        System.out.println("this is method a2");
        return new methodChaining();
    }

    public methodChaining a3() {
        System.out.println("this is method a3");
        return new methodChaining();
    }
}
