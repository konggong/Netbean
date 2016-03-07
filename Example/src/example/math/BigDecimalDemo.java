package example.math;

import java.math.BigDecimal;

public class BigDecimalDemo {

    public static void main(String[] args) {
        BigDecimal num1, num2, ans;
        num1 = new BigDecimal(100);
        num2 = new BigDecimal(25);
        ans = BigDecimal.ZERO;

        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        System.out.println("ans = " + ans);

        //Plus( + )
        ans = num1.add(num2);
        System.out.println(num1 + " + " + num2 + " = " + ans);

        //Minus( - )
        ans = num1.subtract(num2);
        System.out.println(num1 + " - " + num2 + " = " + ans);
        
        //Minus( * )
        ans = num1.multiply(num2);
        System.out.println(num1 + " * " + num2 + " = " + ans);

        //Divide( / )
        ans = num1.divide(num2);
        System.out.println(num1 + " / " + num2 + " = " + ans);

        //Absolute( |num| )
        ans = num2.subtract(num1);
        System.out.println("|" + ans + "| = " + ans.abs());

        //Modulus( % )
        ans = num1.remainder(num2.subtract(new BigDecimal(10)));
        System.out.println(num1 + " % " + num2.subtract(new BigDecimal(10)) + " = " + ans);

        //Get Max
        ans = num1.max(num2);
        System.out.println("The greatest value is " + ans);

        //Get Min
        ans = num1.min(num2);
        System.out.println("The smallest value is " + ans);

        //Equals( = )
        System.out.println("if ( " + num1 + " == " + num2 + " ) [" + num1.equals(num2) + "}");

        //More( > )
        System.out.println("if ( " + num1 + " >  " + num2 + " ) [" + (num1.compareTo(num2) > 0) + "}");

        //Less than( < )
        System.out.println("if ( " + num1 + " <  " + num2 + " ) [" + (num1.compareTo(num2) < 0) + "}");

        //More( >= )
        System.out.println("if ( " + num1 + " >= " + num2 + " ) [" + (num1.compareTo(num2) >= 0) + "}");

        //Less than( <= )
        System.out.println("if ( " + num1 + " <= " + num2 + " ) [" + (num1.compareTo(num2) <= 0) + "}");

    }
}
