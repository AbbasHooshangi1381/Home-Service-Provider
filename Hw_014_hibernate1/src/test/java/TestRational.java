import Q1.Rational;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestRational {

    @Test
     void testAddMethod() {
        Rational rational1 = new Rational(2, 4);
        Rational rational2 = new Rational(5, 7);
        Rational expectedSum = new Rational(17, 14);

        Rational sum = rational1.add(rational2);

        Assertions.assertEquals(expectedSum.getNumerator(), sum.getNumerator());
        Assertions.assertEquals(expectedSum.getDenominator(), sum.getDenominator());
    }


    @Test
     void subMethod(){
        Rational rational1 = new Rational(5, 6);
        Rational rational2 = new Rational(2, 4);
        Rational expectedSub = new Rational(1, 3);

        Rational sub = rational1.sub(rational2);

        Assertions.assertEquals(expectedSub.getNumerator(),sub.getNumerator());
        Assertions.assertEquals(expectedSub.getDenominator(),sub.getDenominator());
    }

    @Test
    private void mulMethod(){
        Rational rational1 = new Rational(3, 4);
        Rational rational2 = new Rational(5, 6);
        Rational expectedMul = new Rational(15, 24);

        Rational mul = rational1.mul(rational2);

        Assertions.assertEquals(expectedMul.getNumerator(),mul.getNumerator());
        Assertions.assertEquals(expectedMul.getDenominator(),mul.getDenominator());
    }

    @Test
     void divMethod(){

        Rational rational1 = new Rational(2, 4);
        Rational rational2 = new Rational(5, 6);
        Rational expectedDiv = new Rational(2, 5);

        Rational div = rational1.div(rational2);

        Assertions.assertEquals(expectedDiv.getNumerator(),div.getNumerator());
        Assertions.assertEquals(expectedDiv.getDenominator(),div.getDenominator());
    }

/*    @Test
    private void toFloatMethod(){
        Rational rational1 = new Rational(7, 2);
        Rational expectedFloat = new Rational(3, 12);

        Rational toFloat = rational1.toFloat(rational2);

        Assertions.assertEquals(expectedFloat.getNumerator(),toFloat.getNumerator());
        Assertions.assertEquals(expectedFloat.getDenominator(),toFloat.getDenominator());
    }*/







}
