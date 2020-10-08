
public class Test {
    
    public static void main(String[] args) {
        //test01();
        test02();
        //test03();
    }
    
    public static void test03() {
        String str = "  ";
        System.out.println("str.length = "+str.length());
    }
    
    public static void test02() {
        String str = "A";
        System.out.println("str.length = " + nullToSpace(str,10).length());
    }
    
    public static String nullToSpace(String str,int i) {
        if (str == null) str = " ";
        if (str.length() < i) str = nullToSpace(str+" ",i);
    return str;
    }

    public static void test01() {
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
