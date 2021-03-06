package com.uottawa.olympus.olympusservices;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 *
 * This class is the java class for the Service Provider's Availabilities menu. This class generates the menu from
 * the saved Availabilities that the user has set previously or on default set no Availabilities.
 * The menu gives the option to change availabilities for each day of the week or remove his availability on that
 * day. User will receive a toast if they set impossible availabilities or they will receive a toast saying
 * that their availabilities have been saved.
 *
 */

public class ServiceProviderAvailabilities extends AppCompatActivity {
    private String username;

    /**
     * This class generates the availabilities from the serviceProvider class fields
     * on creation of this menu so the user can edit and change it on the menu.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_availabilities);
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        DBHelper dbHelper = new DBHelper(this);
        ServiceProvider user = (ServiceProvider) dbHelper.findUserByUsername(username);
        int[][] days  = user.getAvailabilities();
        String startTime;
        String endTime;
        int i = 0;
        for(int[] times: days){
            startTime = formatTime(times[0],times[1]);
            endTime = formatTime(times[2],times[3]);
            if(times[0]==0&&times[1]==0&&times[2]==0&&times[3]==0){
                startTime = "START";
                endTime = "END";
            }
            if(i==0){
                ((Button)findViewById(R.id.MondayStart)).setText(startTime);
                ((Button)findViewById(R.id.MondayEnd)).setText(endTime);
            }else if(i==1){
                ((Button)findViewById(R.id.TuesdayStart)).setText(startTime);
                ((Button)findViewById(R.id.TuesdayEnd)).setText(endTime);
            }else if(i==2){
                ((Button)findViewById(R.id.WednesdayStart)).setText(startTime);
                ((Button)findViewById(R.id.WednesdayEnd)).setText(endTime);
            }else if(i==3){
                ((Button)findViewById(R.id.ThursdayStart)).setText(startTime);
                ((Button)findViewById(R.id.ThursdayEnd)).setText(endTime);
            }else if(i==4){
                ((Button)findViewById(R.id.FridayStart)).setText(startTime);
                ((Button)findViewById(R.id.FridayEnd)).setText(endTime);
            }else if(i==5){
                ((Button)findViewById(R.id.SaturdayStart)).setText(startTime);
                ((Button)findViewById(R.id.SaturdayEnd)).setText(endTime);
            }else if(i==6){
                ((Button)findViewById(R.id.SundayStart)).setText(startTime);
                ((Button)findViewById(R.id.SundayEnd)).setText(endTime);
            }
            i++;
        }
    }


    public void onClick(View v) {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            final Button button = (Button)v;

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            String time = "";

                            button.setText(formatTime(hourOfDay,minute));
                            //set availibility for service provider and check start time is less than finish
                        }

                    }, hour, minute, false);
            timePickerDialog.show();
    }

    public void onRemove(View view){
        //set time to Start/End, set availibility for start and end to null
        Button start;
        Button end;
        if(view.getId()==R.id.DeleteMon){
            start = findViewById(R.id.MondayStart);
            end = findViewById(R.id.MondayEnd);
            start.setText("START");
            end.setText("END");
        }else if(view.getId()==R.id.DeleteTuesday){
            start = findViewById(R.id.TuesdayStart);
            end = findViewById(R.id.TuesdayEnd);
            start.setText("START");
            end.setText("END");
        }else if(view.getId()==R.id.DeleteWednesday){
            start = findViewById(R.id.WednesdayStart);
            end = findViewById(R.id.WednesdayEnd);
            start.setText("START");
            end.setText("END");
        }else if(view.getId()==R.id.DeleteThursday){
            start = findViewById(R.id.ThursdayStart);
            end = findViewById(R.id.ThursdayEnd);
            start.setText("START");
            end.setText("END");
        }else if(view.getId()==R.id.DeleteFriday){
            start = findViewById(R.id.FridayStart);
            end = findViewById(R.id.FridayEnd);
            start.setText("START");
            end.setText("END");
        }else if(view.getId()==R.id.DeleteSaturday){
            start = findViewById(R.id.SaturdayStart);
            end = findViewById(R.id.SaturdayEnd);
            start.setText("START");
            end.setText("END");
        }else{
            start = findViewById(R.id.SundayStart);
            end = findViewById(R.id.SundayEnd);
            start.setText("START");
            end.setText("END");
        }
    }

    /**
     * Parses the views of the UI to generate 2D int array and updates the database and user with
     * the new 2D on click of the setTime button.
     *
     * @param view
     */
    public void onSetTimes(View view){
        int[] mondayTime = parseTime( ((Button)findViewById(R.id.MondayStart)).getText().toString(),((Button)findViewById(R.id.MondayEnd)).getText().toString() );
        int[] tuesdayTime = parseTime( ((Button)findViewById(R.id.TuesdayStart)).getText().toString(),((Button)findViewById(R.id.TuesdayEnd)).getText().toString() );
        int[] wednesdayTime = parseTime( ((Button)findViewById(R.id.WednesdayStart)).getText().toString(),((Button)findViewById(R.id.WednesdayEnd)).getText().toString() );
        int[] thursdayTime = parseTime( ((Button)findViewById(R.id.ThursdayStart)).getText().toString(),((Button)findViewById(R.id.ThursdayEnd)).getText().toString() );
        int[] fridayTime = parseTime( ((Button)findViewById(R.id.FridayStart)).getText().toString(), ((Button)findViewById(R.id.FridayEnd)).getText().toString() );
        int[] saturdayTime = parseTime( ((Button)findViewById(R.id.SaturdayStart)).getText().toString(),((Button)findViewById(R.id.SaturdayEnd)).getText().toString() );
        int[] sundayTime = parseTime( ((Button)findViewById(R.id.SundayStart)).getText().toString(),((Button)findViewById(R.id.SundayEnd)).getText().toString() );
        int[][] availabilities = {mondayTime,tuesdayTime,wednesdayTime,thursdayTime,fridayTime,saturdayTime,sundayTime};
        boolean validation = true;
        for(int[] times: availabilities){
            if(!validateTime(times)){
                validation = false;
            }
        }
        if(validation){
            DBHelper dbHelper = new DBHelper(this);
            ServiceProvider user = (ServiceProvider) dbHelper.findUserByUsername(username);
            user.setAvailabilities(availabilities);
            dbHelper.updateAvailability(user);
            Toast.makeText(this, "New Availabilities have been set.", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(this, "All end times must be later then start times.", Toast.LENGTH_LONG).show();
        }


    }

    /**
     * Override so that previous screen refreshes when pressing the
     * back button on this activity of the app.
     *
     */
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(),ServiceProviderWelcome.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }

    private String formatTime(int hours, int minutes){
        String time = "";
        if(hours<10){
            time = time+"0"+hours+":";
        }else{
            time = time+hours+":";
        }
        if (minutes<10){
            time = time+"0"+minutes;
        }
        else {
            time = time+minutes;
        }
        return time;
    }

    private int[] parseTime(String startTime, String endTime){
        int[] times = new int[4];
        if(startTime.equals("START")){
            times[0]=0;
            times[1]=0;
        }else{
            times[0] = Integer.parseInt(startTime.substring(0,2));
            times[1] = Integer.parseInt(startTime.substring(3));
        }
        if(endTime.equals("END")){
            times[2]=0;
            times[3]=0;
        }else{
            times[2] = Integer.parseInt(endTime.substring(0,2));
            times[3] = Integer.parseInt(endTime.substring(3));
        }
        return times;
    }

    private boolean validateTime(int[] time){
        if(time[0]==0&&time[1]==0&&time[2]==0&&time[3]==0){
            return true;
        }
        if(time[2]>time[0]){
            return true;
        }else{
            if(time[2]==time[0]&&time[3]>time[1]){
                return true;
            }else{
                return false;
            }
        }
    }
}
