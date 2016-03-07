package example.type;

import java.math.BigDecimal;

public class CheckType {
    
    public String chkType(Object objClass) {
        return objClass.getClass().getSimpleName();
    }
    
    
    public static void main(String[] args) {
        CheckType check = new CheckType();
        
        String a = "";
        System.out.println("a type is " + check.chkType(a));
        
        int b = 0;
        System.out.println("b type is " + check.chkType(b));
        
        double c = 0;
        System.out.println("c type is " + check.chkType(c));
        
        long d = 0;
        System.out.println("d type is " + check.chkType(d));
        
        byte e = 0;
        System.out.println("e type is " + check.chkType(e));
        
        BigDecimal f = BigDecimal.ZERO;
        System.out.println("f type is " + check.chkType(f));
        
        StringBuilder g = new StringBuilder();
        System.out.println("g type is " + check.chkType(g));
        
        System.out.println("check type is " + check.chkType(check));
        
    }
    
}
