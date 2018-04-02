package appp.renthub;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chaos.view.PinView;

public class SignUpVerify extends Activity implements View.OnClickListener {
    String type=null,emailtext=null,otptext=null;
    TextView usericon,otpicon,login;
    Button otp;
    EditText otpinput,emailinput;
    com.beardedhen.androidbootstrap.AwesomeTextView signuptype;
    com.chaos.view.PinView pinView;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_verify);
        signuptype=findViewById(R.id.signuptype);
        usericon=findViewById(R.id.usericon);
        otpicon=findViewById(R.id.otpicon);
        otp=findViewById(R.id.otp);
        emailinput=findViewById(R.id.emailinput);
        login=findViewById(R.id.login);
        Typeface font = Typeface.createFromAsset(getAssets(), "Font Awesome 5 Free-Solid-900.otf" );
        usericon.setTypeface(font);
        otpicon.setTypeface(font);
        otp.setTypeface(font);
        pinView=findViewById(R.id.pinView);
        pinView.setEnabled(false);
        Intent intent=getIntent();
        if(intent!=null){
            type=intent.getStringExtra("type");
            signuptype.setText("as "+type);
        }
    }
    @Override
    public void onBackPressed()
    {
        this.startActivity(new Intent(SignUpVerify.this,SignUp.class));
        return;
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.login){
            Intent intent = new Intent(SignUpVerify.this, LoginActivity.class);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                ActivityOptions options = ActivityOptions.makeCustomAnimation(SignUpVerify.this, R.anim.fade_in, R.anim.fade_out);
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }
        if(v.getId()==R.id.otp) {
            if (flag == 0) {
                emailtext = emailinput.getText().toString().trim();
                if (TextUtils.isEmpty(emailtext)) {
                    emailinput.setError("Enter Email ID");
                    emailinput.requestFocus();
                } else {
                    if (!Validation.isValidEmail(email)) {
                        owneremail.setError("Enter Correct Email");


                    }
            /*
            CODE for sending OTP
             */
            /*
            IF success
             */

                }
            }
        }
    }
}
