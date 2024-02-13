package com.example.courseproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private TextView courseText_View;
    private TextView courseTotalFeesText_View;
    private Button courseTotalFeesButton;
    private  Button courseNextButton;
    private Button  courseDetailButton;
    private int currentIndex=0;
    public static String TAG="Course Project";
    public static String KEY_INDEX = "index";

    Course courseRecord1 = new Course("MIS 101", "Intro to info System", 140);
    Course courseRecord2 = new Course("MIS 301", "System Analysis", 35);
    Course courseRecord3 = new Course("MIS 441", "Database Management", 12);
    Course courseRecord4 = new Course("CS 155", "Programing in C++", 90);
    Course courseRecord5 = new Course("MSI 451", "Web_BaseSystems", 30);
    Course courseRecord6 = new Course("CS 551", "Advance Web", 30);
    Course courseRecord7 = new Course("CS 651", "Advance Java", 30);

    //DATA STRUCTURE ARRAY OF OBJECTS
    public Course[] all_courses = new Course[]{courseRecord1,courseRecord2,courseRecord3,courseRecord4
            ,courseRecord5,courseRecord6, courseRecord7};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null)
        {
            currentIndex=savedInstanceState.getInt(KEY_INDEX);
        }

        //Get the view of course_text_view
        courseText_View = (TextView)
        findViewById(R.id.course_text_view);
        courseText_View.setText("Course: "+all_courses[currentIndex].getCourse_no()+" "+all_courses[currentIndex].getCourse_name());

        //Get the view of coursetotalfees_button
        courseTotalFeesButton = (Button)
                findViewById(R.id.coursetotalfees_button);
        courseTotalFeesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the view coursetotalfees_text_view
                courseTotalFeesText_View = (TextView) findViewById(R.id.coursetotalfees_text_view);
                courseTotalFeesText_View.setText("Total course fees: "+ all_courses[currentIndex].calculateTotalFees());

                Toast.makeText(MainActivity.this, "Total course fees: "+all_courses[currentIndex].calculateTotalFees(), Toast.LENGTH_SHORT).show();
            }
        });

        //get the view of coursenext_button
        courseNextButton = (Button) findViewById(R.id.coursenext_button);
        courseNextButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex+1)%all_courses.length;
                courseText_View.setText("Course: "+all_courses[currentIndex].getCourse_no()+" "+all_courses[currentIndex].getCourse_name());

            }
        }));

        //get the course_detail_button
        courseDetailButton = (Button) findViewById(R.id.course_detail_button);
        courseDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //The first approach is to call startActivity as unidirectional communication
                //Only used when sending data from parent activity to child activity
                String courseId  = all_courses[currentIndex].getCourse_no();
                String courseName  = all_courses[currentIndex].getCourse_name();
                int courseMaxEnrl  = all_courses[currentIndex].getMax_enrl();
                int courseCredits  = Course.credits;
                //calling the coding extra
                Intent intent = CourseActivity.newIntent(MainActivity.this,courseId,
                        courseName,courseMaxEnrl,courseCredits);
                //startActivity(intent);
               StartActivityIntent.launch(intent);
            }
        });
    }

    ActivityResultLauncher<Intent> StartActivityIntent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() != Activity.RESULT_OK)
                    {
                        return ;
                    }
                    else
                    {
                        Course courseUpdateinf= CourseActivity.sendMessageCourseUpdateResult(result.getData());
                        courseText_View.setText(String.format("Course: %s %s", courseUpdateinf.getCourse_no(), courseUpdateinf.getCourse_name()));
                        Toast.makeText(MainActivity.this,
                                "Update course: "+ courseUpdateinf.getCourse_no()+ " "+
                                courseUpdateinf.getCourse_name(), Toast.LENGTH_LONG).show();

                        all_courses[currentIndex].setCourse_no(courseUpdateinf.getCourse_no());
                        all_courses[currentIndex].setCourse_name(courseUpdateinf.getCourse_name());
                        all_courses[currentIndex].setMax_enrl(courseUpdateinf.getMax_enrl());
                        Course.credits = Course.credits;
                    }
                }
            }
    );
    @Override
    public  void onStart()
    {
        super.onStart();
        Log.d (TAG, "onStart is called");
    }
    @Override
    public  void onResume()
    {
        super.onResume();
        Log.d (TAG, "onResume is called");
    }
    @Override
    public  void onPause()
    {
        super.onPause();
        Log.d (TAG, "onPause is called");
    }
    @Override
    public  void onStop()
    {
        super.onStop();
        Log.d (TAG, "onStop is called");
    }
    @Override
    public  void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy is called");
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "onSaveInstanceState: called");
        savedInstanceState.putInt(KEY_INDEX,currentIndex);
    } 

}