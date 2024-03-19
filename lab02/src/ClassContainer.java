import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassContainer {
    Map<String, ClassTeacher> grupy = new HashMap<>();

    void addClass(String name, double poj) {
        grupy.put(name, new ClassTeacher(name, (int)poj));
    }

    void removeClass(String name) {
        grupy.remove(name);
    }

    List<ClassTeacher> findEmpty() {
        return grupy.values().stream().filter(a -> a.teachers.size() == 0).toList();
    }

    void summary() {
        for(ClassTeacher g : grupy.values()) {
            System.out.printf("Grupa: %s, pojemość: %d/%d\n",
                    g.name, g.teachers.size(), g.max_teachers);
        }
    }
}
