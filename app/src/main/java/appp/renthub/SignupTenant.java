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

public class SignupTenant extends Activity   implements View.OnClickListener {
    TextView login,emailicon,phoneicon,nameicon;
    EditText tenantemail,tenantphone,tenantname;
    Button tenantsignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_tenant);
        login=findViewById(R.id.login);
        login.setOnClickListener(this);
        tenantsignup=findViewById(R.id.tenantsignup);
        tenantsignup.setOnClickListener(this);
        tenantemail=findViewById(R.id.tenantemail);
        tenantname=findViewById(R.id.tenantname);
        tenantphone=findViewById(R.id.tenantphone);
        emailicon=findViewById(R.id.emailicon);
        phoneicon=findViewById(R.id.phoneicon);
        nameicon=findViewById(R.id.nameicon);
        Typeface font = Typeface.createFromAsset(getAssets(), "Font Awesome 5 Free-Solid-900.otf" );
        nameicon.setTypeface(font);
        emailicon.setTypeface(font);
        phoneicon.setTypeface(font);
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
        if(v.getId()==R.id.login){
            Intent intent = new Intent(SignupTenant.this, LoginActivity.class);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                ActivityOptions options = ActivityOptions.makeCustomAnimation(SignupTenant.this, R.anim.fade_in, R.anim.fade_out);
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }
        if (v.getId() == R.id.tenantsignup) {
            String name= tenantname.getText().toString().trim();
            String mail = tenantemail.getText().toString().trim();
            String phone= tenantphone.getText().toString().trim();
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(mail) || TextUtils.isEmpty(phone)) {

                if (TextUtils.isEmpty(name)) {
                    tenantname.setError("Enter name");
                    tenantname.requestFocus();
                }

                if (TextUtils.isEmpty(mail)) {
                    tenantemail.setError("Enter email");
                    tenantemail.requestFocus();
                }

                if (TextUtils.isEmpty(phone)) {
                    tenantphone.setError("Enter phone number");
                    tenantphone.requestFocus();
                }

            }

            else if (!isValidName(name) || !isValidEmail(mail) || !isValidPhone(phone)) {

                if (!isValidName(name)) {
                    tenantname.setError("Enter Correct Name");
                    tenantname.requestFocus();
                }

                if (!isValidEmail(mail)) {
                    tenantemail.setError("Enter Correct Email");
                    tenantemail.requestFocus();
                }

                if (!isValidPhone(phone)) {
                    tenantphone.setError("Enter Correct Phone Number");
                    tenantphone.requestFocus();
                }

            }
            else {
                Toast.makeText(this, name + mail + phone, Toast.LENGTH_SHORT).show();
            }
        }


    }
}