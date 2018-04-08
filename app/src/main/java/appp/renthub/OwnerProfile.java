package appp.renthub;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.security.acl.Owner;

public class OwnerProfile extends Activity {


    android.app.Fragment fragment = null;
    android.app.FragmentTransaction ft;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_post:
                    fragment=new PostFrag();
                    switchFragment();
                    return true;
                case R.id.navigation_account:
                    fragment=new AccountFrag();
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_profile);
        Intent intent=getIntent();

/*
        Bundle bundle=new Bundle();
        bundle.putString("owneremail",email);*/
        fragment = new PostFrag();
       /* fragment.setArguments(bundle);*/
        switchFragment();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.ownernavigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
