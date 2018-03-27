package appp.renthub;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignupTenant extends Activity   implements View.OnClickListener {
    TextView login,emailicon,phoneicon,nameicon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_tenant);
        login=findViewById(R.id.login);
        login.setOnClickListener(this);
        emailicon=findViewById(R.id.emailicon);
        phoneicon=findViewById(R.id.phoneicon);
        nameicon=findViewById(R.id.nameicon);
        Typeface font = Typeface.createFromAsset(getAssets(), "Font Awesome 5 Free-Solid-900.otf" );
        nameicon.setTypeface(font);
        emailicon.setTypeface(font);
        phoneicon.setTypeface(font);
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

    }
}
