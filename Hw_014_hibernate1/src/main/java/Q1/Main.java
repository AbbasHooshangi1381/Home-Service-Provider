package Q1;

public class Main {
    public static void main(String[] args) {

  /*     Rational rational = new Rational(7, 2);
        Rational rational2 = new Rational(7, 3);
        System.out.println(rational.simplifyFraction(6, 15));
        System.out.println("add :" + rational.add(rational2));
        System.out.println("sub :" + rational.sub(rational2));
        System.out.println("mul :" + rational.mul(rational2));
        System.out.println("div :" +rational.div(rational2));
        rational.simplify();
        float floatValue=rational.toFloat();
        System.out.println(floatValue);*/

        Rational input1 = new Rational(2, 4);
        Rational input2 = new Rational(5, 7);

        Rational sum = input1.add(input2);
        System.out.println("Sum: " + sum);

/*        Rational diffrence = input1.subtract(input2);
        System.out.println("diffrence :" + diffrence);

        Rational multiply = input1.mul(input2);
        System.out.println("multiply : :" + multiply);

        Rational divide = input1.div(input2);
        System.out.println("divide :" + divide);

        double floatresault=input1.toFloatingPoint();
        System.out.println("fraction :"+ floatresault);


        String numberString=input1.toString();
        System.out.println(" string number is "+numberString);*/
    }
}
