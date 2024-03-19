import java.util.Objects;

public class Teacher implements Comparable<Teacher> {
    String imie;
    String nazwisko;
    TeacherCondition stan;
    int rok_urodzenia;
    double wynagrodzenie;

    public Teacher(String name, String surname, TeacherCondition cond,
                   int yob, double salary) {
        imie = name; nazwisko = surname; stan = cond;
        rok_urodzenia = yob; wynagrodzenie = salary;
    }

    public void printing() {
        System.out.printf("Nauczyciel: %s %s (%d). Stan: %s. Wynagrodzenie: %f",
                imie, nazwisko, rok_urodzenia, stan.toString(), wynagrodzenie);
    }

    @Override
    public int compareTo(Teacher o) {
        return nazwisko.compareTo(o.nazwisko);
    }

    public boolean compare(String nazw) {
        return Objects.equals(nazwisko, nazw);
    }

    public int compareTo(String nazw) {
        return nazwisko.compareTo(nazw);
    }
}

