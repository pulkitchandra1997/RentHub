package appp.renthub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.view.MenuItem;
import android.widget.Toast;

import java.io.Serializable;

public class UserProfile extends Activity {

    android.app.Fragment fragment = null;
    android.app.FragmentTransaction ft;
    PROFILE profile;
    int flag;
    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            switch (item.getItemId()) {
                case R.id.navigation_search:
                    fragment=new SearchFrag();
                    flag=0;
                    switchFragment();
                    return true;
                case R.id.navigation_account:
                    fragment=new AccountFrag(profile);
                    flag=3;
                    switchFragment();
                    return true;
                case R.id.navigation_manage:
                    fragment=new UserManageFrag();
                    flag=2;
                    switchFragment();
                    return true;
                case R.id.navigation_messages:
                    fragment=new MessageFrag(profile);
                    flag=1;
                    switchFragment();
                    return true;
            }
            return false;

        }
    };

    public void switchFragment()
    {
        ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        ft.commit();
    }


    //ONBACKPRESS-CLOSE APP
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if(flag==0){
            if (doubleBackToExitPressedOnce) {
                Intent a = new Intent(Intent.ACTION_MAIN);
                a.addCategory(Intent.CATEGORY_HOME);
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(a);
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }
        else{
            fragment=new PostFrag(profile);
            flag=0;
            navigation.setSelectedItemId(R.id.navigation_search);
            switchFragment();
            return;
        }    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Intent intent=getIntent();
        profile=(PROFILE) intent.getSerializableExtra("profile");
        fragment = new SearchFrag();
        flag=0;
        switchFragment();

         navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
