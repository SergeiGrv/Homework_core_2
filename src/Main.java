import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        //Поиск числа несовершеннолетних
        long underage = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println(underage);

        //Список фамилий призывников
        List<String> surnames = persons.stream()
                .filter(x -> x.getAge() > 18 && x.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        System.out.println(surnames);

        //Список потенциально работоспособных людей
        List<Person> capable = persons.stream()
                .filter(x -> x.getAge() > 18 && x.getAge() < 60 && x.getSex() == Sex.WOMAN || x.getAge() < 65 && x.getSex() == Sex.MAN)
                .filter(ed -> ed.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(capable);
    }
}
