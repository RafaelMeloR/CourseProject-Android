# CourseProject-Android

Create an Android Project named CourseProject using Android Studio IDE for reading
course information stored into collection of array objects and displaying each element within 
TextView widget as shown hereafter in Figure.
b) Create Course class which represents Model class of MVC pattern and defines data 
attributes of every course record (course_id, course_name, credits, max_enrl). Add a method 
CalculateCourseFees() that calculates and returns the cost of all student paid fees 
(max_enrl * 250) 
c) Override methods onStart(), onResume(), onPause(), onStop(), and onDestroy(). Press Home 
button and rotate mobile device to see these methods being invoked by Android OS in Log 
viewer tool of Android Studio.
d) Add button “Next Course” to skip through each element of Course array of objects and 
displays course info within TextView widget as shown hereafter in Figure.
e) Add button “Total Course Fees” to display total course fees into TextView widget 
and within device screen layout using Toast class as shown hereafter in Figure.
420-952-VA App.Dev.2 (Mobile) 2/5 CEGEP VANIER COLLEGE 2024
2. Adding layout to Landscape Orientation 
a) Add new layout to CourseProject so mobile user will get that layout when she rotates her 
mobile device as shown in Figure hereafter. Use FrameLayout and place the two buttons 
within vertical LinearLayout.
3. Saving Data Across Rotation using Bundle class
a) Notice that when you rotate mobile device, the activity is destroyed and created again to 
match mobile screen configuration. The mobile user will get first element of array at index
0 displayed in landscape orientation as shown in Figure above despite displaying array 
element at index 3 when using portrait orientation. 
b) Use Bundle class object to save currentIndex of array object so that the current course 
object will be displayed whatever mobile user chooses as orientation. Override the method 
onSaveInstanceState(Bundle savaInstanceState)accordingly
