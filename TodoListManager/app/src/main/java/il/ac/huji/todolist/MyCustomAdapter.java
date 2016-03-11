package il.ac.huji.todolist;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by user on 11/03/2016.
 */
public class MyCustomAdapter extends ArrayAdapter<String> {


    public MyCustomAdapter(Context context, int resource) {
        super(context, resource);
    }

    public MyCustomAdapter(Context context, @LayoutRes int resource, @NonNull ArrayList<String> objects) {
            super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView  view = (TextView)convertView;
        if (view == null) {
            view = (TextView)super.getView(position,convertView, parent);
        }
        else {
            String item = getItem(position);
            view.setText(item);
        }
        view.setTextColor(position % 2 == 0 ? Color.RED : Color.BLUE);
        return view;
    }

}
