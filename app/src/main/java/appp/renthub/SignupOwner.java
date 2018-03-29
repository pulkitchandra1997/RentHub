package appp.renthub;


import android.app.Activity;
import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupOwner extends Activity implements View.OnClickListener {

    TextView login,emailicon,phoneicon,nameicon,placesicon,dobicon,statusicon,cityicon,gendericon,addressicon;
    EditText owneremail,ownerphone,ownername,ownerdob,owneraddress;
    Button ownersignup,ownernext,ownerprevious;
    RadioGroup ownergender;
    LinearLayout pageone,pagetwo;
    int day,year,month;
    String name,phone,email,dob,status,city,gender,address,noofplaces;
    Spinner marrystatus,ownercity,ownerplaces;
    RadioButton male,female;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_owner);
        login=findViewById(R.id.login);
        login.setOnClickListener(this);
        ownersignup=findViewById(R.id.ownersignup);
        ownersignup.setOnClickListener(this);
        owneremail=findViewById(R.id.owneremail);
        ownerplaces=findViewById(R.id.ownerplaces);
        placesicon=findViewById(R.id.placesicon);
        owneraddress=findViewById(R.id.owneraddress);
        addressicon=findViewById(R.id.addressicon);
        ownername=findViewById(R.id.ownername);
        ownerphone=findViewById(R.id.ownerphone);
        ownerdob=findViewById(R.id.ownerdob);
        ownergender=findViewById(R.id.ownergender);
        marrystatus=findViewById(R.id.marrystatus);
        ownercity=findViewById(R.id.ownercity);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        emailicon=findViewById(R.id.emailicon);
        phoneicon=findViewById(R.id.phoneicon);
        nameicon=findViewById(R.id.nameicon);
        dobicon=findViewById(R.id.dobicon);
        cityicon=findViewById(R.id.cityicon);
        gendericon=findViewById(R.id.gendericon);
        statusicon=findViewById(R.id.statusicon);
        ownernext=findViewById(R.id.ownernext);
        pagetwo=findViewById(R.id.pagetwo);
        pageone=findViewById(R.id.pageone);
        ownerplaces=findViewById(R.id.ownerplaces);
        ownerprevious=findViewById(R.id.ownerprevious);
        Typeface font = Typeface.createFromAsset(getAssets(), "Font Awesome 5 Free-Solid-900.otf" );
        nameicon.setTypeface(font);
        emailicon.setTypeface(font);
        phoneicon.setTypeface(font);
        ownersignup.setTypeface(font);
        ownerprevious.setTypeface(font);
        ownernext.setTypeface(font);
        dobicon.setTypeface(font);
        cityicon.setTypeface(font);
        gendericon.setTypeface(font);
        statusicon.setTypeface(font);
        addressicon.setTypeface(font);
        placesicon.setTypeface(font);
        ownernext.setOnClickListener(this);
        ownerprevious.setOnClickListener(this);
        ownerdob.setOnClickListener(this);
    }
    @Override
    public void onBackPressed()
    {
        this.startActivity(new Intent(SignupOwner.this,SignUp.class));
        return;
    }
    private boolean isValidName( String name) {
        String NAME_PATTERN = "^[a-zA-Z\\s]*$";
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    private boolean isValidEmail(String mail) {

        return Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }

    private boolean isValidPhone(String target) {
        if (target == null || target.length() < 10 || target.length() > 10) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(target).matches();
        }
    }
    public String getDate(){
        StringBuilder builder=new StringBuilder();
        builder.append(day+"/");
        builder.append((month + 1)+"/");//month is 0 based
        builder.append(year);
        return builder.toString();
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.login){
            Intent intent = new Intent(SignupOwner.this, LoginActivity.class);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                ActivityOptions options = ActivityOptions.makeCustomAnimation(SignupOwner.this, R.anim.fade_in, R.anim.fade_out);
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }
        if (v.getId() == R.id.ownernext)
        {
            name= ownername.getText().toString().trim();
            email= owneremail.getText().toString().trim();
            phone= ownerphone.getText().toString().trim();
            dob=ownerdob.getText().toString().trim();
            status=(String)marrystatus.getSelectedItem();


            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone)||TextUtils.isEmpty(dob)||status.equalsIgnoreCase("Select Marital Status")) {

                if (TextUtils.isEmpty(name)) {
                    ownername.setError("Enter name");
                    ownername.requestFocus();
                }

                if (TextUtils.isEmpty(email)) {
                    owneremail.setError("Enter email");
                    owneremail.requestFocus();
                }

                if (TextUtils.isEmpty(phone)) {
                    ownerphone.setError("Enter phone number");
                    ownerphone.requestFocus();
                }

                if (TextUtils.isEmpty(dob)) {
                    ownerdob.setError("Select DOB");
                    ownerdob.requestFocus();
                }
                if (((String) marrystatus.getSelectedItem()).equalsIgnoreCase("Select Marital Status"))
                {
                    Toast.makeText(this, "Select Marital Status", Toast.LENGTH_SHORT).show();
                    marrystatus.requestFocus();
                    marrystatus.performClick();
                }
                ownername.requestFocus();
            }

            if (!isValidEmail(email)) {
                owneremail.setError("Enter Correct Email");


            }

            else if (!isValidName(name) || !isValidEmail(email) || !isValidPhone(phone)) {

                if (!isValidName(name)) {
                    ownername.setError("Enter Correct Name");
                    owneremail.requestFocus();
                }

                if (!isValidPhone(phone)) {
                    ownerphone.setError("Enter Correct Phone Number");
                    ownerphone.requestFocus();
                }

            }
            else {
               /* Toast.makeText(this, name + email + phone, Toast.LENGTH_SHORT).show();*/
                pagetwo.setVisibility(View.VISIBLE);
                pageone.setVisibility(View.GONE);
            }


        }
        if (v.getId() == R.id.ownerprevious)
        {
            pageone.setVisibility(View.VISIBLE);
            pagetwo.setVisibility(View.GONE);

        }
        if (v.getId() == R.id.ownerdob)
        {
            Calendar mcurrentDate=Calendar.getInstance();
            int mYear=mcurrentDate.get(Calendar.YEAR);
            int mMonth=mcurrentDate.get(Calendar.MONTH);
            int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog mDatePicker=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                    // TODO Auto-generated method stub
                    /*      Your code   to get date and time    */
                    day=selectedday;
                    month=selectedmonth;
                    year=selectedyear;
                    ownerdob.setText(getDate());
                }
            },mYear, mMonth, mDay);
            mDatePicker.setTitle("Select date");
            mDatePicker.show();

        }

        if (v.getId() == R.id.ownersignup) {
            city=(String)ownercity.getSelectedItem();
            address=owneraddress.getText().toString().trim();
            noofplaces=(String)ownerplaces.getSelectedItem();

            if (male.isChecked())
            {
                gender=male.getText().toString();
            }
            else if (female.isChecked())
            {
                gender=female.getText().toString();
            }

            if (!male.isChecked() && !female.isChecked() || ((String) ownercity.getSelectedItem()).equalsIgnoreCase("Select City") || TextUtils.isEmpty(address)|| ((String) ownerplaces.getSelectedItem()).equalsIgnoreCase("Select No of Places"))
            {
                if (!male.isChecked()|| !female.isChecked())
                {
                    Toast.makeText(this, "Please select Gender", Toast.LENGTH_SHORT).show();
                    ownergender.requestFocus();
                }
                if (((String) ownercity.getSelectedItem()).equalsIgnoreCase("Select City"))
                {
                    Toast.makeText(this, "Select City", Toast.LENGTH_SHORT).show();
                    ownercity.requestFocus();
                    ownercity.performClick();
                }
                if (TextUtils.isEmpty(address)) {
                    owneraddress.setError("Enter Address");
                    owneraddress.requestFocus();
                }
                if (((String) ownerplaces.getSelectedItem()).equalsIgnoreCase("Select No of Places"))
                {
                    Toast.makeText(this, "Select No of Places", Toast.LENGTH_SHORT).show();
                    ownerplaces.requestFocus();
                    ownerplaces.performClick();
                }
            }
        }


    }
}
