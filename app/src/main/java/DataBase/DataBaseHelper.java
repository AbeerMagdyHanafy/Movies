package DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ABeeR on 23-Apr-16.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase Tasks;

    public DataBaseHelper(Context context) {
        super(context, DataBase.database_Name, null, DataBase.database_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DataBase.Table + "("
                + DataBase.columns.ID + " integer primary key autoincrement, "
                + DataBase.columns.poster_path + " Text, "
                + DataBase.columns.backdrop_path + " Text, "
                + DataBase.columns.original_title + " Text, "
                + DataBase.columns.overview + " Text, "
                + DataBase.columns.release_date + " Text, "
                + DataBase.columns.id + " Text, "
                + DataBase.columns.vote_average + " Text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + DataBase.Table);
        onCreate(db);
    }
}
