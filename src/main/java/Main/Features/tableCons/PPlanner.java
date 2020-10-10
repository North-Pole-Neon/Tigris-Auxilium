package Main.Features.tableCons;

public class PPlanner {
    int id;
    String name;
    String description;
    int status;
    int priority;
    String dueDate;
    int cat;

    public PPlanner(int id, String name, String description, int status, int priority, String dueDate, int cat) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
        this.cat = cat;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getStatus() {
        return status;
    }

    public int getPriority() {
        return priority;
    }

    public String getDueDate() {
        return dueDate;
    }

    public int getCat() {
        return cat;
    }
}
