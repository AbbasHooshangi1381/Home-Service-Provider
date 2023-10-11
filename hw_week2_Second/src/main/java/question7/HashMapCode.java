package question7;

import java.util.ArrayList;
import java.util.List;

public class HashMapCode {
    private final int Size=50;
    private Entry [] bag;

    public HashMapCode(Entry[] bag) {
        this.bag = bag;
    }

    public void put(String key , String value){
        int indexOf=Math.abs(key.hashCode()%Size);
        Entry entry=bag[indexOf];

        if (entry==null){
            bag[indexOf]=new Entry(key,value);

        }
        else
        { while (entry.getNext()!=null){
            if (entry.getKey().equals(key)){
                entry.setValue(value);
                return;
            }
            entry=entry.getNext();
        }
    }

}

public boolean contain(String key){
    int indexOf=Math.abs(key.hashCode()%Size);
    Entry entry=bag[indexOf];

    while (entry !=null){
        if (entry.getKey().equals(key)){
            return true ;
        }
        entry=entry.getNext();
    }
    return false;


}

public boolean emptyHashMap(){
    for (Entry entry:bag) {
        if (entry!=null){
            return false;
        }
    }
    return true;

}

public List<String> all(){
List <String> list=new ArrayList<>();
    for (Entry entry:bag) {
        while (entry!=null){
            list.add(entry.getValue());
            entry=entry.getNext();
        }
    }
    return list;

    }

/*    public void replace(String key , String value){

    }*/



}