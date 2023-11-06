import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Person> people = MockData.getPeople();
//1
        List<Person> filteredPeople = people.stream()
                .filter(person -> person.getAge() > 50)
                .collect(Collectors.toList());

        System.out.println(filteredPeople);
//2
        List<Person> filteredPeople2 =people.stream().sorted(Comparator.comparing(Person::getUsername)).
                collect(Collectors.toList());
        System.out.println(filteredPeople2);
//3

        List<Person> filteredPeople3 =people.stream().sorted(Comparator.comparing(Person::getAge)
                        .thenComparing(Person::getLastName))
                .collect(Collectors.toList());

        System.out.println(filteredPeople3);
//4

        Set<String> filteredPeople4 =people.stream().map(Person::getIpv4)
                .collect(Collectors.toSet());

        System.out.println(filteredPeople4);

//5
        Map<String,Person>personMap  = (Map<String, Person>) people.stream()
                .sorted(Comparator.comparing(Person::getLastName))
                .filter(person -> person.getGender().equals("Female"))
                .filter(person -> person.getAge() > 40)
                .dropWhile(person -> person.getFirstName()
                        .startsWith("A")).skip(5).limit(100)
                .collect(Collectors.toMap
                        (person -> person.getFirstName() + " " + person.getLastName(), Function.identity()));

        System.out.println(personMap);


//6






    }





}
