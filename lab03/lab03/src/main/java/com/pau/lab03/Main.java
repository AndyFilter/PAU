package com.pau.lab03;

//public class Main {
//    public static void main(String[] args) {
//        ClassContainer cont = new ClassContainer();
//        cont.addClass("Klasa1", 25);
//        cont.addClass("Klasa2", 11);
//        cont.addClass("Klasa3", 2);
//
//        Teacher t1 = new Teacher("Tomasz", "Jabłowski", TeacherCondition.obecny, 1994, 2250);
//        Teacher t2 = new Teacher("Jan", "Jabłoński", TeacherCondition.obecny, 1991, 3200);
//        Teacher t3 = new Teacher("Jerzy", "Jabolowicz", TeacherCondition.obecny, 1984, 2112);
//        Teacher t4 = new Teacher("Slim", "Shady", TeacherCondition.nieobecny, 1978, 4000);
//        Teacher t5 = new Teacher("Krzysztof", "Tomaszewski", TeacherCondition.obecny, 1999, 2000);
//
//        cont.grupy.get("Klasa1").addTeacher(t1);
//        cont.grupy.get("Klasa1").addTeacher(t2);
//        cont.grupy.get("Klasa1").addTeacher(t5);
//        cont.grupy.get("Klasa1").addTeacher(t5); // ten nauczyciel jest już w grupie
//
//        cont.grupy.get("Klasa3").addTeacher(t3);
//        cont.grupy.get("Klasa3").addTeacher(t1);
//        cont.grupy.get("Klasa3").addTeacher(t4); // limit grupy
//
//        System.out.printf("%d pustych klas\n", cont.findEmpty().size());
//
//        System.out.println("\nNauczyciele z Klasy 1 o imieniu / nazwisku z \"Toma\":");
//        cont.grupy.get("Klasa1").findPartial("Toma").forEach(Teacher::printing);
//
//        System.out.println("\nNauczyciele z Klasy 1 posortowani po zarobkach:");
//        cont.grupy.get("Klasa1").sortBySalary().forEach(a -> System.out.printf("%s %s (%.2f$)\n", a.imie, a.nazwisko, a.wynagrodzenie));
//
//        System.out.println("\nPodsumowanie Klasy 1:");
//        cont.grupy.get("Klasa1").summary();
//    }
//}
//
enum TeacherCondition {
    obecny,
    chory,
    delegacja,
    nieobecny,
}