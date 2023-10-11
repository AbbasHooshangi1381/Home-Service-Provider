package question7;

import java.util.Arrays;
public class HashMap {


  public static void main(String[] args) {
    HashMapCode hashMapCode = new HashMapCode();

    // Add key-value pairs
    hashMapCode.put(1,"abbas");
    hashMapCode.put(2, "ahmad");
    hashMapCode.put(3, "naser");

    System.out.println("Contains key 2== " + hashMapCode.containsKey(3));
    System.out.println("Contains key 4==" + hashMapCode.containsKey(9));

    System.out.println(hashMapCode.isEmpty());

    Object[] values = hashMapCode.getAllValues();
    System.out.println("All values==" + Arrays.toString(values));

    hashMapCode.replace(2, "jamshid");

    values = hashMapCode.getAllValues();
    System.out.println(Arrays.toString(values));
  }
}