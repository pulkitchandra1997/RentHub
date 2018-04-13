package appp.renthub;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.Serializable;

public class OwnerProfile extends Activity {


    android.app.Fragment fragment = null;
    android.app.FragmentTransaction ft;
    PROFILE profile;
    int flag;
    BottomNavigationView navigation;


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
            navigation.setSelectedItemId(R.id.navigation_post);
            switchFragment();
            return;
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_post:
                    fragment=new PostFrag(profile);
                    flag=0;
                    switchFragment();
                    return true;
                case R.id.navigation_account:
                    fragment=new AccountFrag(profile);
                    flag=3;
                    switchFragment();
                    return true;
                case R.id.navigation_manage:
                    fragment=new ManageFrag(profile);
                    flag=2;
                    switchFragment();
                    return true;
                case R.id.navigation_messages:
                    fragment=new MessageFrag();
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_profile);
        Intent intent=getIntent();
        profile= (PROFILE)intent.getSerializableExtra("profile");
/*
        Bundle bundle=new Bundle();
        bundle.putString("owneremail",email);*/
        fragment = new PostFrag(profile);
        flag=0;
       /* fragment.setArguments(bundle);*/
        switchFragment();

         navigation = (BottomNavigationView) findViewById(R.id.ownernavigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
