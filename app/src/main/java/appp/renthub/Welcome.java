    package appp.renthub;
            import android.app.Activity;
            import android.app.ActivityOptions;
            import android.content.Context;
            import android.content.DialogInterface;
            import android.content.Intent;
            import android.content.SharedPreferences;
            import android.content.pm.PackageManager;
            import android.net.ConnectivityManager;
            import android.net.NetworkInfo;
            import android.os.Build;
            import android.os.Handler;
            import android.support.design.widget.Snackbar;
            import android.support.v4.app.ActivityCompat;
            import android.support.v4.content.ContextCompat;
            import android.support.v7.app.AlertDialog;
            import android.os.Bundle;
            import android.text.Html;
            import android.view.View;
            import android.widget.Toast;
            import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
            import static android.Manifest.permission.ACCESS_FINE_LOCATION;
            import static android.Manifest.permission.ACCESS_NETWORK_STATE;
            import static android.Manifest.permission.CAMERA;
            import static android.Manifest.permission.INTERNET;
            import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

    public class Welcome extends Activity implements View.OnClickListener {
        android.support.v7.widget.AppCompatButton login, signup;
        private static final int PERMISSION_REQUEST_CODE = 200;
        SharedPreferences sp;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            haveNetworkConnection();
            checksp();
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_welcome);
            login = findViewById(R.id.login);
            login.setOnClickListener(this);
            signup = findViewById(R.id.signup);
            signup.setOnClickListener(this);
            checkPermission();
        }


        //CHECK FOR LOCALSTORAGE OF PROFILE
        private void checksp() {
            sp=getSharedPreferences("RentHub_data",MODE_PRIVATE);
            if(sp!=null){
                String type=sp.getString("type",null);
                if(type!=null) {
                    Intent intent=null;
                    PROFILE profile = new PROFILE(
                            sp.getString("email", null),sp.getString("name", null),
                            sp.getString("phone", null),
                            sp.getString("dob", null),
                            sp.getString("marriagestatus", null),
                            sp.getString("city", null),
                            sp.getString("permanentaddress", null),
                            sp.getString("pincode", null),
                            sp.getString("gender", null),
                            sp.getString("password", null),
                            sp.getString("verified",null),type);
                    if (type.equalsIgnoreCase("owner"))
                        intent = new Intent(Welcome.this, OwnerProfile.class);
                    else
                            intent = new Intent(Welcome.this, UserProfile.class);
                    intent.putExtra("profile",profile);
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        ActivityOptions options = ActivityOptions.makeCustomAnimation(Welcome.this, R.anim.fade_in, R.anim.fade_out);
                        startActivity(intent, options.toBundle());
                    } else {
                        startActivity(intent);
                    }
                }
            }
        }


        //ONBACKPRESS-CLOSE APP
        boolean doubleBackToExitPressedOnce = false;
        @Override
        public void onBackPressed() {
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


        //PERMISSION CHECK AND ASK FOR IT
        private void checkPermission() {
            int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
            int result2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
            if (result1 == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{CAMERA,READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            }
        }
        @Override
        public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
            switch (requestCode) {
                case PERMISSION_REQUEST_CODE:
                    if (grantResults.length > 0) {
                        boolean externalstorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                        boolean camera = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                        if (camera && externalstorage)
                        {}
                        else {
                            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                            alertDialog.setMessage("Permission Denied");
                            alertDialog.setIcon(R.mipmap.ic_launcher_round);
                            alertDialog.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                            alertDialog.show();
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                if (shouldShowRequestPermissionRationale(CAMERA)) {
                                    showMessageOKCancel("You need to allow access to all the permissions",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                        requestPermissions(new String[]{CAMERA,READ_EXTERNAL_STORAGE},
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
                    .setIcon(R.mipmap.ic_launcher_round)
                    .setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"))
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
        //INTERNET CONNECTION CHECK
        private void haveNetworkConnection() {
            boolean haveConnectedWifi = false;
            boolean haveConnectedMobile = false;
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo[] netInfo = cm.getAllNetworkInfo();
            for (NetworkInfo ni : netInfo) {
                if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                    if (ni.isConnected())
                        haveConnectedWifi = true;
                if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                    if (ni.isConnected())
                        haveConnectedMobile = true;
            }
            if( !haveConnectedWifi && !haveConnectedMobile)
            {
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setMessage("No Internet Connection");
                alertDialog.setIcon(R.mipmap.ic_launcher_round);
                alertDialog.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                alertDialog.show();
            }
        }
    }