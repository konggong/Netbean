package example.math;

import java.math.RoundingMode;
import java.text.NumberFormat;

public class NumberRound {
    
    public static void main(String[] args) {
        NumberFormat nf = NumberFormat.getNumberInstance();  
        nf.setMaximumFractionDigits(0); // ต่ำแหน่งทศนิยม  
        
        nf.setRoundingMode(RoundingMode.UP);        //ปัดขึ้นเสมอ ถ้าบวกมีค่าเพิ่มขึ้น ลบมีค่าน้อยลง
        System.out.println("RoundingMode.UP");
        System.out.println(" 1.5 rounds to " + nf.format(1.5));
        System.out.println(" 1.1 rounds to " + nf.format(1.1));
        System.out.println("-1.5 rounds to " + nf.format(-1.5));
        System.out.println("-1.1 rounds to " + nf.format(-1.1));
        
        nf.setRoundingMode(RoundingMode.DOWN);      //ปัดลงเสมอ ถ้าบวกมีค่าลดลง ลบมีค่าเพิ่มขึ้น
        System.out.println("RoundingMode.DOWN");
        System.out.println(" 1.5 rounds to " + nf.format(1.5));
        System.out.println(" 1.1 rounds to " + nf.format(1.1));
        System.out.println("-1.5 rounds to " + nf.format(-1.5));
        System.out.println("-1.1 rounds to " + nf.format(-1.1));
        
        nf.setRoundingMode(RoundingMode.CEILING);   //ปัดขึ้นเสมอ ถ้าบวกมีค่าเพิ่มขึ้น ลบมีค่าเพิ่มขึ้น
        System.out.println("RoundingMode.CEILING");
        System.out.println(" 1.5 rounds to " + nf.format(1.5));
        System.out.println(" 1.1 rounds to " + nf.format(1.1));
        System.out.println("-1.5 rounds to " + nf.format(-1.5));
        System.out.println("-1.1 rounds to " + nf.format(-1.1));
        
        nf.setRoundingMode(RoundingMode.FLOOR);     //ปัดลงเสมอ ถ้าบวกมีค่าลดลง ลบมีค่าลงลง
        System.out.println("RoundingMode.UP");
        System.out.println(" 1.5 rounds to " + nf.format(1.5));
        System.out.println(" 1.1 rounds to " + nf.format(1.1));
        System.out.println("-1.5 rounds to " + nf.format(-1.5));
        System.out.println("-1.1 rounds to " + nf.format(-1.1));
        
        nf.setRoundingMode(RoundingMode.HALF_UP);   //ตั้งแต่ .5 ปัดขึ้น น้อยกว่าปัดลง
        System.out.println("RoundingMode.HALF_UP");
        System.out.println(" 1.6 rounds to " + nf.format(1.6));
        System.out.println(" 1.5 rounds to " + nf.format(1.5));
        System.out.println(" 1.1 rounds to " + nf.format(1.1));
        System.out.println("-1.6 rounds to " + nf.format(-1.6));
        System.out.println("-1.5 rounds to " + nf.format(-1.5));
        System.out.println("-1.1 rounds to " + nf.format(-1.1));
        
        nf.setRoundingMode(RoundingMode.HALF_DOWN);
        System.out.println("RoundingMode.HALF_DOWN");   //มากกว่า .5 ปัดขึ้น น้อยกว่าปัดลง
        System.out.println(" 1.6 rounds to " + nf.format(1.6));
        System.out.println(" 1.5 rounds to " + nf.format(1.5));
        System.out.println(" 1.1 rounds to " + nf.format(1.1));
        System.out.println("-1.6 rounds to " + nf.format(-1.6));
        System.out.println("-1.5 rounds to " + nf.format(-1.5));
        System.out.println("-1.1 rounds to " + nf.format(-1.1));
        
        nf.setRoundingMode(RoundingMode.HALF_EVEN);     //ถ้าข้างหน้าเป็นเลขคี่ ตั้งแต่ 5 ปัดขึ้น ข้างหน้าเป็นเลขคู่ มากกว่า 5 ปัดขึ้น
        System.out.println("RoundingMode.HALF_EVEN");
        System.out.println(" 1.5 rounds to " + nf.format(1.5));
        System.out.println(" 4.5 rounds to " + nf.format(4.5));
        System.out.println("-7.5 rounds to " + nf.format(-7.5));
        System.out.println("-10.5 rounds to " + nf.format(-10.5));

    }
    
}
