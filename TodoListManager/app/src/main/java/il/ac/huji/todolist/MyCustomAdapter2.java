package il.ac.huji.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by user on 16/03/2016.
 */
public class MyCustomAdapter2 extends BaseAdapter {

    Context context;
    Task [] data;
    private static LayoutInflater inflater = null;
    public MyCustomAdapter2(Context context, Task [] data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
                v = inflater.inflate(R.layout.row, null);

            Task t = (Task)getItem(position);
            if (t != null) {
                TextView tt1 = (TextView)v.findViewById(R.id.txtTodoTitle);
                TextView tt2 = (TextView)v.findViewById(R.id.txtTodoDueDate);
                tt1.setText(t.task);
                tt2.setText(t.date);
            }
        }
        return v;
    }
}
