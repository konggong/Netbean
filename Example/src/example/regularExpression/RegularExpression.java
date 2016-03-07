package example.regularExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* ---------------------------------------------------------------------
 *                        Character Classes
 * ---------------------------------------------------------------------
 * .  	Dot, any character (may or may not match line terminators, read on)
 * \d  	A digit: [0-9]
 * \D  	A non-digit: [^0-9]
 * \s  	A whitespace character: [ \t\n\x0B\f\r]
 * \S  	A non-whitespace character: [^\s]
 * \w  	A word character: [a-zA-Z_0-9]
 * \W  	A non-word character: [^\w]
 * 
 * ---------------------------------------------------------------------
 *                           Quantifiers
 * ---------------------------------------------------------------------
 * *      Match 0 or more times
 * +      Match 1 or more times
 * ?      Match 1 or 0 times
 * {n}    Match exactly n times
 * {n,}   Match at least n times
 * {n,m}  Match at least n but not more than m times
 * 
 * ---------------------------------------------------------------------
 *                        Meta-characters
 * ---------------------------------------------------------------------
 * \   	Escape the next meta-character (it becomes a normal/literal character)
 * ^   	Match the beginning of the line
 * .   	Match any character (except newline)
 * $   	Match the end of the line (or before newline at the end)
 * |   	Alternation (‘or’ statement)
 * ()  	Grouping
 * []  	Custom character class
 * 
 *  ---------------------------------------------------------------------
 */

public class RegularExpression {

    // Matching-Validating    
    public void matching() {
        List<String> input = new ArrayList<>();
        input.add("123-45-6789");
        input.add("9876-5-4321");
        input.add("987-65-4321 (attack)");
        input.add("987-65-4321 ");
        input.add("192-83-7465");


        for (String ssn : input) {
            if (ssn.matches("^(\\d{3}-?\\d{2}-?\\d{4})$")) {
                System.out.println("Found good SSN: " + ssn);
            }
        }
    }
    
    // Extracting-Capturing
    public void extract() {
        String input = "I have a cat, but I like my dog better.";

        Pattern p = Pattern.compile("(mouse|cat|dog|wolf|bear|human)");
        Matcher m = p.matcher(input);

        //List<String> animals = new ArrayList<>();
        while (m.find()) {
            System.out.println("Found a " + m.group() + ".");
            //animals.add(m.group());
        }
    }

    public static void main(String[] args) {
        RegularExpression regX = new RegularExpression();
        regX.matching();
        regX.extract();
    }
}