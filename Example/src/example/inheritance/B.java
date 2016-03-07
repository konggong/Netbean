package example.inheritance;

public class B extends A {
    
    public String getNameCode2() {
        return name + "456";
    }
    
    public String getNameCode3() {
        return name + "789";
    }
    
    @Override
    public String getNameCode4() {
        return name + "000";
        
    }
     
    public static void main(String[] args) {
        B b = new B();
        System.out.println("b.name           = " + b.name);
        System.out.println("b.getNameCode1() = " + b.getNameCode1());
        System.out.println("b.getNameCode2() = " + b.getNameCode2());
        System.out.println("b.getNameCode3() = " + b.getNameCode3());
        System.out.println("b.getNameCode4() = " + b.getNameCode4());
    }
    
}
