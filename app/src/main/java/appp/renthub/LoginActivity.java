package appp.renthub;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends Activity implements View.OnClickListener{
EditText email;
EditText password;
TextView pwdicon,usericon;
Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);
        usericon=findViewById(R.id.usericon);
        pwdicon=findViewById(R.id.pwdicon);
        login=findViewById(R.id.login);
        login.setOnClickListener(this);
        Typeface font = Typeface.createFromAsset(getAssets(), "Font Awesome 5 Free-Solid-900.otf" );
        login.setTypeface(font);
        pwdicon.setTypeface(font);
        Typeface font2 = Typeface.createFromAsset(getAssets(), "Font Awesome 5 Free-Regular-400.otf" );
        usericon.setTypeface(font2);

    }
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isValidPhone(String email) {
        return android.util.Patterns.PHONE.matcher(email).matches();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login) {
            String emailid = email.getText().toString().trim();
            String pwd = password.getText().toString().trim();
            if (TextUtils.isEmpty(emailid) || TextUtils.isEmpty(pwd)) {
                if (TextUtils.isEmpty(emailid)) {
                    email.setError("Enter email id");
                    email.requestFocus();
                }
                if (TextUtils.isEmpty(pwd)) {
                    password.setError("Enter password");
                    password.requestFocus();
                }
            } else if (!isValidEmail(emailid)) {
                if (!isValidPhone(emailid)) {
                    email.setError("Enter correct Email or Phone Number");
                    email.requestFocus();
                } else {
                    Toast.makeText(this, emailid + pwd, Toast.LENGTH_SHORT).show();
                }
            }
        }
        if(v.getId()==R.id.signup){
            Intent intent = new Intent(LoginActivity.this, SignUp.class);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                ActivityOptions options = ActivityOptions.makeCustomAnimation(LoginActivity.this, R.anim.fade_in, R.anim.fade_out);
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }
        if(v.getId()==R.id.forgot){
            Toast.makeText(this, "FORGOT", Toast.LENGTH_SHORT).show();
        }
    }
}
