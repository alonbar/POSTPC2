package il.ac.huji.todolist;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by user on 11/03/2016.
 */
public class MyCustomAdapter3 extends ArrayAdapter<Task> {


    public MyCustomAdapter3(Context context, int resource) {
        super(context, resource);
    }

    public MyCustomAdapter3(Context context, @LayoutRes int resource, @NonNull ArrayList<Task> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Task task;

        if (view == null) {
            //view = (TextView)super.getView(position,convertView, parent);
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.row, null);
        }
        task = getItem(position);
        if (task != null) {
            Task item = getItem(position);
            TextView tt1 = (TextView)view.findViewById(R.id.txtTodoTitle);
            TextView tt2 = (TextView)view.findViewById(R.id.txtTodoDueDate);
            tt1.setText(item.task);
            tt2.setText(item.date);
            Date currentDate = new Date(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            String dateStr = task.date;
            int year = Integer.parseInt(dateStr.substring(dateStr.length() - 4));
            int month = Integer.parseInt(dateStr.substring(dateStr.length() - 7, dateStr.length() - 5)) - 1;
            int day = Integer.parseInt(dateStr.substring(dateStr.length() - 10, dateStr.length() - 8));
            Date taskDate = new Date(year, month, day);
            if (currentDate.compareTo(taskDate) > 0) {
                tt1.setTextColor(Color.RED);
                tt2.setTextColor(Color.RED);
            }
            else {
                tt1.setTextColor(Color.BLACK);
                tt2.setTextColor(Color.BLACK);
            }
        }
        return view;
    }

}
