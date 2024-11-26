package Business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Task_Manager {

    private ArrayList<Task> Tasks = new ArrayList<>();

//  Constructor for Task_Manager
    public Task_Manager(){

    }

// Add, Delete and List Tasks
    public void addTask(Task task) {
        Tasks.add(task);
        System.out.println("Task added: " + task.getTask_name());
    }

    public void removeTask(Task task){
        if (Tasks.contains(task)) { 
            Tasks.remove(task); 
            System.out.println("Task removed: " + task.getTask_name());
        } else {
            System.out.println("Task not found: " + task.getTask_name());
        }
    }
    
//Overloading
    public void listTasks() {
        System.out.println("Tasks:");
        for (Task task : Tasks) {
            System.out.println(task.toString());
        }
    }

//Overloading
    public void listTasks(ArrayList<Task> TasksList) {
        System.out.println("Tasks:");
        for (Task task : TasksList) {
            System.out.println(task.toString());
        }
    }

// Search for tasks

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

    public void SortRoomNum(){
        Collections.sort(Tasks, new Comparator<Task>() {
            @Override
            public int compare(Task T1, Task T2) {
                return T1.getRoomNum().compareTo(T2.getRoomNum());
            }
        });
    }

    public void SortTaskViaTime(){
        Tasks.sort((T1, T2) -> T1.getTimeTrack().getCreateTime().compareTo(T2.getTimeTrack().getCreateTime()));
    }
    
// Search for tasks

    public List<Task> SearchTask(String TargetTask, String SearchCategory) {
        ArrayList<Task> SearchedTasks = new ArrayList<>();

        for (Task task : Tasks) {
            String valueToSearch = "";
            switch (SearchCategory.toLowerCase()) {
                case "task id":
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
                    valueToSearch = task.getTimeTrack().getCreateTime().toString();
                    break;
                case "room#":
                    valueToSearch = task.getRoomNum();
                    break;
                default:
                    System.out.println("Invalid category: " + SearchCategory);
            }

            if (valueToSearch.toLowerCase().contains(TargetTask.toLowerCase())) {
                SearchedTasks.add(task);
            }
        }

        if (SearchedTasks.isEmpty()) {
            System.out.println("No match found for " + TargetTask + " in " + SearchCategory);
        } else {
            System.out.println("Found " + SearchedTasks.size() + " matching tasks.");
            listTasks(SearchedTasks);
        }
    

        return SearchedTasks;
    }

	public Object completeTask(String selectedValue) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object generateReport() {
		// TODO Auto-generated method stub
		return null;
	}


}
