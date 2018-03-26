package appp.renthub;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends Activity implements View.OnClickListener{
EditText email;
EditText password;
Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);
        login=findViewById(R.id.login);
        login.setOnClickListener(this);
        Typeface font = Typeface.createFromAsset(getAssets(), "Font Awesome 5 Free-Solid-900.otf" );
        login.setTypeface(font);

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
    public void signup(View view) {
    }

    public void forgot(View view) {
    }

    public void login(View view) {


    }

    @Override
    public void onClick(View v) {
        String emailid=email.getText().toString().trim();
        String pwd=password.getText().toString().trim();
        if (TextUtils.isEmpty(emailid) || TextUtils.isEmpty(pwd)) {
            if (TextUtils.isEmpty(emailid)) {
                email.setError("Enter email id");
                email.requestFocus();
            }
            if (TextUtils.isEmpty(pwd)) {
                password.setError("Enter password");
                password.requestFocus();
            }
        }else
        if (!isValidEmail(emailid)) {
            if (!isValidPhone(emailid)) {
                email.setError("Enter correct Email or Phone Number");
                email.requestFocus();
            }else
            {
                Toast.makeText(this, emailid+pwd, Toast.LENGTH_SHORT).show();
            }
        }


    }
}
