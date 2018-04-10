package appp.renthub;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class ForgotPasswordOtp extends Activity implements View.OnClickListener {
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
        setContentView(R.layout.activity_sign_up_verify);
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
        Intent intent=getIntent();
        if(intent!=null){
            type=intent.getStringExtra("type");
            signuptype.setText("as "+type);
        }
        sp=getSharedPreferences("RentHub_data",MODE_PRIVATE);
        se=sp.edit();
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.otp) {
            emailinput.clearFocus();
            emailtext = emailinput.getText().toString().trim();
            if (TextUtils.isEmpty(emailtext)) {
                emailinput.setError("Enter Email ID");
                emailinput.requestFocus();
            } else {
                if (!Validation.isValidEmail(emailtext)) {
                    emailinput.setError("Enter Correct Email");
                    emailinput.requestFocus();
                }
                else{
                    sendotp();
                    pinView.setEnabled(true);
                    pinView.requestFocus();
                }
            }
        }
        if(v.getId()==R.id.signup){
            otptext=pinView.getText().toString().trim();
            pinView.clearFocus();
            if(TextUtils.isEmpty(otptext)){
                pinView.setError("Enter OTP");
                pinView.requestFocus();
            }
            else
                checkotp();
        }
        if(v.getId()==R.id.resendotp2){
            sendotp();
        }
    }
    private void checkotp() {
        if(num!=0 || sp!=null) {
            int num2=sp.getInt("otp_sent",0);
            if(Integer.valueOf(otptext)==num2 || Integer.valueOf(otptext)==num){
                se.remove("otp_sent");
                se.commit();
                Intent intent = new Intent(ForgotPasswordOtp.this, ConfirmPassword.class);
                intent.putExtra("email",emailtext);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    ActivityOptions options = ActivityOptions.makeCustomAnimation(ForgotPasswordOtp.this, R.anim.fade_in, R.anim.fade_out);
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }
            else{
                pinView.setError("Incorrect OTP");
                pinView.requestFocus();
            }
        }
    }
    private void sendotp() {
        num=OTP_GENERATION.generateRandomNumber();
        Toast.makeText(this, String.valueOf(num), Toast.LENGTH_SHORT).show();
        se.putInt("otp_sent", num);
        se.commit();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url.URL_SEND_OTP, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                if(response.equalsIgnoreCase("1") ){
                    if (otp.getVisibility() == View.VISIBLE) {
                        otp.setVisibility(View.GONE);
                        signup.setVisibility(View.VISIBLE);
                        resendotp1.setVisibility(View.VISIBLE);
                        new CountDownTimer(120000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                long min, sec;
                                if (millisUntilFinished > 60000) {
                                    min = millisUntilFinished / 60000;
                                    sec = (millisUntilFinished - (min * 60000)) / 1000;
                                    resendotp1.setText("Resend Otp in " + min + " min " + sec + " sec");
                                } else {
                                    resendotp1.setText("Resend Otp in " + millisUntilFinished / 1000 + " sec");
                                }
                            }

                            public void onFinish() {
                                resendotp1.setVisibility(View.GONE);
                                resendotp2.setVisibility(View.VISIBLE);
                            }
                        }.start();
                    } else {
                        resendotp1.setVisibility(View.VISIBLE);
                    }
                }
                else{
                    if(response.equalsIgnoreCase("0")) {
                        Snackbar snackbar = Snackbar
                                .make(getWindow().getDecorView().getRootView(), "Email already Registered.", Snackbar.LENGTH_LONG);
                        View sbView = snackbar.getView();
                        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
                        textView.setTextColor(Color.RED);
                        snackbar.setAction("Login",new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(SignUpVerify.this, LoginActivity.class);
                                intent.putExtra("email",emailtext);
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                                    ActivityOptions options = ActivityOptions.makeCustomAnimation(SignUpVerify.this, R.anim.fade_in, R.anim.fade_out);
                                    startActivity(intent, options.toBundle());
                                } else {
                                    startActivity(intent);
                                }
                            }
                        });
                        snackbar.show();
                    }
                    if(response.equalsIgnoreCase("error")){
                        Snackbar snackbar = Snackbar
                                .make(getWindow().getDecorView().getRootView(), "Error in sending OTP. Retry!", Snackbar.LENGTH_LONG);
                        View sbView = snackbar.getView();
                        TextView textView =sbView.findViewById(android.support.design.R.id.snackbar_text);
                        textView.setTextColor(Color.RED);
                        snackbar.show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Snackbar snackbar = Snackbar
                        .make(getWindow().getDecorView().getRootView(), "Error in sending OTP. Retry!", Snackbar.LENGTH_LONG);
                View sbView = snackbar.getView();
                TextView textView =sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.RED);
                snackbar.show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> params = new HashMap<>();
                params.put("email", emailtext);
                params.put("otp", String.valueOf(num));
                return params;
            }
        };
        MySingleton.getInstance(SignUpVerify.this).addToRequestQueue(stringRequest);
    }
}