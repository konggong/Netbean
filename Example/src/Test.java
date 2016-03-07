
public class Test {

    public static void main(String[] args) {
        int a = 0;
        int b = 1;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        
        a = b++;
        System.out.println("a = b++");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        
        a = ++b;
        System.out.println("a = ++b");
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        
    }
}
