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

public class SignupTenant extends Activity   implements View.OnClickListener {
    TextView login,emailicon,phoneicon,nameicon,dobicon,statusicon,cityicon,gendericon,addressicon;
    EditText tenantemail,tenantphone,tenantname,tenantdob,tenantaddress;
    Button tenantsignup,tenantnext,tenantprevious;
    RadioGroup tenantgender;
    LinearLayout pageone,pagetwo;
    int day,year,month;
    String name,phone,email,dob,status,city,gender,address;
    Spinner marrystatus,tenantcity;
    RadioButton male,female;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_tenant);
        login=findViewById(R.id.login);
        login.setOnClickListener(this);
        tenantsignup=findViewById(R.id.tenantsignup);
        tenantsignup.setOnClickListener(this);
        tenantemail=findViewById(R.id.tenantemail);
        tenantaddress=findViewById(R.id.tenantaddress);
        addressicon=findViewById(R.id.addressicon);
        tenantname=findViewById(R.id.tenantname);
        tenantphone=findViewById(R.id.tenantphone);
        tenantdob=findViewById(R.id.tenantdob);
        tenantgender=findViewById(R.id.tenantgender);
        marrystatus=findViewById(R.id.marrystatus);
        tenantcity=findViewById(R.id.tenantcity);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        emailicon=findViewById(R.id.emailicon);
        phoneicon=findViewById(R.id.phoneicon);
        nameicon=findViewById(R.id.nameicon);
        dobicon=findViewById(R.id.dobicon);
        cityicon=findViewById(R.id.cityicon);
        gendericon=findViewById(R.id.gendericon);
        statusicon=findViewById(R.id.statusicon);
        tenantnext=findViewById(R.id.tenantnext);
        pagetwo=findViewById(R.id.pagetwo);
        pageone=findViewById(R.id.pageone);
        tenantprevious=findViewById(R.id.tenantprevious);
        Typeface font = Typeface.createFromAsset(getAssets(), "Font Awesome 5 Free-Solid-900.otf" );
        nameicon.setTypeface(font);
        emailicon.setTypeface(font);
        phoneicon.setTypeface(font);
        tenantsignup.setTypeface(font);
        tenantprevious.setTypeface(font);
        tenantnext.setTypeface(font);
        dobicon.setTypeface(font);
        cityicon.setTypeface(font);
        gendericon.setTypeface(font);
        statusicon.setTypeface(font);
        addressicon.setTypeface(font);
        tenantnext.setOnClickListener(this);
        tenantprevious.setOnClickListener(this);
        tenantdob.setOnClickListener(this);
    }
    @Override
    public void onBackPressed()
    {
        this.startActivity(new Intent(SignupTenant.this,SignUp.class));
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
            Intent intent = new Intent(SignupTenant.this, LoginActivity.class);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                ActivityOptions options = ActivityOptions.makeCustomAnimation(SignupTenant.this, R.anim.fade_in, R.anim.fade_out);
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }
        if (v.getId() == R.id.tenantnext)
        {
            name= tenantname.getText().toString().trim();
            email= tenantemail.getText().toString().trim();
            phone= tenantphone.getText().toString().trim();
            dob=tenantdob.getText().toString().trim();
            status=(String)marrystatus.getSelectedItem();


            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone)||TextUtils.isEmpty(dob)||status.equalsIgnoreCase("Select Marital Status")) {

                if (TextUtils.isEmpty(name)) {
                    tenantname.setError("Enter name");
                    tenantname.requestFocus();
                }

                if (TextUtils.isEmpty(email)) {
                    tenantemail.setError("Enter email");
                    tenantemail.requestFocus();
                }

                if (TextUtils.isEmpty(phone)) {
                    tenantphone.setError("Enter phone number");
                    tenantphone.requestFocus();
                }

                if (TextUtils.isEmpty(dob)) {
                    tenantdob.setError("Select DOB");
                    tenantdob.requestFocus();
                }
                if (((String) marrystatus.getSelectedItem()).equalsIgnoreCase("Select Marital Status"))
                {
                    Toast.makeText(this, "Select Marital Status", Toast.LENGTH_SHORT).show();
                    marrystatus.requestFocus();
                    marrystatus.performClick();
                }
                tenantname.requestFocus();
            }

            if (!isValidEmail(email)) {
                tenantemail.setError("Enter Correct Email");


            }

            else if (!isValidName(name) || !isValidEmail(email) || !isValidPhone(phone)) {

                if (!isValidName(name)) {
                    tenantname.setError("Enter Correct Name");
                    tenantemail.requestFocus();
                }

                if (!isValidPhone(phone)) {
                    tenantphone.setError("Enter Correct Phone Number");
                    tenantphone.requestFocus();
                }

            }
            else {
               /* Toast.makeText(this, name + email + phone, Toast.LENGTH_SHORT).show();*/
                pagetwo.setVisibility(View.VISIBLE);
                pageone.setVisibility(View.GONE);
            }


        }
        if (v.getId() == R.id.tenantprevious)
        {
            pageone.setVisibility(View.VISIBLE);
            pagetwo.setVisibility(View.GONE);

        }
        if (v.getId() == R.id.tenantdob)
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
                    tenantdob.setText(getDate());
                }
            },mYear, mMonth, mDay);
            mDatePicker.setTitle("Select date");
            mDatePicker.show();

        }

        if (v.getId() == R.id.tenantsignup) {
            city=(String)tenantcity.getSelectedItem();
            id=tenantgender.getCheckedRadioButtonId();
            address=tenantaddress.getText().toString().trim();
            if (male.isChecked())
            {
                gender=male.getText().toString();
            }
            else if (female.isChecked())
            {
                gender=female.getText().toString();
            }

            if (!male.isChecked()|| !female.isChecked() || ((String) tenantcity.getSelectedItem()).equalsIgnoreCase("Select City") || TextUtils.isEmpty(address)){

                if (TextUtils.isEmpty(address)) {
                    tenantaddress.setError("Enter Address");
                    tenantaddress.requestFocus();
                }
                if (!male.isChecked()|| !female.isChecked())
                {
                    Toast.makeText(this, "Please select Gender", Toast.LENGTH_SHORT).show();
                    tenantgender.requestFocus();
                }
                if (((String) tenantcity.getSelectedItem()).equalsIgnoreCase("Select City"))
                {
                    Toast.makeText(this, "Select City", Toast.LENGTH_SHORT).show();
                    tenantcity.requestFocus();
                    tenantcity.performClick();
                }
            }
        }


    }
}
