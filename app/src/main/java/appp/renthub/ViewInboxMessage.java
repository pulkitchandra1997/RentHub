package appp.renthub;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewInboxMessage extends AppCompatActivity implements View.OnClickListener {

    com.beardedhen.androidbootstrap.BootstrapCircleThumbnail senderpic;
    com.beardedhen.androidbootstrap.BootstrapButton reply;
    TextView sendername,receivername,dateofmsg,timeofmsg,message;
    INBOXRESULT inboxresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_message);

        Intent intent=getIntent();
        inboxresult=(INBOXRESULT)intent.getSerializableExtra("inboxresult");

        reply=findViewById(R.id.reply);
        senderpic=findViewById(R.id.senderpic);
        sendername=findViewById(R.id.sendername);
        receivername=findViewById(R.id.receivername);
        dateofmsg=findViewById(R.id.dateofmsg);
        timeofmsg=findViewById(R.id.timeofmsg);
        message=findViewById(R.id.message);

        reply.setOnClickListener(this);

        if (inboxresult.getSenderpic().equalsIgnoreCase("null"))
        {
            senderpic.setImageResource(R.drawable.defaultpic);
        }
        else
        {
            Picasso.with(this).load(inboxresult.getSenderpic()).fit().centerCrop().into(senderpic);
        }

        sendername.setText(inboxresult.getSendername());
        receivername.setText(inboxresult.getReceivername());
        dateofmsg.setText(inboxresult.getDateofmsg());
        timeofmsg.setText(inboxresult.getTimeofmsg());
        message.setText(inboxresult.getMsg());

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.reply)
        {
            Intent intent = new Intent(ViewInboxMessage.this, ComposeMessage.class);
            intent.putExtra("receivermail",inboxresult.getSendermail());
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                ActivityOptions options = ActivityOptions.makeCustomAnimation(ViewInboxMessage.this, R.anim.fade_in, R.anim.fade_out);
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }
    }
}
