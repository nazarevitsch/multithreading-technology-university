package ThirdLab.students;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Group {

    private HashMap<Student, List<Point>> studentsMarksPreEachWeak;

    public Group(List<Student> students) {
        studentsMarksPreEachWeak = new HashMap<>();
        students.forEach(el -> studentsMarksPreEachWeak.put(el, new ArrayList<Point>()));
    }

    public synchronized HashMap<Student, List<Point>> getMarks() {
        return studentsMarksPreEachWeak;
    }

    public Set<Student> getStudents() {
        return studentsMarksPreEachWeak.keySet();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        studentsMarksPreEachWeak.keySet().forEach(st -> {
            builder.append(st + ",   ");
            studentsMarksPreEachWeak.get(st).forEach(mark -> {
                builder.append(mark.getMarkA() > 9 ? mark.getMarkA() : " " + mark.getMarkA());
                builder.append("/");
                builder.append(mark.getMarkL() > 9 ? mark.getMarkL() : " " + mark.getMarkL());
                builder.append(" | ");
            });
            builder.append("\n");
        });
        return builder.toString();
    }
}
