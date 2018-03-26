package appp.renthub;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
public class WelcomeScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(WelcomeScreen.this,Welcome.class);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    ActivityOptions options = ActivityOptions.makeCustomAnimation(WelcomeScreen.this, R.anim.fade_in, R.anim.fade_out);
                    startActivity(intent, options.toBundle());
                }
                else{
                    startActivity(intent);
                }
                finish();
            }
        }, 4000);
    }
}
