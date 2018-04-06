package appp.renthub;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
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
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity implements View.OnClickListener{
    EditText email;
    EditText password;
    TextView pwdicon,usericon,forgot,signup;
    Button login;
    SharedPreferences sp;
    SharedPreferences.Editor se;
    String typevalue,emailvalue,namevalue,phonevalue,dobvalue,marriagestatusvalue,cityvalue,permanentaddressvalue,pincodevalue,gendervalue,passwordvalue,verifiedvalue;
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
        signup=findViewById(R.id.signup);
        signup.setOnClickListener(this);
        forgot=findViewById(R.id.forgot);
        forgot.setOnClickListener(this);
        sp=getSharedPreferences("RentHub_data",MODE_PRIVATE);
        se=sp.edit();
    }
    /*    @Override
        public void onBackPressed()
        {
            this.startActivity(new Intent(LoginActivity.this,Welcome.class));
            return;
        }*/
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login) {
            final String emailid = email.getText().toString().trim();
            final String pwd = password.getText().toString().trim();
            if (TextUtils.isEmpty(emailid) || TextUtils.isEmpty(pwd)) {
                if (TextUtils.isEmpty(emailid)) {
                    email.setError("Enter email id");
                    email.requestFocus();
                }
                if (TextUtils.isEmpty(pwd)) {
                    password.setError("Enter password");
                    password.requestFocus();
                }
            } else{
                if(!Validation.isValidEmail(emailid)&&!Validation.isValidPhone(emailid)){
                    email.setError("Enter Valid Email/Phone no");
                    email.requestFocus();
                }
                else{
                    JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST,Url.URL_LOGIN,null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    if (response.toString().equalsIgnoreCase("error")) {
                                        Toast.makeText(LoginActivity.this, "error", Toast.LENGTH_SHORT).show();
                                    } else {
                                        try {
                                            JSONArray array = response.getJSONArray("userdetails");
                                            JSONObject userdetails = array.getJSONObject(0);
                                            typevalue = userdetails.getString("type");
                                            emailvalue = userdetails.getString("email");
                                            namevalue = userdetails.getString("name");
                                            phonevalue = userdetails.getString("phone");
                                            dobvalue = userdetails.getString("dob");
                                            marriagestatusvalue = userdetails.getString("marriagestatus");
                                            cityvalue = userdetails.getString("city");
                                            permanentaddressvalue = userdetails.getString("permanentaddress");
                                            pincodevalue = userdetails.getString("pincode");
                                            gendervalue = userdetails.getString("gender");
                                            passwordvalue = userdetails.getString("password");
                                            verifiedvalue = userdetails.getString("verified");
                                            se.putString("type", typevalue);
                                            se.putString("email", emailvalue);
                                            se.putString("name", namevalue);
                                            se.putString("phone", phonevalue);
                                            se.putString("dob", dobvalue);
                                            se.putString("marriagestatus", marriagestatusvalue);
                                            se.putString("city", cityvalue);
                                            se.putString("permanentaddress", permanentaddressvalue);
                                            se.putString("pincode", pincodevalue);
                                            se.putString("gender", gendervalue);
                                            se.putString("password", passwordvalue);
                                            se.putString("verifystatus", verifiedvalue);
                                            se.commit();
                                            Intent intent;
                                            if (typevalue.equalsIgnoreCase("owner")) {
                                                OWNER owner = new OWNER(namevalue, phonevalue, dobvalue, marriagestatusvalue, cityvalue, permanentaddressvalue, pincodevalue, gendervalue, passwordvalue, verifiedvalue);
                                                intent = new Intent(LoginActivity.this, OwnerProfile.class);
                                                intent.putExtra("profile", owner);
                                            } else {
                                                USER user = new USER(namevalue, phonevalue, dobvalue, marriagestatusvalue, cityvalue, permanentaddressvalue, pincodevalue, gendervalue, passwordvalue, verifiedvalue);
                                                intent = new Intent(LoginActivity.this, UserProfile.class);
                                                intent.putExtra("profile", user);
                                            }
                                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                                                ActivityOptions options = ActivityOptions.makeCustomAnimation(LoginActivity.this, R.anim.fade_in, R.anim.fade_out);
                                                startActivity(intent, options.toBundle());
                                            } else {
                                                startActivity(intent);
                                            }
                                        }catch(JSONException e){
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                },
                            new Response.ErrorListener(){
                                @Override
                                public void onErrorResponse(VolleyError error){
                                    // Do something when error occurred
                                    Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError
                        {
                            Map<String, String> params = new HashMap<>();
                            params.put("email", emailid);
                            params.put("password", pwd);
                            return params;
                        }
                    };
                    MySingleton.getInstance(LoginActivity.this).addToRequestQueue(jsonObjectRequest);
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
