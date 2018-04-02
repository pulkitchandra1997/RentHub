package appp.renthub;
        import android.app.Activity;
        import android.app.DatePickerDialog;
        import android.content.Intent;
        import android.graphics.Typeface;
        import android.os.Bundle;
        import android.text.TextUtils;
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

public class SignUpForm extends Activity implements View.OnClickListener {

    TextView login,emailicon,phoneicon,nameicon,dobicon,statusicon,cityicon,gendericon,addressicon,typeicon;
    EditText inputemail,inputphone,inputname,inputdob,inputaddress;
    Button inputsignup,inputnext,inputprevious;
    RadioGroup inputgender;
    LinearLayout pageone,pagetwo;
    int day,year,month;
    String name,phone,email,dob,status,city,gender,address,type;
    Spinner marrystatus,inputcity;
    RadioButton male,female;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);
        login=findViewById(R.id.login);
        login.setOnClickListener(this);
        inputsignup=findViewById(R.id.inputsignup);
        inputsignup.setOnClickListener(this);
        inputemail=findViewById(R.id.inputemail);
        inputaddress=findViewById(R.id.inputaddress);
        addressicon=findViewById(R.id.addressicon);
        inputname=findViewById(R.id.inputname);
        inputphone=findViewById(R.id.inputphone);
        inputdob=findViewById(R.id.inputdob);
        inputgender=findViewById(R.id.inputgender);
        marrystatus=(Spinner)findViewById(R.id.marrystatus);
        inputcity=findViewById(R.id.inputcity);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        emailicon=findViewById(R.id.emailicon);
        phoneicon=findViewById(R.id.phoneicon);
        nameicon=findViewById(R.id.nameicon);
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
        inputnext.setTypeface(font);
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
            inputemail.setEnabled(false);
            type=intent.getStringExtra("type");
            typeicon.setText(type);
        }
    }
    @Override
    public void onBackPressed()
    {
        this.startActivity(new Intent(SignUpForm.this,SignUp.class));
        return;
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
            status = (String) marrystatus.getSelectedItem();
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(dob) || status.equalsIgnoreCase("Select Marital Status")) {
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
                if (((String) marrystatus.getSelectedItem()).equalsIgnoreCase("Select Marital Status")) {
                    Toast.makeText(this, "Select Marital Status", Toast.LENGTH_SHORT).show();
                    marrystatus.requestFocus();
                    marrystatus.performClick();
                }
            } else {
                if(!Validation.isValidName(name)||!Validation.isValidPhone(phone)){
                    if(!Validation.isValidPhone(phone)){
                        inputphone.setError("Enter Valid Phone NO");
                        inputphone.requestFocus();
                    }
                    if(!Validation.isValidName(name)){
                        inputname.setError("Enter Valid Phone NO");
                        inputname.requestFocus();
                    }
                } else {
                    pagetwo.setVisibility(View.VISIBLE);
                    pageone.setVisibility(View.GONE);
                }
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
            mDatePicker.setTitle("Select date");
            mDatePicker.show();
        }
        if (v.getId() == R.id.inputsignup) {
            city=(String)inputcity.getSelectedItem();
            address=inputaddress.getText().toString().trim();
            if (male.isChecked())
            {
                gender=male.getText().toString();
            }
            else if (female.isChecked())
            {
                gender=female.getText().toString();
            }
            if (!male.isChecked() && !female.isChecked() || ((String) inputcity.getSelectedItem()).equalsIgnoreCase("Select City") || TextUtils.isEmpty(address))
            {
                if (!male.isChecked()|| !female.isChecked())
                {
                    Toast.makeText(this, "Please select Gender", Toast.LENGTH_SHORT).show();
                    inputgender.requestFocus();
                }
                if (((String) inputcity.getSelectedItem()).equalsIgnoreCase("Select City"))
                {
                    Toast.makeText(this, "Select City", Toast.LENGTH_SHORT).show();
                    inputcity.requestFocus();
                    inputcity.performClick();
                }
                if (TextUtils.isEmpty(address)) {
                    inputaddress.setError("Enter Address");
                    inputaddress.requestFocus();
                }
            }
        }
    }
}
