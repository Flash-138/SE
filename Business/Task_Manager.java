package Business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Task_Manager {

    private ArrayList<Task> Tasks = new ArrayList<>();
    public Task_Manager(){

    }

    public void addTask(Task task) {
        Tasks.add(task);
        System.out.println("Task added: " + task.getTask_name());
    }

    public void listTasks() {
        System.out.println("Tasks:");
        for (Task task : Tasks) {
            System.out.println(task.toString());
        }
    }

    public void SortTaskPriority(){
        Collections.sort(Tasks, new Comparator<Task>() {
            @Override
            public int compare(Task T1, Task T2) {
                return Integer.compare(T1.getTask_Priority(), T2.getTask_Priority());
            }

        });
    }

    public void SortTaskName(){
        Collections.sort(Tasks, new Comparator<Task>() {
            @Override
            public int compare(Task T1, Task T2) {
                return T1.getTask_name().compareTo(T2.getTask_name());
            }
        });
    }

    public void SortTaskCategory(){
        Collections.sort(Tasks, new Comparator<Task>() {
            @Override
            public int compare(Task T1, Task T2) {
                return T1.getTask_Category().compareTo(T2.getTask_Category());
            }
        });
    }


    public void SortTaskID(){
        Collections.sort(Tasks, new Comparator<Task>() {
            @Override
            public int compare(Task T1, Task T2) {
                return Integer.compare(T1.getTASKID(), T2.getTASKID());
            }
        });
    }

    public void SortTaskViaTime(){
        Tasks.sort((T1, T2) -> T1.getCreateTime().compareTo(T2.getCreateTime()));
    }
    
    public Task SearchTask(String TargetTask, String SearchCategory) {
        boolean found = false;

        for (Task task : Tasks) {
            String valueToSearch = "";
            switch (SearchCategory.toLowerCase()) {
                case "id":
                    valueToSearch = String.valueOf(task.getTASKID());
                    break;
                case "name":
                    valueToSearch = task.getTask_name();
                    break;
                case "description":
                    valueToSearch = task.getTask_Description();
                    break;
                case "category":
                    valueToSearch = task.getTask_Category();
                    break;
                case "priority":
                    valueToSearch = String.valueOf(task.getTask_Priority());
                    break;
                case "creation time":
                    valueToSearch = task.getCreateTime().toString();
                    break;
                default:
                    System.out.println("Invalid category: " + SearchCategory);
            }

            if (valueToSearch.equalsIgnoreCase(TargetTask)) {
                System.out.println("Match found: " + task.toString());
                found = true;
                return task;
            }
        }

        if (!found) {
            System.out.println("No match found for " + TargetTask + " in " + SearchCategory);
        }

        return null;
    }
}
