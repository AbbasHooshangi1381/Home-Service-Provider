package question7;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AbbasHashmap {
    public String[] splitData;
    public String[] arrayKeyValues;

    ///Example input is {ali=20, ahmad=10, naser=30}

    public String[] splitHashMap(String map) {
        map = map.replace("{", "");
        map = map.replace("}", "");
        this.splitData = map.split(",");
        for (String variable:this.splitData) {
            this.arrayKeyValues=variable.split("=");

            for (String keyValue:this.arrayKeyValues) {
                System.out.println(keyValue);
            }
        }
        return this.arrayKeyValues;

     }


}
