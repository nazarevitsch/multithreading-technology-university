package ThirdLab.students;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MarkBook {

    private List<Group> groups;
    private boolean[] flagsForAssistants;
    private boolean flagForLecturer;

    private int weekNumber;


    public MarkBook(int groupsSize) {
        groups = new ArrayList<>();
        flagsForAssistants = new boolean[groupsSize];
        flagsForAssistants[0] = true;
    }

    public void putGroup(Group group) {
        groups.add(group);
    }

    public synchronized void putMarksByAssistant(int groupNumber) throws InterruptedException {
        while (!flagsForAssistants[groupNumber]){
            wait();
        }
        Random r = new Random();
        groups.get(groupNumber).getStudents()
                .forEach(st -> groups.get(groupNumber).getMarks().get(st).add(new Point(
                        r.nextInt(5) > 2 ? r.nextInt(41) + 60 : 0)));
        changeFlag();
        notifyAll();
    }

    public synchronized void putMarksByLecturer(int weekNumber) throws InterruptedException{
        while (!flagForLecturer){
            wait();
        }
        this.weekNumber = weekNumber;
        Random r = new Random();
        for (int i = 0; i < groups.size(); i++) {
            int finalI = i;
            groups.get(i).getStudents()
                    .forEach(st -> groups.get(finalI).getMarks().get(st).get(weekNumber).setMarkL(
                            r.nextInt(5) > 2 ? r.nextInt(41) + 60 : 0));
        }
        changeFlag();
        System.out.println(this);
        notifyAll();
    }

    public void changeFlag() {
        for (int i = 0; i < flagsForAssistants.length; i++) {
            if (flagsForAssistants[i] && i < flagsForAssistants.length - 1) {
                flagsForAssistants[i] = false;
                flagsForAssistants[i + 1] = true;
                return;
            }
        }
        if (flagForLecturer) {
            flagsForAssistants[0] = true;
            flagForLecturer = false;
        } else {
            flagsForAssistants[flagsForAssistants.length - 1] = false;
            flagForLecturer = true;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("WEEK:  ");
        builder.append(weekNumber);
        builder.append("\n");
        for (int i = 0; i < groups.size(); i++) {
            builder.append("Group N: ");
            builder.append(i + 1);
            builder.append("            Points  A/L\n");
            builder.append(groups.get(i).toString());
            builder.append("\n");
        }
        return builder.toString();
    }
}
