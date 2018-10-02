package appp.renthub;

import java.io.Serializable;

public class SENTITEMSRESULT implements Serializable {

    String msgid,sendermail,receivermail,sendername,receivername,senderpic,receiverpic,msg,dateofmsg,timeofmsg;

    public SENTITEMSRESULT(String msgid, String sendermail, String receivermail, String msg,  String dateofmsg, String timeofmsg,String sendername,  String senderpic,String receivername, String receiverpic) {
        this.msgid = msgid;
        this.sendermail = sendermail;
        this.receivermail = receivermail;
        this.sendername = sendername;
        this.receivername = receivername;
        this.senderpic = senderpic;
        this.receiverpic = receiverpic;
        this.msg = msg;
        this.dateofmsg = dateofmsg;
        this.timeofmsg = timeofmsg;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public String getSendermail() {
        return sendermail;
    }

    public void setSendermail(String sendermail) {
        this.sendermail = sendermail;
    }

    public String getReceivermail() {
        return receivermail;
    }

    public void setReceivermail(String receivermail) {
        this.receivermail = receivermail;
    }

    public String getSendername() {
        return sendername;
    }

    public void setSendername(String sendername) {
        this.sendername = sendername;
    }

    public String getReceivername() {
        return receivername;
    }

    public void setReceivername(String receivername) {
        this.receivername = receivername;
    }

    public String getSenderpic() {
        return senderpic;
    }

    public void setSenderpic(String senderpic) {
        this.senderpic = senderpic;
    }

    public String getReceiverpic() {
        return receiverpic;
    }

    public void setReceiverpic(String receiverpic) {
        this.receiverpic = receiverpic;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDateofmsg() {
        return dateofmsg;
    }

    public void setDateofmsg(String dateofmsg) {
        this.dateofmsg = dateofmsg;
    }

    public String getTimeofmsg() {
        return timeofmsg;
    }

    public void setTimeofmsg(String timeofmsg) {
        this.timeofmsg = timeofmsg;
    }
}
