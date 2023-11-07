import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Person> people = MockData.getPeople();
//1
        List<Person> filteredPeople = people.stream()
                .filter(person -> person.getAge() < 50)
                .sorted(Comparator.comparingInt(Person::getAge))
                .collect(Collectors.toList());

        System.out.println(filteredPeople);
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");

//2
        List<Person> filteredPeople2 = people.stream().sorted(Comparator.comparing(Person::getUsername)).
                collect(Collectors.toList());
        System.out.println(filteredPeople2);

        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
//3


        List<Person> filteredPeople3 = people.stream().sorted(Comparator.comparing(Person::getAge)
                        .thenComparing(Person::getLastName))
                .collect(Collectors.toList());

        System.out.println(filteredPeople3);

        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
//4

        Set<String> filteredPeople4 = people.stream().map(Person::getIpv4)
                .collect(Collectors.toSet());

        System.out.println(filteredPeople4);
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");

//5
        Map<String, Person> personMap = (Map<String, Person>) people.stream()
                .sorted(Comparator.comparing(Person::getLastName))
                .filter(person -> person.getGender().equals("Female"))
                .filter(person -> person.getAge() > 40)
                .dropWhile(person -> person.getFirstName()
                        .startsWith("A")).skip(5).limit(100)
                .collect(Collectors.toMap
                        (person -> person.getFirstName() + " " + person.getLastName(), Function.identity(), (a, b) -> b));

        System.out.println(personMap);

        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
        System.out.println("================&&&&&&&&&&&&&&&&&&===============");
//6

        List<PersonSummary> filteredPeople6 = people.stream().map(person ->{
            PersonSummary personSummary=new PersonSummary();
            personSummary.setId(person.getId());
            personSummary.setFirstName(person.getFirstName());
            personSummary.setLastName(person.getLastName());
            personSummary.setAge(person.getAge());
            personSummary.setGender(person.getGender());
            LocalDate personSummaryDate=LocalDate.parse(person.getBirthDate());
            LocalDate curDate = LocalDate.now();
            Period time=Period.between(personSummaryDate,curDate);
            int age=time.getYears();
            personSummary.setAge(age);
            return personSummary;
        }).toList();

        System.out.println(filteredPeople6);



        double avgAge=filteredPeople6.stream().filter(p->p.getGender().equals("Male")).mapToInt(PersonSummary::getAge)
                .average().orElse(0);
        System.out.println("average of male age is "+avgAge);


    }

}