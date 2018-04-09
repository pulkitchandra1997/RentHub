package appp.renthub;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ForgotPasswordOtp extends Activity implements View.OnClickListener{
    String type=null,emailtext=null,otptext=null;
    int num;
    TextView usericon,otpicon,login,resendotp1,resendotp2;
    Button otp,signup;
    EditText emailinput;
    com.beardedhen.androidbootstrap.AwesomeTextView signuptype;
    com.chaos.view.PinView pinView;
    SharedPreferences sp;
    SharedPreferences.Editor se;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_otp);
        signuptype=findViewById(R.id.signuptype);
        usericon=findViewById(R.id.usericon);
        otpicon=findViewById(R.id.otpicon);
        otp=findViewById(R.id.otp);
        resendotp1=findViewById(R.id.resendotp1);
        resendotp2=findViewById(R.id.resendotp2);
        resendotp2.setOnClickListener(this);
        emailinput=findViewById(R.id.emailinput);
        login=findViewById(R.id.login);
        login.setOnClickListener(this);
        Typeface font = Typeface.createFromAsset(getAssets(), "Font Awesome 5 Free-Solid-900.otf" );
        usericon.setTypeface(font);
        otpicon.setTypeface(font);
        otp.setTypeface(font);
        signup=findViewById(R.id.signup);
        signup.setOnClickListener(this);
        signup.setTypeface(font);
        pinView=findViewById(R.id.pinView);
        pinView.setEnabled(false);
        otp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login) {
            Intent intent = new Intent(ForgotPasswordOtp.this, LoginActivity.class);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                ActivityOptions options = ActivityOptions.makeCustomAnimation(ForgotPasswordOtp.this, R.anim.fade_in, R.anim.fade_out);
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }

    }
}
