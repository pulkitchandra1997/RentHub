package appp.renthub;

/**
 * Created by PULKITCHANDRA on 11-Apr-18.
 */

public class SEARCHRESULT {
    public SEARCHRESULT(String address, String amount, String picname) {
        this.address = address;
        this.amount = amount;
        this.picname = picname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPicname() {
        return picname;
    }

    public void setPicname(String picname) {
        this.picname = picname;
    }

    String address,amount,picname;

}
