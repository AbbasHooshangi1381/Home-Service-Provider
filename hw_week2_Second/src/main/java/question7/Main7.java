package question7;

import java.util.Arrays;
import java.util.List;

public class Main7{
    public static void main(String[] args) {
      Entry [] bag= new Entry[50];
      HashMapCode hashMapCode=new HashMapCode(bag);

      hashMapCode.put("ali","asghar");
      hashMapCode.put("naser","jafar");

      hashMapCode.contain("ali");

      hashMapCode.emptyHashMap();

      List<String> allEntity=hashMapCode.all();




}


}