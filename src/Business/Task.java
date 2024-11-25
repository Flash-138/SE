package Business;

public class Task {
    private String Task_name;
    private String task_Description;
    private int Task_Priority = 0;
    private final int TASKID;
    private static int idCounter = 0;
    private String Category;
    private TimeTrack Time;
    private String RoomNum;
    private String Complete;

    public Task(String Task_name,String task_Description,String Category,String RoomNum){
        this.Task_name = Task_name;
        this.task_Description = task_Description;
        this.TASKID = idCounter++;
        this.Category = Category;
        this.Time = new TimeTrack();
        this.RoomNum = RoomNum;
        this.Complete = "Pending";
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

    public void setRoomNum(String Roomnum){
        this.RoomNum = Roomnum;
    }

    public void CompleteTask(){
        this.Complete = "Complete";
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

    public String getRoomNum(){
        return this.RoomNum;
    }

    public String getCompleted(){
        return this.Complete;
    }

    public TimeTrack getTimeTrack(){
        return this.Time;
    }


    @Override
    public String toString() {
        return "Task ID: " + getTASKID() + ", Room#: " + getRoomNum() +", Name: " + getTask_name() + ", Description: "
                + getTask_Description() + ", Category: " + getTask_Category() +", Priority: " + getTask_Priority()
                + ", Creation Time: " + Time.getCreateTimeString();

    }

    public String TaskComplete(){
        Time.setCompleteTime();
        this.CompleteTask();
        return "Task ID " + getTASKID() + ", Room#: " + getRoomNum() +", Name: " + getTask_name() + ", Description: "
        + getTask_Description() + ", Category: " + getTask_Category() +", Priority: " + getTask_Priority()
        + ", Completion Time: " + Time.getCompleteTimeString();
    }

}
