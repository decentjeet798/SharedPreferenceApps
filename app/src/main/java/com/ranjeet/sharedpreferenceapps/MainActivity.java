package com.ranjeet.sharedpreferenceapps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<StudentInfo> studentsList;
    LayoutInflater inflater;
    ListView lvCustomListView;
    static int index, top;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initializing the ArrayList and adding to values(objects of StudentsInfo Class) init
        studentsList = new ArrayList<StudentInfo>();
        studentsList.add(new StudentInfo("Ehsan", "08", R.drawable.offcntct));
        studentsList.add(new StudentInfo("Qasim", "21", R.drawable.pg1));
        studentsList.add(new StudentInfo("Nimra", "16", R.drawable.pool1));
        studentsList.add(new StudentInfo("Saleem Ullah", "22", R.drawable.villa1));
        studentsList.add(new StudentInfo("Ehsan", "08", R.drawable.offcntct));
        studentsList.add(new StudentInfo("Qasim", "21", R.drawable.pg1));
        studentsList.add(new StudentInfo("Nimra", "16", R.drawable.pool1));
        studentsList.add(new StudentInfo("Saleem Ullah", "22", R.drawable.villa1));
        studentsList.add(new StudentInfo("Ehsan", "08", R.drawable.offcntct));
        studentsList.add(new StudentInfo("Qasim", "21", R.drawable.pg1));
        studentsList.add(new StudentInfo("Nimra", "16", R.drawable.pool1));
        studentsList.add(new StudentInfo("Saleem Ullah", "22", R.drawable.villa1));
        studentsList.add(new StudentInfo("Ehsan", "08", R.drawable.offcntct));
        studentsList.add(new StudentInfo("Qasim", "21", R.drawable.pg1));
        studentsList.add(new StudentInfo("Nimra", "16", R.drawable.pool1));
        studentsList.add(new StudentInfo("Saleem Ullah", "22", R.drawable.villa1));
        // initializing listView
        lvCustomListView = (ListView) findViewById(R.id.lvCustomListView);
        // adapter will get the values from arrayList and then show in ListView
        CustomAdapter adapter = new CustomAdapter(studentsList);
        // adapter.notifyDataSetChanged();
        lvCustomListView.setAdapter((ListAdapter) adapter);
        lvCustomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ActivityForChangeInTheListView.class);
                startActivity(intent);
            }

            
        });
    }



    @Override
    public void onResume() {
        super.onResume();
        lvCustomListView.setSelectionFromTop(index, top);
    }
    @Override
    protected void onPause() {
        super.onPause();
        index = lvCustomListView.getFirstVisiblePosition();
        View v = lvCustomListView.getChildAt(0);
        top = (v == null) ? 0 : (v.getTop() - lvCustomListView.getPaddingTop());
    }
    public class CustomAdapter extends BaseAdapter {
        ArrayList<StudentInfo> studentsList;
        // Constructor
        public CustomAdapter(ArrayList<StudentInfo> studentsList) {
            this.studentsList = studentsList;
            inflater = getLayoutInflater();
        }
        // this will gives the indexes of the ListView
        @Override
        public int getCount() {
            return studentsList.size();
        }
        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
        // this function will actualy set the view on each index of the list
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            view = inflater.inflate(R.layout.student_info, parent, false);
            TextView tvStudentName = (TextView) view.findViewById(R.id.tvStudentName);
            TextView tvRollNo = (TextView) view.findViewById(R.id.tvRollNo);
            ImageView ivStudentImage = (ImageView) view.findViewById(R.id.ivStudentImage);
            // set the values from arrayList to ListView
            tvStudentName.setText(studentsList.get(position).getName().toString());
            tvRollNo.setText(studentsList.get(position).getRollNo().toString());
            ivStudentImage.setImageResource(studentsList.get(position).getImage());
            return view;
        }
    }
}