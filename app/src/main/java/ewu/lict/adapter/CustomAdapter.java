package ewu.lict.adapter;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ewu.lict.MainActivity;
import ewu.lict.R;
import ewu.lict.model.Student;

/**
 * Created by Faculty on 10/6/2017.
 */

public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<Student> arrayListStudents;

    public CustomAdapter(Context c, ArrayList<Student> arrayListStudents) {
        this.c = c;
        this.arrayListStudents = arrayListStudents;
    }

    @Override
    public int getCount() {
        return arrayListStudents.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View custom_view = inflater.inflate(R.layout.custom_item_layout, null);

        TextView tVName = custom_view.findViewById(R.id.textViewName);
        TextView tVCGPA = custom_view.findViewById(R.id.textViewCGPA);
        ImageView iVCall = custom_view.findViewById(R.id.imageViewCall);

        tVName.setText("UserName: " + arrayListStudents.get(position).getUserName());
        tVCGPA.setText("CGPA: " + arrayListStudents.get(position).getCGPA());

        iVCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(c);
                dialog.setTitle("Make a call");
                dialog.setMessage("Do you want to make a call to this student?");
                dialog.setCancelable(false);

                dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String phoneNo = arrayListStudents.get(position).getPhoneNo();
                        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                        dialIntent.setData(Uri.parse("tel:"+phoneNo));
                        c.startActivity(dialIntent);
                    }
                });

                dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                dialog.show();
            }
        });


        return custom_view;
    }
}
