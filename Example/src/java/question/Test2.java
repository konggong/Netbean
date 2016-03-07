package java.question;

public class Test2 extends Test1 {

    String[] stuff = new String[]{"Harmonica", "Saxophone", "Particle Accelerator"};

    public static void main(String[] args) {
        Test2 a = new Test2();
        a.readStuff();

    }
}
/*
This way you are hiding the parent variable stuff with the defined stuff.
Try giving value to stuff in the initialization block (or in the constructor):

abstract public class Test1 {
  protected String[] stuff;

  {
    stuff = new String[] { "Pizza", "Shoes" };
  }

  public void readStuff() {
    for(String p : stuff) { 
      System.out.println(p); 
    }
  }
}
..

public class Test2 extends Test1 {
  {
    stuff = new String[] { "Harmonica", "Saxophone", "Particle Accelerator" };
  }
  
  public static void main(String[] args) {
      Test2 a = new Test();
      a.readStuff();
  }
}
*/
