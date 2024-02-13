package com.example.courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class CourseActivity extends AppCompatActivity {

    private EditText courseIDEditText;
    private EditText courseNameEditText;
    private EditText courseMaxEnrlEditText;
    private EditText courseCreditEditText;

    private Button courseUpdateButton;

    private static  final String EXTRA_COURSE_NO="com.example.courseproject.course_no";
    private static  final String EXTRA_COURSE_NAME="com.example.courseproject.course_name";
    private static  final String EXTRA_COURSE_MAX_ENRL="com.example.courseproject.course_max_enrl";
    private static  final String EXTRA_COURSE_CREDITS="com.example.courseproject.course_credits";

    public static  Intent newIntent(Context packageContext, String course_id, String course_name, int course_max_enrl, int course_credits)
    {
        Intent intent = new Intent(packageContext, CourseActivity.class);
        intent.putExtra(EXTRA_COURSE_NO, course_id);
        intent.putExtra(EXTRA_COURSE_NAME, course_name);
        intent.putExtra(EXTRA_COURSE_MAX_ENRL, course_max_enrl);
        intent.putExtra(EXTRA_COURSE_CREDITS, course_credits);
        return  intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        //Decoding the extra data from the intent object
        String courseIDRetrieve = getIntent().getStringExtra(EXTRA_COURSE_NO);
        String courseNameRetrieve = getIntent().getStringExtra(EXTRA_COURSE_NAME);
        int courseMaxEnrlRetrieve = getIntent().getIntExtra(EXTRA_COURSE_MAX_ENRL,0);
        int courseCreditsRetrieve = getIntent().getIntExtra(EXTRA_COURSE_CREDITS,0);

        //GET THE VIEW OF COURSE_NO_EDIT_TEXT
        courseIDEditText = (EditText) findViewById(R.id.course_no_edit_text);
        courseIDEditText.setText(courseIDRetrieve);

        //GET THE VIEW OF COURSE_NAME_EDIT_TEXT
        courseNameEditText = (EditText) findViewById(R.id.course_name_edit_text);
        courseNameEditText.setText(courseNameRetrieve);

        //GET THE VIEW OF COURSE_MAX_ENRL_EDIT_TEXT
        courseMaxEnrlEditText = (EditText) findViewById(R.id.course_max_enrl_edit_text);
        courseMaxEnrlEditText.setText(Integer.toString( courseMaxEnrlRetrieve));

        //GET THE VIEW OF COURSE_CREDIT_EDIT_TEXT
        courseCreditEditText = (EditText) findViewById(R.id.course_credits_edit_text);
        courseCreditEditText.setText(Integer.toString(courseCreditsRetrieve));

        //Get the view of course_update_button
        courseUpdateButton = (Button) findViewById(R.id.course_update_button);
        courseUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //coding updated infor as extra parameter
                setCourseUpdateCodeResult(courseIDEditText.getText().toString(),
                courseNameEditText.getText().toString(),
                Integer.parseInt(courseMaxEnrlEditText.getText().toString()),
                Integer.parseInt(courseCreditEditText.getText().toString()));
            }
        });


    }
    //coding extra data intent from child to parent activity
    private void setCourseUpdateCodeResult(String course_id, String course_name, int course_max_enrl, int course_credits)
    {
        Intent dataIntent = new Intent();
        dataIntent.putExtra(EXTRA_COURSE_NO, course_id);
        dataIntent.putExtra(EXTRA_COURSE_NAME, course_name);
        dataIntent.putExtra(EXTRA_COURSE_MAX_ENRL, course_max_enrl);
        dataIntent.putExtra(EXTRA_COURSE_CREDITS, course_credits);
        setResult(RESULT_OK,dataIntent);
    }

    //Decoding extra data intent parentActivity
    public static Course sendMessageCourseUpdateResult(Intent resultIntent)
    {
        Course courseUpdateInfo = new Course();
        courseUpdateInfo.setCourse_no(resultIntent.getStringExtra(EXTRA_COURSE_NO));
        courseUpdateInfo.setCourse_name(resultIntent.getStringExtra(EXTRA_COURSE_NAME));
        courseUpdateInfo.setMax_enrl(resultIntent.getIntExtra(EXTRA_COURSE_MAX_ENRL,0));
        courseUpdateInfo.credits = resultIntent.getIntExtra(EXTRA_COURSE_CREDITS,0);
        return courseUpdateInfo;
    }
}