package Q1;

public class Rational {
    private int numerator = 0;
    private int denominator = 1;


    public Rational(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    //we can use a recursive method to find divisible number that want to use in simplifyFraction method:
    public int divisibleNumber(int a, int b) {
        if (b == 0) {
            return a;
        }
        return divisibleNumber(b, a % b);
    }

    public Rational simplifyFraction(int numerator, int denominator) {
        int finalDivisible = divisibleNumber(numerator, denominator);
        int simplifiedNumerator = numerator / finalDivisible;
        int simplifiedDenominator = denominator / finalDivisible;
        return new Rational(simplifiedNumerator, simplifiedDenominator);
    }

    public  Rational add(Rational num) {
        int newNumerator1 = this.numerator * num.denominator;
        int newNumerator2 = num.numerator * this.denominator;
        int newDenominator = this.denominator * num.denominator;
        return simplifyFraction(newNumerator1 + newNumerator2, newDenominator);
    }

    public Rational sub(Rational num) {
        int newNumerator1 = this.numerator * num.denominator;
        int newNumerator2 = num.numerator * this.denominator;
        int newDenominator = this.denominator * num.denominator;
        return simplifyFraction(newNumerator1 - newNumerator2, newDenominator);
    }

    public Rational mul(Rational num) {
        int newNumerator = this.numerator * num.numerator;
        int newDenominator = this.denominator * num.denominator;
        return simplifyFraction(newNumerator, newDenominator);
    }

    public Rational div(Rational num) {
        if (num.numerator == 0) {
            throw new ArithmeticException("Divisor cannot be zero.");
        }
        double newNumerator = (double) this.numerator / num.numerator;
        double newDenominator = (double) this.denominator / num.denominator;
        return simplifyFraction((int) newNumerator, (int) newDenominator);
    }
    public double toFloatingPoint() {
        return (double) (numerator) / (double) (denominator);
    }

    public void simplify() {
        if (this.numerator > this.denominator) {
            int remaining = this.numerator % this.denominator;
            int divisible = this.numerator / this.denominator;
            System.out.println("simplify :" + remaining + "/" + this.denominator + "+" + divisible);
        } else {
            System.out.println("simplify :" + this.numerator + "/" + this.denominator);
        }
    }

    public String toString() {

        return "Simplified Fraction: " + numerator + "/" + denominator;
    }
}
