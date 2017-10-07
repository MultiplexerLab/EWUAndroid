package ewu.lict;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ewu.lict.adapter.CustomAdapter;
import ewu.lict.database.DatabaseHelper;
import ewu.lict.model.Student;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.editTextUseraName) EditText eTUserName;
    @BindView(R.id.editTextPassword) EditText eTPassword;
    @BindView(R.id.editTextPhoneNo) EditText eTPhoneNo;
    @BindView(R.id.editTextEmail) EditText etCGPA;
    @BindView(R.id.listViewStudents) ListView lTStudents;
    ImageView imageViewPassword;
    ArrayList<Student> arrayLStd;
    DatabaseHelper dbHelper;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        imageViewPassword = (ImageView) findViewById(R.id.imageViewPassword);

        Glide.with(MainActivity.this).load("http://icons.iconarchive.com/icons/graphicloads/flat-finance/256/lock-icon.png").into(imageViewPassword);
        Glide.with(MainActivity.this).load("http://wfarm1.dataknet.com/static/resources/icons/set72/c79107f8.png").into((ImageView) findViewById(R.id.imageViewPhoneNo));

        dbHelper = new DatabaseHelper(MainActivity.this);
        arrayLStd = dbHelper.getAllStudentData();
        adapter = new CustomAdapter(MainActivity.this, arrayLStd);
        lTStudents.setAdapter(adapter);

        lTStudents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {

                final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Delete a student");
                dialog.setMessage("Do you want to delete this student?");
                dialog.setCancelable(false);
                dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int student_id = arrayLStd.get(position).getId();
                        int flag = dbHelper.deleteStudent(student_id);
                        if(flag==1){
                            updateAdapter();
                            Toast.makeText(MainActivity.this, "Data is deleted Properly!", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Data is not deleted Properly!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                      dialogInterface.cancel();
                    }
                });

                dialog.show();
                return false;
            }
        });
    }

    public void saveData(View view) {
        Student std = new Student();
        std.setUserName(eTUserName.getText().toString());
        std.setPassword(eTPassword.getText().toString());
        std.setCGPA(Float.parseFloat(etCGPA.getText().toString()));
        std.setPhoneNo(eTPhoneNo.getText().toString());

        boolean flag = dbHelper.insertStudent(std);
        if(flag){
            Toast.makeText(MainActivity.this, "Data is saved Properly!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "Data is not saved Properly!", Toast.LENGTH_LONG).show();
        }
        updateAdapter();
        //Toast.makeText(MainActivity.this, arrayLStd.toString(), Toast.LENGTH_LONG).show();
    }

    private void updateAdapter() {
        arrayLStd.clear();
        arrayLStd.addAll(dbHelper.getAllStudentData());
        adapter.notifyDataSetChanged();
    }
}
