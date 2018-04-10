package appp.renthub;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmPassword extends Activity implements View.OnClickListener{
    EditText oldpassword,newpassword;
    TextView pwdicon,pwdicon1;
    Button change;
    String newpwd,oldpwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_password);
        oldpassword=findViewById(R.id.oldpassword);
        newpassword=findViewById(R.id.newpassword);
        pwdicon=findViewById(R.id.pwdicon);
        pwdicon1=findViewById(R.id.pwdicon1);
        change=findViewById(R.id.change);
        change.setOnClickListener(this);
        Typeface font = Typeface.createFromAsset(getAssets(), "Font Awesome 5 Free-Solid-900.otf" );
        change.setTypeface(font);
        pwdicon.setTypeface(font);
        pwdicon1.setTypeface(font);
        change.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.change) {

         oldpwd = oldpassword.getText().toString().trim();
         newpwd = newpassword.getText().toString().trim();
            if (TextUtils.isEmpty(oldpwd) || TextUtils.isEmpty(newpwd)) {
                if (TextUtils.isEmpty(oldpwd)) {
                    oldpassword.setError("Enter Your Password");
                    oldpassword.requestFocus();
                }
                if (TextUtils.isEmpty(newpwd)) {
                    newpassword.setError("Confirm Your password");
                    newpassword.requestFocus();
                }
            } else

                if(!Validation.isValidPassword(oldpwd)|| !Validation.isValidPassword(newpwd)||!oldpwd.equals(newpwd)){
                    if(!Validation.isValidPassword(oldpwd)){
                        oldpassword.setError("Password must be alphanumeric & 8 characters");
                        oldpassword.requestFocus();

                    }
                    if(!Validation.isValidPassword(newpwd)){
                        newpassword.setError("Re-Type Correct password");
                        newpassword.requestFocus();

                    }
                    if(!oldpwd.equals(newpwd)){
                        newpassword.setError("Re-Type Correct password");
                        newpassword.requestFocus();

                    }
                }
                else {
                    Toast.makeText(this, ""+oldpwd+newpwd, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
