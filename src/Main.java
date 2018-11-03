import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Main {
    public static void main(String[] args) {
        StandardCharsets.UTF_8.name();
        List<Person> per = getPersons("http://szgrabowski.kis.p.lodz.pl/zpo18/dane.txt");

        int sum = per.stream()
                .filter(g -> g.kraj.equals("PL"))
                .sorted(Comparator.comparing(Person::getZarobki).reversed())
                .limit(2)
                .mapToInt(Person::getZarobki)
                .sum();

        System.out.print(sum);

        sum = per.stream()
                .filter(g -> g.kraj.equals("PL"))
                .sorted(Comparator.comparing(Person::getZarobki))
                .limit(2)
                .mapToInt(Person::getZarobki)
                .sum();

        System.out.print(" " + sum);


        Map<String, Long> letterToCount = per.stream()
                .map(g -> g.getKraj())
                .collect(groupingBy(identity(), LinkedHashMap::new, counting()));


        System.out.println();

        for (String i : letterToCount.keySet()) {
            System.out.print(i + ": " + letterToCount.get(i) + " ");
        }
    }

    public static List<Person> getPersons(String uri) {
        List<Person> list = new ArrayList<>();
        BufferedReader reader;
        try {
            URL ur = new URL(uri);
            reader = new BufferedReader(new InputStreamReader(
                    ur.openStream()));

            String line = reader.readLine();
            while (line != null) {
                String[] buf = line.split(" ");
                list.add(new Person(buf[0], buf[1], buf[2], buf[3]));
                //System.out.println(line);
                // read next line
                line = reader.readLine();

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void disp(List<Person> per) {
        for (Person person : per) {
            System.out.println("Pan " + person.imie + " " + person.nazwisko + " pochodzenia " + person.kraj + " zarabia " + person.zarobki);
        }
    }
}
