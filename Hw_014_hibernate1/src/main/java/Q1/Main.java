package Q1;

public class Main {
    public static void main(String[] args) {

       Rational rational = new Rational(7, 2);
        Rational rational2 = new Rational(7, 3);
        System.out.println(rational.simplifyFraction(6, 15));
        System.out.println("add :" + rational.add(rational2));
        System.out.println("sub :" + rational.sub(rational2));
        System.out.println("mul :" + rational.mul(rational2));
        System.out.println("div :" +rational.div(rational2));
        rational.simplify();
        float floatValue=rational.toFloat();
        System.out.println(floatValue);
    }
}
