package appp.renthub;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ComposeMessage extends AppCompatActivity implements View.OnClickListener{

    EditText sendermail,receivermail,message;
    Button send;
    String sender,receiver,msg;
    JSONObject jsonObject;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_message);

        sp=getSharedPreferences("RentHub_data",MODE_PRIVATE);

        Intent intent=getIntent();
        receiver=intent.getStringExtra("receivermail");

        sender=sp.getString("email","email");


        sendermail=findViewById(R.id.sendermail);
        receivermail=findViewById(R.id.receivermail);
        message=findViewById(R.id.msg);
        send=findViewById(R.id.send);

        send.setOnClickListener(this);

        sendermail.setText(sender);
        receivermail.setText(receiver);

        message.requestFocus();

    }

    public void clearform() {
        message.setText("");
        sendermail.setText("");
        receivermail.setText("");

    }

    @Override
    public void onClick(View v) {

        msg = message.getText().toString().trim();


        if (TextUtils.isEmpty(msg)) {
            message.setError("Message cannot be empty!");
            message.requestFocus();
        } else {
            String receiver1 = receivermail.getText().toString();
            String sender1 = sendermail.getText().toString();
            jsonObject = new JSONObject();

            try {
                jsonObject.put("sender", sender1);
                jsonObject.put("receiver", receiver);
                jsonObject.put("message", msg);


            } catch (JSONException e) {
                e.printStackTrace();
            }
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Url.URL_POST_MSG, new Response.Listener <String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("success")) {
                        clearform();
                        AlertDialog builder = new AlertDialog.Builder(ComposeMessage.this).create();
                        builder.setIcon(R.mipmap.ic_launcher_round);
                        builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                        builder.setMessage("Your message has been sent.");
                        builder.show();
                        // Setting image as transparent after done uploading.
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                finish();
                            }
                        }, 2500);
                    } else {

                        if (response.toLowerCase().contains("formerror1")) {
                            AlertDialog builder = new AlertDialog.Builder(ComposeMessage.this).create();
                            builder.setIcon(R.mipmap.ic_launcher_round);
                            builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                            builder.setMessage("Server Error!");
                            builder.show();
                        }
                        if (response.toLowerCase().contains("formerror2")) {
                            AlertDialog builder = new AlertDialog.Builder(ComposeMessage.this).create();
                            builder.setIcon(R.mipmap.ic_launcher_round);
                            builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                            builder.setMessage("Internet Error.Check Connection!");
                            builder.show();
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    AlertDialog builder = new AlertDialog.Builder(ComposeMessage.this).create();
                    builder.setIcon(R.mipmap.ic_launcher_round);
                    builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                    builder.setMessage("Connection error!");
                    builder.show();
                }
            }) {
                @Override
                protected Map <String, String> getParams() throws AuthFailureError {
                    Map <String, String> params = new HashMap <>();
                    params.put("data", jsonObject.toString());
                    return params;
                }
            };
            MySingleton.getInstance(ComposeMessage.this).addToRequestQueue(stringRequest);
        }
    }
}

