package appp.renthub;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pranj on 01-04-2018.
 */

public class ManageFrag extends Fragment {

    ListView listview;
    PROFILE profile;
    String email;
    RESULTADAPTER resultadapter;
    ArrayList<SEARCHRESULT> searchresults=new ArrayList<>();
    ProgressBar login_progress;

    @SuppressLint("ValidFragment")
    public ManageFrag(PROFILE profile) {
        this.profile = profile;
    }

    public ManageFrag() {
    }

    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {

            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);


            login_progress.setVisibility(show ? View.VISIBLE : View.GONE);
            login_progress.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    login_progress.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            login_progress.setVisibility(show ? View.VISIBLE : View.GONE);

        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.managefrag,container,false);

        listview=view.findViewById(R.id.listview);
        login_progress=view.findViewById(R.id.login_progress);


        email=profile.getEmail();
        searchemail(email);
        return view;
    }

    private void searchemail(final String email) {
        showProgress(true);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url.URL_PLACE_OWNED, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                 showProgress(false);
                if (response.toLowerCase().contains("searcherror")) {
                    AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
                    builder.setIcon(R.mipmap.ic_launcher_round);
                    builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                    builder.setMessage("Error in connection");
                    builder.show();
                } else {
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        int count=Integer.parseInt(jsonObject.getString("count").toString());
                        if(count>0){
                            while (count>0){
                                count--;
                                String data[]=jsonObject.get(String.valueOf(count)).toString().split("--%--");
                                searchresults.add(new SEARCHRESULT(data[0],data[1],data[2],data[3]));
                            }
                            resultadapter=new RESULTADAPTER(getActivity(),searchresults);
                            listview.setAdapter(resultadapter);
                            listview.setVisibility(View.VISIBLE);
                        }
                        else{
                            AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
                            builder.setIcon(R.mipmap.ic_launcher_round);
                            builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                            builder.setMessage("No results found.");
                            builder.show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                
                AlertDialog builder = new AlertDialog.Builder(getActivity()).create();
                builder.setIcon(R.mipmap.ic_launcher_round);
                builder.setTitle(Html.fromHtml("<font color='#FF0000'>RentZHub</font>"));
                builder.setMessage("Error in connection");
                builder.show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> params = new HashMap<>();
                params.put("email",email);
                return params;
            }
        };
        MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);

    }
}