package appp.renthub.dbutil;

/**
 * Created by pranj on 08-04-2018.
 */

public class RenthubConstant {
    public static final String DBNAME="renthub";
    public static final int DBVERSION=1;
    public static final String TABLE_NAME="rentform";
    public static final String COL_ADDRESS="address";
    public static final String COL_CITY="city";
    public static final String COL_STATUS="status";
    public static final String COL_PINCODE="pincode";
    public static final String COL_AMOUNT="amount";
    public static final String COL_TVID="tvid";
    public static final String COL_MESSID="messid";
    public static final String COL_REFRIGERATORID="refrigeratorid";
    public static final String COL_INVERTORID="invertorid";
    public static final String COL_WIFI="wifiid";
    public static final String COL_SOFAID="sofaid";
    public static final String COL_BEDID="bedid";
    public static final String COL_PARKINGID="parkingid";
    public static final String COL_ACID="acid";
    public static final String COL_RENTED="rented";

    public static final String RENT_SQL="create table rentform(address text,city text,status text,pincode text,amount text,tvid text,messid text" +
            ",refrigeratorid text, invertorid text,wifiid text,sofaid text,bedid text,parkingid text,acid text,rented text)";


}
