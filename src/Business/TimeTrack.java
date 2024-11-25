package Business;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeTrack {
    private final LocalDateTime creationTime;
    private LocalDateTime CompleteTime;

    public TimeTrack(){
        creationTime = LocalDateTime.now();
    }

    public void setCompleteTime(){
        this.CompleteTime = LocalDateTime.now();
    }

    public String getCreateTimeString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDateTime = creationTime.format(formatter);
        return formattedDateTime;
    }

    public LocalDateTime getCreateTime(){
        return this.creationTime;
    }
    
    public LocalDateTime getCompleteTime(){
        return this.CompleteTime;
    }

    public String getCompleteTimeString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDateTime =  CompleteTime.format(formatter);
        return formattedDateTime;
    }

}
