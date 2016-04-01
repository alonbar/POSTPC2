package il.ac.huji.todolist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class TodoListManagerActivity extends AppCompatActivity {
    ArrayList<Task> todoArray =new ArrayList<Task>();
    MyCustomAdapter3 adapter;
    Firebase myFirebaseRef;
    boolean onCreateFlag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Firebase.setAndroidContext(this);
        adapter = new MyCustomAdapter3(this, R.layout.row, todoArray);
        ListView listView = (ListView) findViewById(R.id.lstTodoItems);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
        myFirebaseRef = new Firebase("https://glaring-inferno-6020.firebaseio.com/");

        Firebase ref = new Firebase("https://glaring-inferno-6020.firebaseio.com/");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());
                if (onCreateFlag == true) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        todoArray.add(new Task(postSnapshot.getKey(),postSnapshot.getValue(String.class)));
                    }
                    adapter.notifyDataSetChanged();
                    onCreateFlag = false;
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
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
                myFirebaseRef.child(task.task).setValue(task.date);
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        int position = info.position;
        String title = adapter.getItem(position).task;
        menu.setHeaderTitle(title);
        menu.add(0, view.getId(), 0, "Delete");//groupId, itemId, order, title
        if (title.length() >= 4 && title.substring(0, 4).equals("Call") == true)
            menu.add(0, view.getId(), 0, title);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getTitle().toString().equals("Delete") ) {

            myFirebaseRef.child(adapter.getItem(info.position).task).setValue(null);
            adapter.remove(adapter.getItem(info.position));
            adapter.notifyDataSetChanged();

        }
        else {
            String num = item.getTitle().toString().substring(5);
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + num));
            startActivity(intent);
        }
        return true;
    }
}
