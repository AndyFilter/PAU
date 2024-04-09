package com.pau.lab03;

import java.util.Objects;

public class Teacher implements Comparable<Teacher> {
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public TeacherCondition getStan() {
        return stan;
    }

    public void setStan(TeacherCondition stan) {
        this.stan = stan;
    }

    String imie;
    String nazwisko;
    TeacherCondition stan;
    int rok_urodzenia;

    public double getWynagrodzenie() {
        return wynagrodzenie;
    }

    public void setWynagrodzenie(double wynagrodzenie) {
        this.wynagrodzenie = wynagrodzenie;
    }

    double wynagrodzenie;

    public Teacher(String name, String surname, TeacherCondition cond,
                   int yob, double salary) {
        imie = name; nazwisko = surname; stan = cond;
        rok_urodzenia = yob; wynagrodzenie = salary;
    }

    public void printing() {
        System.out.printf("Nauczyciel: %s %s (%d). Stan: %s. Wynagrodzenie: %f\n",
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

