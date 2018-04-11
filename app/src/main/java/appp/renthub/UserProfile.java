package appp.renthub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.view.MenuItem;

import java.io.Serializable;

public class UserProfile extends Activity {

    android.app.Fragment fragment = null;
    android.app.FragmentTransaction ft;
    PROFILE profile;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            switch (item.getItemId()) {
                case R.id.navigation_search:
                    fragment=new SearchFrag();
                    switchFragment();
                    return true;
                case R.id.navigation_account:
                    fragment=new AccountFrag(profile);
                    switchFragment();
                    return true;
                case R.id.navigation_manage:
                    fragment=new ManageFrag();
                    switchFragment();
                    return true;
                case R.id.navigation_messages:
                    fragment=new MessageFrag();
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
    public void onBackPressed()
    {
        this.startActivity(new Intent(UserProfile.this,LoginActivity.class));
        return;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Intent intent=getIntent();
        profile=(PROFILE) intent.getSerializableExtra("profile");
        fragment = new SearchFrag();
        switchFragment();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
