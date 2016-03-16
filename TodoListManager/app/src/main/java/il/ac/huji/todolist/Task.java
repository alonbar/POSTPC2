package il.ac.huji.todolist;

import java.util.Date;

/**
 * Created by user on 16/03/2016.
 */
public class Task {
    public Date date;
    public String task;
    public Task(String task, Date date){
        this.date = date;
        this.task = task;
    }
}
