package appp.renthub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewSentMessage extends AppCompatActivity {

    com.beardedhen.androidbootstrap.BootstrapCircleThumbnail receiverpic;
    TextView sendername,receivername,dateofmsg,timeofmsg,message;
    SENTITEMSRESULT sentitemsresult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sent_message);

        Intent intent=getIntent();
        sentitemsresult=(SENTITEMSRESULT)intent.getSerializableExtra("sentresult");

        receiverpic=findViewById(R.id.receiverpic);
        sendername=findViewById(R.id.sendername);
        receivername=findViewById(R.id.receivername);
        dateofmsg=findViewById(R.id.dateofmsg);
        timeofmsg=findViewById(R.id.timeofmsg);
        message=findViewById(R.id.message);

        if (sentitemsresult.getSenderpic().equalsIgnoreCase("null"))
        {
            receiverpic.setImageResource(R.drawable.defaultpic);
        }
        else
        {
            Picasso.with(this).load(sentitemsresult.getReceiverpic()).fit().centerCrop().into(receiverpic);
        }

        sendername.setText(sentitemsresult.getSendername());
        receivername.setText(sentitemsresult.getReceivername());
        dateofmsg.setText(sentitemsresult.getDateofmsg());
        timeofmsg.setText(sentitemsresult.getTimeofmsg());
        message.setText(sentitemsresult.getMsg());
    }
}
