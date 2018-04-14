package appp.renthub;

import java.io.Serializable;

/**
 * Created by PULKITCHANDRA on 11-Apr-18.
 */

public class SEARCHRESULT implements Serializable {
    String address,amount,picname,tvid,wifiid,messid,refrigeratorid,invertorid,sofaid,bedid,parkingid,acid,rented,owneremail,city,pincode,status,ownername,ownerpic;

    public SEARCHRESULT(String address, String amount, String picname, String status) {
        this.address = address;
        this.amount = amount;
        this.picname = picname;
        this.status = status;
    }

    public SEARCHRESULT(String address, String amount, String picname, String tvid, String wifiid, String messid, String refrigeratorid, String invertorid, String sofaid, String bedid, String parkingid, String acid, String rented, String owneremail, String city, String pincode, String status, String ownername, String ownerpic) {
        this.address = address;
        this.amount = amount;
        this.picname = picname;
        this.tvid = tvid;
        this.wifiid = wifiid;
        this.messid = messid;
        this.refrigeratorid = refrigeratorid;
        this.invertorid = invertorid;
        this.sofaid = sofaid;
        this.bedid = bedid;
        this.parkingid = parkingid;
        this.acid = acid;
        this.rented = rented;
        this.owneremail = owneremail;
        this.city = city;
        this.pincode = pincode;
        this.status = status;
        this.ownername = ownername;
        this.ownerpic = ownerpic;
    }

    public SEARCHRESULT() {
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

    public String getTvid() {
        return tvid;
    }

    public void setTvid(String tvid) {
        this.tvid = tvid;
    }

    public String getWifiid() {
        return wifiid;
    }

    public void setWifiid(String wifiid) {
        this.wifiid = wifiid;
    }

    public String getMessid() {
        return messid;
    }

    public void setMessid(String messid) {
        this.messid = messid;
    }

    public String getRefrigeratorid() {
        return refrigeratorid;
    }

    public void setRefrigeratorid(String refrigeratorid) {
        this.refrigeratorid = refrigeratorid;
    }

    public String getInvertorid() {
        return invertorid;
    }

    public void setInvertorid(String invertorid) {
        this.invertorid = invertorid;
    }

    public String getSofaid() {
        return sofaid;
    }

    public void setSofaid(String sofaid) {
        this.sofaid = sofaid;
    }

    public String getBedid() {
        return bedid;
    }

    public void setBedid(String bedid) {
        this.bedid = bedid;
    }

    public String getParkingid() {
        return parkingid;
    }

    public void setParkingid(String parkingid) {
        this.parkingid = parkingid;
    }

    public String getAcid() {
        return acid;
    }

    public void setAcid(String acid) {
        this.acid = acid;
    }

    public String getRented() {
        return rented;
    }

    public void setRented(String rented) {
        this.rented = rented;
    }

    public String getOwneremail() {
        return owneremail;
    }

    public void setOwneremail(String owneremail) {
        this.owneremail = owneremail;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getOwnerpic() {
        return ownerpic;
    }

    public void setOwnerpic(String ownerpic) {
        this.ownerpic = ownerpic;
    }
}
