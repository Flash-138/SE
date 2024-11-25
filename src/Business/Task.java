package Business;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String Task_name;
    private String task_Description;
    private int Task_Priority = 0;
    private final int TASKID;
    private static int idCounter = 0;
    private String Category;
    private final LocalDateTime creationTime;
    private LocalDateTime CompleteTime;

    public Task(String Task_name,String task_Description,String Category){
        this.Task_name = Task_name;
        this.task_Description = task_Description;
        this.TASKID = idCounter++;
        this.Category = Category;
        this.creationTime = LocalDateTime.now();
    }

    public void setTask_name(String task_name) {
        this.Task_name = task_name;
    }

    public void setTask_Description(String task_Description) {
        this.task_Description = task_Description;
    }

    public void setCategory(String Category){
        this.Category = Category;
    }

    public void setTask_Priority(int task_Priority) {
        Task_Priority = task_Priority;
    }

    public void setCompleteTime(){
        this.CompleteTime = LocalDateTime.now();
    }

    public String getTask_name() {
        return this.Task_name;
    }

    public String getTask_Description() {
        return this.task_Description;
    }

    public int getTask_Priority() {
        return Task_Priority;
    }

    public int getTASKID() {
        return TASKID;
    }

    public String getTask_Category(){
        return this.Category;
    }

    public String getCreateTimeString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDateTime = creationTime.format(formatter);
        return formattedDateTime;
    }

    public LocalDateTime getCreateTime(){
        return this.creationTime;
    }
    
    public String getCompleteTime(){
        return this.CompleteTime.toString();
    }


    @Override
    public String toString() {
        return "Task ID: " + getTASKID() + ", Name: " + getTask_name() + ", Description: "
                + getTask_Description() + ", Category: " + getTask_Category() +", Priority: " + getTask_Priority()
                + ", Creation Time: " + getCreateTimeString();

    }

}
