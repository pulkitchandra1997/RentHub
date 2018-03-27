package appp.renthub;


import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignupOwner extends Activity implements View.OnClickListener {

    TextView usericon, mailicon, phoneicon, login;
    EditText ownername, ownermail, ownerphone, ownerpass;
    Button ownerSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_owner);
        ownername=findViewById(R.id.ownername);
        ownermail=findViewById(R.id.owneremail);
        ownerphone=findViewById(R.id.ownerphone);
        ownerSignup=findViewById(R.id.ownerSignup);
        login=findViewById(R.id.login);


        usericon=findViewById(R.id.usericon);
        mailicon=findViewById(R.id.mailicon);
        phoneicon=findViewById(R.id.phoneicon);


        Typeface font = Typeface.createFromAsset(getAssets(), "Font Awesome 5 Free-Solid-900.otf" );
        usericon.setTypeface(font);
        mailicon.setTypeface(font);
        phoneicon.setTypeface(font);


        ownerSignup.setOnClickListener(this);
        login.setOnClickListener(this);
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

    private boolean isValidPhone(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.ownerSignup) {
            String name= ownername.getText().toString().trim();
            String mail = ownermail.getText().toString().trim();
            String phone= ownerphone.getText().toString().trim();
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(mail) || TextUtils.isEmpty(phone)) {

                if (TextUtils.isEmpty(name)) {
                    ownername.setError("Enter name");
                    ownername.requestFocus();
                }

                if (TextUtils.isEmpty(mail)) {
                    ownermail.setError("Enter email");
                    ownermail.requestFocus();
                }

                if (TextUtils.isEmpty(phone)) {
                    ownerphone.setError("Enter phone number");
                    ownerphone.requestFocus();
                }

            }

            else if (!isValidName(name) || !isValidEmail(mail) || !isValidPhone(phone)) {

                if (!isValidName(name)) {
                    ownername.setError("Enter Correct Name");
                    ownername.requestFocus();
                }

                if (!isValidEmail(mail)) {
                    ownermail.setError("Enter Correct Email");
                    ownermail.requestFocus();
                }

                if (!isValidPhone(phone)) {
                    ownerphone.setError("Enter Correct Phone Number");
                    ownerphone.requestFocus();
                }

            }
                else {
                    Toast.makeText(this, name + mail + phone, Toast.LENGTH_SHORT).show();
                }
        }

        if(v.getId()==R.id.login){
            Intent intent = new Intent(SignupOwner.this, LoginActivity.class);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                ActivityOptions options = ActivityOptions.makeCustomAnimation(SignupOwner.this, R.anim.fade_in, R.anim.fade_out);
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }
    }
}
