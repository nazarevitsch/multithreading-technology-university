package ThirdLab.students;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Group {

    private HashMap<Student, List<Point>> studentsMarksPreEachWeak;

    public Group(List<Student> students) {
        studentsMarksPreEachWeak = new HashMap<>();
        students.forEach(el -> studentsMarksPreEachWeak.put(el, new ArrayList<Point>()));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        studentsMarksPreEachWeak.keySet().forEach(st -> {
            builder.append(st + ",   ");
            studentsMarksPreEachWeak.get(st).forEach(mark -> {
                builder.append(mark.getMarkA());
                builder.append("/");
                builder.append(mark.getMarkL());
            });
            builder.append("\n");
        });
        return builder.toString();
    }
}
