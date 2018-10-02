package appp.renthub;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.os.Build.VERSION_CODES.JELLY_BEAN;

/**
 * Created by pranj on 01-04-2018.
 */

public class SearchFrag extends Fragment implements AdapterView.OnItemClickListener,SearchView.OnQueryTextListener,View.OnClickListener{
    ListView list,searchlist;
    ListViewAdapter adapter;
    SearchView editsearch;
    String[] cityNameList;
    ArrayList<CityNames> arraylist = new ArrayList <CityNames>();
    ArrayList<SEARCHRESULT> searchresults=new ArrayList<>();
    RESULTADAPTER resultadapter;
    ProgressBar login_progress;
    Button searchproperty;
    LinearLayout searchcard;
    RelativeLayout searchcontent;
    String address;
    FragmentTransaction ft;




    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {

            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            searchlist.setVisibility(show ? View.GONE : View.VISIBLE);
            searchlist.animate().setDuration(500).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    searchlist.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

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
            searchlist.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.searchfrag,container,false);
        searchproperty=v.findViewById(R.id.searchproperty);
        searchcard=v.findViewById(R.id.searchcard);
        searchcontent=v.findViewById(R.id.searchcontent);


        searchproperty.setOnClickListener(this);

        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "Font Awesome 5 Free-Solid-900.otf");

        searchproperty.setTypeface(font);



        cityNameList = new String[]{"Lucknow", "Kanpur",
                "Delhi", "Gurgaon", "Mumbai", "Pune", "Kolkata", "Chennai", "Bangalore", "Ahmedabad", "Hyderabad",
                "Jaipur", "Ghaziabad", "Noida", "Patna", "Chandigarh", "Allahabad"};

        // Locate the ListView in listview_main.xml
        list =  v.findViewById(R.id.listview);

        for (int i = 0; i < cityNameList.length; i++) {
            CityNames cityNames = new CityNames(cityNameList[i]);
            // Binds all strings into an array
            arraylist.add(cityNames);
        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(getActivity(), arraylist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) v.findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

        searchlist=v.findViewById(R.id.searchlist);
        searchlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), PlaceDetails.class);
                intent.putExtra("searchresult",searchresults.get(position));
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    ActivityOptions options = ActivityOptions.makeCustomAnimation(getActivity(), R.anim.fade_in, R.anim.fade_out);
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });
        login_progress=v.findViewById(R.id.login_progress);


        return v;
    }
    @Override    public boolean onQueryTextSubmit(String query) {
        list.setVisibility(View.INVISIBLE);
        searchcity(query);
        return false;
    }
    private void searchcity(final String query) {
        searchresults.clear();
        searchlist.setAdapter(null);
        showProgress(true);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, Url.URL_SEARCH_CITY, new Response.Listener<String>()
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
                                searchresults.add(new SEARCHRESULT(data[0],data[1],data[2],data[3],data[4],data[5],data[6],data[7],data[8],data[9],data[10],data[11],data[12],data[13],data[14],data[15],data[16],data[17],data[18]));
                            }
                            resultadapter=new RESULTADAPTER(getActivity(),searchresults);
                            searchlist.setAdapter(resultadapter);
                            searchlist.setVisibility(View.VISIBLE);
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
                showProgress(false);
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
                params.put("city",query);
                return params;
            }
        };
        MySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        if (newText.equalsIgnoreCase("")) {
            list.setVisibility(View.GONE);
        }else {
            list.setVisibility(View.VISIBLE);
        }
        return false;
    }


    @Override
    public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
        String queryString = parent.getItemAtPosition(position).toString();
        editsearch.setQuery(queryString,false);
        list.setVisibility(View.INVISIBLE);
        editsearch.clearFocus();
        searchcity(queryString);
    }


    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.searchproperty)
        {
            searchcontent.setVisibility(View.VISIBLE);
            searchcard.setVisibility(View.GONE);
        }
    }
}
