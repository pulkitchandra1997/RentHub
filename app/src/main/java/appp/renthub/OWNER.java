package appp.renthub;

import java.io.Serializable;

/**
 * Created by PULKITCHANDRA on 07-Apr-18.
 */

public class OWNER implements Serializable {
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String type;
    String name;
    String phone;
    String dob;

    public OWNER() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String email;
    public OWNER(String email, String name, String phone, String dob, String marriagestatus, String city, String permanentaddress, String pincode, String gender, String password, String verified) {
        this.name = name;
        this.email=email;
        this.phone = phone;
        this.dob = dob;
        this.marriagestatus = marriagestatus;
        this.city = city;
        this.permanentaddress = permanentaddress;
        this.pincode = pincode;
        this.gender = gender;
        this.password = password;
        this.verified = verified;
        this.type="owner";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMarriagestatus() {
        return marriagestatus;
    }

    public void setMarriagestatus(String marriagestatus) {
        this.marriagestatus = marriagestatus;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPermanentaddress() {
        return permanentaddress;
    }

    public void setPermanentaddress(String permanentaddress) {
        this.permanentaddress = permanentaddress;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    String marriagestatus;
    String city;
    String permanentaddress;
    String pincode;
    String gender;
    String password;
    String verified;
}
