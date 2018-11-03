public class Person implements Comparable<Person> {
    public String imie;
    public String nazwisko;
    public String kraj;
    public int zarobki;

    public Person(String imie, String nazwisko, String kraj, String zarobki) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.kraj = kraj;
        this.zarobki = Integer.parseInt(zarobki);
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public int getZarobki() {
        return zarobki;
    }

    public String getKraj() {
        return kraj;
    }

    @Override
    public int compareTo(Person o) {
        if(this.zarobki > o.zarobki)
            return this.zarobki;
        else
            return o.zarobki;
    }
}
