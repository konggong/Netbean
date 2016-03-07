package example.method;


public class UnderMethod {

    public UnderMethod() {
        aa();
    }
    
    public void aa() {
        bb();
    }
    
    public void bb() {
        cc();
    }
    
    public void cc() {
        dd();
        System.out.println("-----------");
        ee();
    }
    
    public void dd() {
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        System.out.println("name = " + name);
    }
    
    public void ee() {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        
        for (StackTraceElement val : stack) {
            System.out.println(val.getMethodName());
        }
        
        System.out.println("--");
        
        for (int i = 0; i < stack.length ; i++) {
            System.out.println(stack[i].getMethodName());
        }
    }
    
    public static void main(String[] args) {
        System.out.println(new Object(){}.getClass().getEnclosingClass().getSimpleName());
        System.out.println("--");
        UnderMethod underMethod = new UnderMethod();
    }
    
}
