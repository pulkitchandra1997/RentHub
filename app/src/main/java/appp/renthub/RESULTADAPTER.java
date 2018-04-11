package appp.renthub;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by PULKITCHANDRA on 11-Apr-18.
 */

public class RESULTADAPTER extends ArrayAdapter<SEARCHRESULT> {
    List<SEARCHRESULT> searchresults;
    Activity activity;
    public RESULTADAPTER(Activity activity,List<SEARCHRESULT> searchresults){
        super(activity,R.layout.customcardlist,searchresults);
        this.activity=activity;
        this.searchresults=searchresults;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= activity.getLayoutInflater().inflate(R.layout.customcardlist,null);
        ImageView imageView=convertView.findViewById(R.id.image);
        TextView textView=convertView.findViewById(R.id.address);
        TextView textView1=convertView.findViewById(R.id.amount);
        TextView textView2=convertView.findViewById(R.id.amounticon);
        SEARCHRESULT searchresult=searchresults.get(position);
        Picasso.with(getContext()).load(searchresult.getPicname()).fit().centerCrop().into(imageView);
        textView.setText(searchresult.getAddress());
        textView1.setText(searchresult.getAmount());
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "Font Awesome 5 Free-Solid-900.otf" );
        textView2.setTypeface(font);
        return convertView;
    }

}
