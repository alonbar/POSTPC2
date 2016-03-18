package il.ac.huji.todolist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TodoListManagerActivity extends AppCompatActivity {
    ArrayList<Task> todoArray =new ArrayList<Task>();
    private String m_Text = "";
    AlertDialog.Builder builder;
    MyCustomAdapter3 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        adapter = new MyCustomAdapter3(this, R.layout.row, todoArray);
        ListView listView = (ListView) findViewById(R.id.lstTodoItems);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
//        builder = new AlertDialog.Builder(this);
//        builder.setTitle("Add a task");
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//
//            @Override
//            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
//
//                final AlertDialog.Builder b = new AlertDialog.Builder(TodoListManagerActivity.this);
//                b.setIcon(android.R.drawable.ic_dialog_alert);
//                b.setMessage(((TextView)view).getText().toString());
//                b.setNeutralButton("Delete " + ((TextView) view).getText().toString(), new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        adapter.remove(adapter.getItem(position));
//                        adapter.notifyDataSetChanged();
//                    }
//                });
//                b.show();
//                return true;
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todo_list_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Intent intent = new Intent(this, AddNewTodoItemActivity.class);
        startActivityForResult(intent, 1);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String taskStr=data.getStringExtra("task");
                Date date = (Date)data.getSerializableExtra("date");
                String dateStr = Integer.toString(date.getDate() + 100).substring(1) + "/" + Integer.toString(date.getMonth() + 100).substring(1) + "/" + Integer.toString(date.getYear());
                Task task = new Task(taskStr, dateStr);
                todoArray.add(task);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        TextView tt1 = (TextView)view.findViewById(R.id.txtTodoTitle);
        menu.setHeaderTitle(tt1.getText().toString());
        menu.add(0, view.getId(), 0, "Delete");//groupId, itemId, order, title
        if (tt1.getText().toString().length() >= 4 && tt1.getText().toString().substring(0,4).equals("Call") == true)
            menu.add(0, view.getId(), 0, tt1.getText().toString());
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getTitle().toString().equals("Delete") ) {
            adapter.remove(adapter.getItem(info.position));
            adapter.notifyDataSetChanged();

        }
        else if (item.getTitle() == "Action 2") {
            System.out.println("calling");
        }
        return true;
    }
}
