package il.ac.huji.todolist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Date;

public class AddNewTodoItemActivity extends AppCompatActivity {
    Intent returnIntent = new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_todo_item);
        returnIntent = new Intent();
        Button okBtn = (Button) findViewById(R.id.btnOK);
        okBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText taskView = (EditText) findViewById(R.id.edtNewItem);
                String taskStr = taskView.getText().toString();
                if (taskStr.equals("")){
                    Toast.makeText(getApplicationContext(), "Task field can't remain blank", Toast.LENGTH_LONG).show();
                    setResult(Activity.RESULT_CANCELED, returnIntent);
                    finish();

                }
                returnIntent.putExtra("task", taskStr);
                DatePicker dateView = (DatePicker)findViewById(R.id.datePicker);
                returnIntent.putExtra("date", new Date (dateView.getYear(), dateView.getMonth() + 1, dateView.getDayOfMonth()));
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
        Button cancelBtn = (Button)findViewById(R.id.btnCancel);
        cancelBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
               setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });




    }
}
