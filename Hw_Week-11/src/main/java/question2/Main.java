package question2;

public class Main {
    public static void main(String[] args) {
        Deadlock deadlock=new Deadlock();

        new Thread(deadlock::work1, " operate 1");
        new Thread(deadlock::work2, " operate 2");
    }


}
