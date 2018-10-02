package appp.renthub;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class SENTITEMSADAPTER extends ArrayAdapter<SENTITEMSRESULT> {
    List<SENTITEMSRESULT> sentitemsresults;

    Activity activity;

    public SENTITEMSADAPTER(@NonNull Activity activity, @NonNull List <SENTITEMSRESULT> sentitemsresults ) {
        super(activity, R.layout.customsentlist, sentitemsresults);
        this.activity=activity;
        this.sentitemsresults=sentitemsresults;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        convertView= activity.getLayoutInflater().inflate(R.layout.customsentlist,null);
        com.beardedhen.androidbootstrap.BootstrapCircleThumbnail imageView=convertView.findViewById(R.id.image);
        TextView textView=convertView.findViewById(R.id.name);
        TextView textView1=convertView.findViewById(R.id.lastmsg);
        TextView textView3=convertView.findViewById(R.id.date);

        SENTITEMSRESULT sentitemsresult=sentitemsresults.get(position);
        Picasso.with(getContext()).load(sentitemsresult.getReceiverpic()).fit().centerCrop().into(imageView);
        textView.setText(sentitemsresult.getReceivername());
        textView1.setText(sentitemsresult.getMsg());
        textView3.setText(sentitemsresult.getDateofmsg());

        return convertView;
    }
}
