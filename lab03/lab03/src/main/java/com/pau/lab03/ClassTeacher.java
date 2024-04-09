package com.pau.lab03;

import javafx.beans.property.SimpleStringProperty;

import java.util.*;

public class ClassTeacher {
    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public SimpleStringProperty name = null;

    List<Teacher> teachers = new ArrayList<>();

    public int getMax_teachers() {
        return max_teachers;
    }
    public void setMax_teachers(int max_teachers) {
        this.max_teachers = max_teachers;
        fill = CalculateFill();
    }
    int max_teachers;

    public float getFill() {
        return fill * 100;
    }

    public void setFill(float fill) {
        this.fill = fill;
    }

    float fill = 0.0f;

    float CalculateFill() {
        return (float)teachers.size() / max_teachers;
    }

    ClassTeacher(String n, int poj) {
        this.name = new SimpleStringProperty(n);
        max_teachers = poj;
    }

    void addTeacher(Teacher teacher) {
        if(teachers.stream().anyMatch(teacher1 -> teacher1 == teacher)) {
            System.out.println("Ten Nauczyciel już jest w podanej grupie");
        }
        else if(teachers.size() < max_teachers) {
            teachers.add(teacher);
        }
        else {
            System.out.println("Limit nauczycieli w grupie został osiągnięty");
        }

        fill = CalculateFill();
    }

    void addSalary(Teacher t, double amount) {
        t.wynagrodzenie += amount;
    }

    void removeTeacher(Teacher t) {
        teachers.remove(t);
        fill = CalculateFill();
    }

    void changeCondition(Teacher t, TeacherCondition cond) {
        t.stan = cond;
    }

    List<Teacher> search(String text) {
        return teachers.stream().filter(a -> a.compare(text)).toList();
    }

    List<Teacher> findPartial(String text) {
        return teachers.stream().filter(a -> a.nazwisko.contains(text) || a.imie.contains(text)).toList();
    }

    long countByCondition(TeacherCondition cond) {
        return teachers.stream().filter(a -> a.stan == cond).count();
    }

    void summary() {
        for(Teacher t: teachers) {
            System.out.printf("Nauczyciel: %s %s, płaca: %.2f$, stan: %s\n",
                    t.imie, t.nazwisko, t.wynagrodzenie, t.stan.toString());
        }
    }

    List<Teacher> sortByName() {
        return teachers.stream().sorted((t1, t2) -> t1.imie.compareToIgnoreCase(t2.imie)).toList();
    }

    List<Teacher> sortBySalary() {
        return teachers.stream().sorted((t1, t2) -> (int) Math.signum(t2.wynagrodzenie - t1.wynagrodzenie)).toList();
    }

    // Zwraca nauczyciela o największym wynagrodzeniu
    Teacher max() {
        return teachers.stream().max((a, b) -> (int) (a.wynagrodzenie - b.wynagrodzenie)).orElseThrow();
        //return Collections.max(teachers);
    }
}

