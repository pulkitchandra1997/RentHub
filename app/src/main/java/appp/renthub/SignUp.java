package appp.renthub;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUp extends Activity implements View.OnClickListener{

    Button tenant,owner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        tenant=findViewById(R.id.tenant);
        owner=findViewById(R.id.owner);
        Typeface font = Typeface.createFromAsset(getAssets(), "Font Awesome 5 Free-Solid-900.otf" );
        owner.setTypeface(font);
        tenant.setTypeface(font);
        tenant=findViewById(R.id.tenant);
        owner=findViewById(R.id.owner);
        tenant.setOnClickListener(this);
        owner.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.tenant){
            Intent intent = new Intent(SignUp.this, SignupTenant.class);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                ActivityOptions options = ActivityOptions.makeCustomAnimation(SignUp.this, R.anim.fade_in, R.anim.fade_out);
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }
        if(v.getId()==R.id.owner){
            Intent intent = new Intent(SignUp.this, SignupOwner.class);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                ActivityOptions options = ActivityOptions.makeCustomAnimation(SignUp.this, R.anim.fade_in, R.anim.fade_out);
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }
    }
}
