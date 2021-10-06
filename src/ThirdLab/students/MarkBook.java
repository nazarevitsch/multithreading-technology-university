package ThirdLab.students;

import java.util.ArrayList;
import java.util.List;

public class MarkBook {

    private List<Group> groups;

    public MarkBook() {
        groups = new ArrayList<>();
    }

    public void putGroup(Group group) {
        groups.add(group);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < groups.size(); i++) {
            builder.append("Group N: ");
            builder.append(i);
            builder.append("            Points  A/L\n");
            builder.append(groups.get(i).toString());
            builder.append("\n");
        }
        return builder.toString();
    }
}
