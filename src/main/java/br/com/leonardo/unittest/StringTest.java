package br.com.leonardo.unittest;

public class StringTest {
	public static void main(String[] args) {


        String s1 = "abc";

        String s2 = new String("abc");

        String s3 = "abc";

        String s4 = new String("abc");

        String s5 = new String("abc").intern();

        System.out.println("--comparing s1--");

        System.out.println(s1==s2); //false

        System.out.println(s1==s3); //true

        System.out.println(s1==s4); //false

        System.out.println(s1==s5); //true

        System.out.println("--comparing s2--");

        System.out.println(s2==s3); //false

        System.out.println(s2==s4); //false

        System.out.println(s2==s5); //false

       

        System.out.println("--comparing s3--");

        System.out.println(s3==s4); //false

        System.out.println(s3==s5); //true

       

        System.out.println("--comparing s4--");

        System.out.println(s4==s5); //false

       

 }


}
