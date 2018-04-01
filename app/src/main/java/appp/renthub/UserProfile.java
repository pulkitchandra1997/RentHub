package appp.renthub;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class UserProfile extends Activity {

    android.app.Fragment fragment = null;
    android.app.FragmentTransaction ft;

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
        setContentView(R.layout.activity_user_profile);
        ft = getFragmentManager().beginTransaction();
        fragment = new SearchFrag();
        ft.replace(R.id.content, fragment);
        ft.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
