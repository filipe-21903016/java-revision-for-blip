package designpatterns.creational.prototype;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Task implements Cloneable {
    private  String title;
    private  String description;
    private  int priority;
    private  int assignedToUserId;
    private  LocalDateTime dueDate;
    private List<String> tags;

    public Task(String title, String description, int priority, int assignedToUserId, LocalDateTime dueDate, List<String> tags) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.assignedToUserId = assignedToUserId;
        this.dueDate = dueDate;
        this.tags = tags;
    }

    @Override
    protected Task clone() throws CloneNotSupportedException {
        Task dolly = (Task) super.clone();
        dolly.setTags(new ArrayList<>(this.tags));
        return dolly;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setAssignedToUserId(int assignedToUserId) {
        this.assignedToUserId = assignedToUserId;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public List<String> getTags() {
        return tags;
    }
}
