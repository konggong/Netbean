package java.question;

abstract public class Test1 {

    String[] stuff = new String[]{"Pizza", "Shoes"};

    public void readStuff() {
        for (String p : stuff) {
            System.out.println(p);
        }
    }
}
