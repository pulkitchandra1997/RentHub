    package appp.renthub;
            import android.app.Activity;
            import android.app.ActivityOptions;
            import android.content.DialogInterface;
            import android.content.Intent;
            import android.content.SharedPreferences;
            import android.content.pm.PackageManager;
            import android.os.Build;
            import android.os.Handler;
            import android.support.design.widget.Snackbar;
            import android.support.v4.app.ActivityCompat;
            import android.support.v4.content.ContextCompat;
            import android.support.v7.app.AlertDialog;
            import android.os.Bundle;
            import android.view.View;
            import android.widget.Toast;

            import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
            import static android.Manifest.permission.ACCESS_FINE_LOCATION;
    public class Welcome extends Activity implements View.OnClickListener {
        android.support.v7.widget.AppCompatButton login, signup;
        private static final int PERMISSION_REQUEST_CODE = 200;

        SharedPreferences sp;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            checksp();
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_welcome);
            login = findViewById(R.id.login);
            login.setOnClickListener(this);
            signup = findViewById(R.id.signup);
            signup.setOnClickListener(this);
            checkPermission();
        }

        private void checksp() {
            sp=getSharedPreferences("RentHub_data",MODE_PRIVATE);
            if(sp!=null){
                String type=sp.getString("type",null);
                String username=sp.getString("username",null);
                String name=sp.getString("name",null);
                String mobileno=sp.getString("mobileno",null);
                if(type.equalsIgnoreCase("owner")){

                }
                else {
                    if (type.equalsIgnoreCase("tenant")){

                    }
                }
            }
        }

        boolean doubleBackToExitPressedOnce = false;

        @Override
        public void onBackPressed() {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
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

        private void checkPermission() {
            int result = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
            int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_COARSE_LOCATION);
            if (result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_CODE);
            }
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
            switch (requestCode) {
                case PERMISSION_REQUEST_CODE:
                    if (grantResults.length > 0) {
                        boolean fineLocationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                        boolean courseLocationAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                        if (fineLocationAccepted && courseLocationAccepted)
                            Snackbar.make(getWindow().getDecorView().getRootView(), "Permission Granted, Now you can access location data and camera.", Snackbar.LENGTH_LONG).show();
                        else {
                            Snackbar.make(getWindow().getDecorView().getRootView(), "Permission Denied, You cannot access location data and camera.", Snackbar.LENGTH_LONG).show();
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                                    showMessageOKCancel("You need to allow access to both the permissions",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                        requestPermissions(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION},
                                                                PERMISSION_REQUEST_CODE);
                                                    }
                                                }
                                            });
                                    return;
                                }
                            }

                        }
                    }
                    break;
            }
        }

        private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
            new AlertDialog.Builder(Welcome.this)
                    .setMessage(message)
                    .setPositiveButton("OK", okListener)
                    .setNegativeButton("Cancel", null)
                    .create()
                    .show();
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.login) {
                Intent intent = new Intent(Welcome.this, LoginActivity.class);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    ActivityOptions options = ActivityOptions.makeCustomAnimation(Welcome.this, R.anim.fade_in, R.anim.fade_out);
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }

            if (v.getId() == R.id.signup) {
                Intent intent = new Intent(Welcome.this, SignUp.class);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    ActivityOptions options = ActivityOptions.makeCustomAnimation(Welcome.this, R.anim.fade_in, R.anim.fade_out);
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }
        }
    }