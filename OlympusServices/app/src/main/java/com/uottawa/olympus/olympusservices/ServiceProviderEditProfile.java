package com.uottawa.olympus.olympusservices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class ServiceProviderEditProfile extends AppCompatActivity {
    String username;
    DBHelper dbHelper;

    /**
     * Prepoplates the fields with user information when the activity is created
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider_edit_profile);
        Bundle bundle = getIntent().getExtras();
        username = bundle.getString("username");
        dbHelper = new DBHelper(this);
        ServiceProvider user;
        user = (ServiceProvider)dbHelper.findUserByUsername(username);
        TextView firstname = findViewById(R.id.FirstNameInput);
        TextView lastname = findViewById(R.id.LastNameInput);
        TextView password = findViewById(R.id.PasswordInput);
        TextView companyname = findViewById(R.id.CompanyNameInput);
        TextView address = findViewById(R.id.AddressInput);
        TextView phonenumber = findViewById(R.id.PhoneNumberInput);
        TextView description = findViewById(R.id.DescriptionInput);
        CheckBox licensed = findViewById(R.id.LicensedInput);


        firstname.setText(user.getFirstname());
        lastname.setText(user.getLastname());
        password.setText("");
        companyname.setText(user.getCompanyname());
        address.setText(user.getAddress());
        phonenumber.setText(user.getPhonenumber());
        description.setText(user.getDescription());
        licensed.setChecked(user.isLicensed());

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

    /**
     * Saves updated user information to the database
     * @param view
     */
    public void Save(View view){
        TextView firstname = findViewById(R.id.FirstNameInput);
        TextView lastname = findViewById(R.id.LastNameInput);
        TextView password = findViewById(R.id.PasswordInput);
        TextView companyname = findViewById(R.id.CompanyNameInput);
        TextView address = findViewById(R.id.AddressInput);
        TextView phonenumber = findViewById(R.id.PhoneNumberInput);
        TextView description = findViewById(R.id.DescriptionInput);
        CheckBox licensed = findViewById(R.id.LicensedInput);

        //Checks for the fields
        if((password.getText().toString().length()>=5 || password.getText().toString().equals(""))
            && firstname.getText().toString().length()>0
            && lastname.getText().toString().length()>0 && companyname.getText().toString().length()>0
            && address.getText().toString().length()>0 && phonenumber.getText().toString().length()>0
            && password.getText().toString().matches("[a-zA-Z0-9]*")
            && firstname.getText().toString().matches("[a-zA-Z]*")
            && lastname.getText().toString().matches("[a-zA-Z]*")
            && companyname.getText().toString().matches("^[a-zA-Z0-9_ ]*$")
            && address.getText().toString().matches("^[a-zA-Z0-9_ ]*$")
            && description.getText().toString().matches("^[a-zA-Z0-9_ ]*$")
            && phonenumber.getText().toString().matches("^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$")
            && address.getText().toString().replaceAll("\\s+","").length()>0) {


            if(dbHelper.updateUserInfo(username, password.getText().toString(), firstname.getText().toString(), lastname.getText().toString(),
                address.getText().toString(), phonenumber.getText().toString(), companyname.getText().toString(), licensed.isChecked(), description.getText().toString())){
                //add comment method here
                Toast.makeText(this, "Profile has been updated", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "Could not update profile ", Toast.LENGTH_LONG).show();

            }

        }
        else{
            Toast.makeText(this, "Fields cannot be empty (other than description) and must be formatted correctly", Toast.LENGTH_LONG).show();
        }
    }


}
