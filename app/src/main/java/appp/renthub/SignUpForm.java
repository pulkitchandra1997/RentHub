package appp.renthub;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SignUpForm extends Activity implements View.OnClickListener {

    TextView emailicon,phoneicon,nameicon,dobicon,statusicon,cityicon,gendericon,passwordicon,addressicon,typeicon,pincodeicon;
    EditText inputpassword,inputemail,inputphone,inputname,inputdob,inputaddress,inputpincode;
    Button inputsignup,inputnext,inputprevious;
    RadioGroup inputgender;
    LinearLayout pageone,pagetwo;
    int day,year,month;
    String name,phone,email,dob,status,city,gender,address,type,pincode,password;
    Spinner marrystatus,inputcity;
    RadioButton male,female;
    SharedPreferences sp;
    SharedPreferences.Editor se;
    JSONObject jsonObject;
        ProgressBar login_progress;
    ScrollView login_form;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);
        inputsignup=findViewById(R.id.inputsignup);
        login_progress=findViewById(R.id.login_progress);
        login_form=findViewById(R.id.login_form);
        inputsignup.setOnClickListener(this);
        inputemail=findViewById(R.id.inputemail);
        inputaddress=findViewById(R.id.inputaddress);
        addressicon=findViewById(R.id.addressicon);
        passwordicon=findViewById(R.id.passwordicon);
        inputpassword=findViewById(R.id.inputpassword);
        inputname=findViewById(R.id.inputname);
        inputphone=findViewById(R.id.inputphone);
        inputdob=findViewById(R.id.inputdob);
        inputgender=findViewById(R.id.inputgender);
        inputpincode=findViewById(R.id.inputpincode);
        pincodeicon=findViewById(R.id.pincodeicon);
        marrystatus=(Spinner)findViewById(R.id.marrystatus);
        inputcity=findViewById(R.id.inputcity);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        emailicon=findViewById(R.id.emailicon);
        phoneicon=findViewById(R.id.phoneicon);
        nameicon=findViewById(R.id.nameicon);
        typeicon=findViewById(R.id.type);
        dobicon=findViewById(R.id.dobicon);
        cityicon=findViewById(R.id.cityicon);
        gendericon=findViewById(R.id.gendericon);
        statusicon=findViewById(R.id.statusicon);
        inputnext=findViewById(R.id.inputnext);
        pagetwo=findViewById(R.id.pagetwo);
        pageone=findViewById(R.id.pageone);
        inputprevious=findViewById(R.id.inputprevious);
        Typeface font = Typeface.createFromAsset(getAssets(), "Font Awesome 5 Free-Solid-900.otf" );
        nameicon.setTypeface(font);
        emailicon.setTypeface(font);
        phoneicon.setTypeface(font);
        inputsignup.setTypeface(font);
        inputprevious.setTypeface(font);
        pincodeicon.setTypeface(font);
        inputnext.setTypeface(font);
        passwordicon.setTypeface(font);
        dobicon.setTypeface(font);
        cityicon.setTypeface(font);
        gendericon.setTypeface(font);
        statusicon.setTypeface(font);
        addressicon.setTypeface(font);
        inputnext.setOnClickListener(this);
        inputprevious.setOnClickListener(this);
        inputdob.setOnClickListener(this);
        Intent intent=getIntent();
        if(intent!=null){
            email=intent.getStringExtra("email");
            inputemail.setText(email);
            type=intent.getStringExtra("type");
            typeicon.setText(" as "+type);
        }
        sp=getSharedPreferences("RentHub_data",MODE_PRIVATE);
        se=sp.edit();
    }
    @Override
    public void onBackPressed()
    {
        this.startActivity(new Intent(SignUpForm.this,Welcome.class));
        return;
    }

    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            login_progress.setVisibility(show ? View.VISIBLE : View.GONE);
            login_progress.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    login_progress.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            login_progress.setVisibility(show ? View.VISIBLE : View.GONE);

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
        if (v.getId() == R.id.inputnext) {
            name = inputname.getText().toString().trim();
            phone = inputphone.getText().toString().trim();
            dob = inputdob.getText().toString().trim();
            password=inputpassword.getText().toString().trim();
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(dob)||TextUtils.isEmpty(password))
            {
                if(TextUtils.isEmpty(password)){
                    inputpassword.setError("Enter Password");
                    inputpassword.requestFocus();
                }
                if (TextUtils.isEmpty(name)) {
                    inputname.setError("Enter name");
                    inputname.requestFocus();
                }
                if (TextUtils.isEmpty(phone)) {
                    inputphone.setError("Enter phone number");
                    inputphone.requestFocus();
                }
                if (TextUtils.isEmpty(dob)) {
                    inputdob.setError("Select DOB");
                    inputdob.requestFocus();
                }
            } else
                if(!Validation.isValidName(name)||!Validation.isValidPhone(phone)||!Validation.isValidPassword(password)){
                    if(!Validation.isValidPassword(password)){
                        inputpassword.setError("Password must be more than 8 characters");
                        inputpassword.requestFocus();
                    }
                    if(!Validation.isValidPhone(phone)){
                        inputphone.setError("Enter Valid Phone no");
                        inputphone.requestFocus();
                    }
                    if(!Validation.isValidName(name)){
                        inputname.setError("Enter Valid Name");
                        inputname.requestFocus();
                    }
                } else {
                    pagetwo.setVisibility(View.VISIBLE);
                    pageone.setVisibility(View.GONE);
                }
        }
        if (v.getId() == R.id.inputprevious)
        {
            pageone.setVisibility(View.VISIBLE);
            pagetwo.setVisibility(View.GONE);
        }
        if (v.getId() == R.id.inputdob)
        {
            Calendar mcurrentDate=Calendar.getInstance();
            int mYear=mcurrentDate.get(Calendar.YEAR);
            int mMonth=mcurrentDate.get(Calendar.MONTH);
            int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog mDatePicker=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                    day=selectedday;
                    month=selectedmonth;
                    year=selectedyear;
                    inputdob.setText(getDate());
                }
            },mYear, mMonth, mDay);
            mDatePicker.getDatePicker().setMaxDate(new Date().getTime());
            mDatePicker.setTitle("Select date");
            mDatePicker.show();
        }
        if (v.getId() == R.id.inputsignup) {
            city=(String)inputcity.getSelectedItem();
            address=inputaddress.getText().toString().trim();
            pincode=inputpincode.getText().toString().trim();
            status=(String)marrystatus.getSelectedItem();
            if (male.isChecked())
            {
                gender=male.getText().toString();
            }
            else if (female.isChecked())
            {
                gender=female.getText().toString();
            }
            if (!male.isChecked() && !female.isChecked() ||status.equalsIgnoreCase("Select Marital Status") || ((String) inputcity.getSelectedItem()).equalsIgnoreCase("Select City") || TextUtils.isEmpty(address) || TextUtils.isEmpty((pincode)))
            {
                if (((String) marrystatus.getSelectedItem()).equalsIgnoreCase("Select Marital Status")) {
                    Toast.makeText(this, "Select Marital Status", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(pincode)){
                    inputpincode.setError("Enter Pincode");
                    inputpincode.requestFocus();
                }
                if (!male.isChecked()|| !female.isChecked())
                {
                    Toast.makeText(this, "Please select Gender", Toast.LENGTH_SHORT).show();
                    inputgender.requestFocus();
                }
                if (((String) inputcity.getSelectedItem()).equalsIgnoreCase("Select City"))
                    Toast.makeText(this, "Select City", Toast.LENGTH_SHORT).show();
                if (TextUtils.isEmpty(address)) {
                    inputaddress.setError("Enter Address");
                    inputaddress.requestFocus();
                }
            }
            else{
                jsonObject=new JSONObject();
                try {
                    jsonObject.put("type",type);
                    jsonObject.put("email",email);
                    jsonObject.put("name",name);
                    jsonObject.put("phone",phone);
                    jsonObject.put("dob",dob);
                    jsonObject.put("marriagestatus",status);
                    jsonObject.put("city",city);
                    jsonObject.put("permanentaddress",address);
                    jsonObject.put("pincode",pincode);
                    jsonObject.put("gender",gender);
                    jsonObject.put("password",password);
                    toserver();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    public void loginsuccess(){
        se.putString("type", type);
        se.putString("email", email);
        se.putString("name", name);
        se.putString("phone", phone);
        se.putString("dob", dob);
        se.putString("marriagestatus", status);
        se.putString("city", city);
        se.putString("permanentaddress", address);
        se.putString("pincode", pincode);
        se.putString("gender", gender);
        se.putString("password", password);
        se.putString("verified","0");
        se.commit();
        Intent intent=null;
            PROFILE profile = new PROFILE(email,name, phone, dob, status, city, address, pincode, gender, password, "0",type);
            if(type.equalsIgnoreCase("owner"))
                intent = new Intent(SignUpForm.this, OwnerProfile.class);
else
        intent = new Intent(SignUpForm.this, UserProfile.class);
            intent.putExtra("profile", profile);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            ActivityOptions options = ActivityOptions.makeCustomAnimation(SignUpForm.this, R.anim.fade_in, R.anim.fade_out);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }
    private void toserver() {
        showProgress(true);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url.URL_SIGNUP_FORM, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                showProgress(false);
                if(response.equalsIgnoreCase("success"))
                    loginsuccess();
                if(response.equalsIgnoreCase("error")){
                    AlertDialog builder = new AlertDialog.Builder(SignUpForm.this).create();
                    builder.setIcon(R.mipmap.ic_launcher_round);
                    builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                    builder.setMessage("Error in connection");
                    builder.show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                showProgress(false);
                AlertDialog builder = new AlertDialog.Builder(SignUpForm.this).create();
                builder.setIcon(R.mipmap.ic_launcher_round);
                builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                builder.setMessage("Connection error! Retry");
                builder.show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> params = new HashMap<>();
                params.put("data",jsonObject.toString());
                return params;
            }
        };
        MySingleton.getInstance(SignUpForm.this).addToRequestQueue(stringRequest);
    }
}
