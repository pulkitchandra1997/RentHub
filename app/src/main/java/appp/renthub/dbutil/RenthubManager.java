package appp.renthub.dbutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by pranj on 08-04-2018.
 */

public class RenthubManager
{
    RenthubHelper renthubHelper;
    SQLiteDatabase sqLiteDatabase;
    public RenthubManager(Context context) {
        renthubHelper = new RenthubHelper(context, RenthubConstant.DBNAME, null, RenthubConstant.DBVERSION);
    }
    public SQLiteDatabase openDB() {
        sqLiteDatabase = renthubHelper.getWritableDatabase();
        return sqLiteDatabase;
    }

    public void closeDB() {
        renthubHelper.close();
    }
}
