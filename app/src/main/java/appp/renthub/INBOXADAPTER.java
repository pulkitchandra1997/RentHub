package appp.renthub;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class INBOXADAPTER extends ArrayAdapter <INBOXRESULT> {
    List<INBOXRESULT> inboxresults;

    Activity activity;

    public INBOXADAPTER(@NonNull Activity activity, @NonNull List <INBOXRESULT> inboxresults) {
        super(activity, R.layout.custominboxlist, inboxresults);
        this.activity=activity;
        this.inboxresults=inboxresults;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        

        convertView= activity.getLayoutInflater().inflate(R.layout.custominboxlist,null);
        com.beardedhen.androidbootstrap.BootstrapCircleThumbnail imageView=convertView.findViewById(R.id.image);
        TextView textView=convertView.findViewById(R.id.name);
        TextView textView1=convertView.findViewById(R.id.lastmsg);
        TextView textView3=convertView.findViewById(R.id.date);

        INBOXRESULT inboxresult=inboxresults.get(position);
        Picasso.with(getContext()).load(inboxresult.getSenderpic()).fit().centerCrop().into(imageView);
        textView.setText(inboxresult.getSendername());
        textView1.setText(inboxresult.getMsg());
        textView3.setText(inboxresult.getDateofmsg());
        
        return convertView;
    }
}
