package appp.renthub;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignIn extends Activity{
   // Button tenant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
       // tenant=findViewById(R.id.login);
      //  tenant.setOnClickListener(this);
       // Typeface font = Typeface.createFromAsset(getAssets(), "Font Awesome 5 Free-Solid-900.otf" );
       // tenant.setTypeface(font);
    }

    public void tenant(View view) {
    }

    public void owner(View view) {
    }


}
