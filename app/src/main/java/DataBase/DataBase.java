package DataBase;

import android.provider.BaseColumns;
import com.example.ABeeR.movie;

/**
 * Created by ABeeR on 23-Apr-16.
 */
public class DataBase {

    public static final String database_Name = "Favourite Movies";
    public static final int database_Version = 1;
    public static final String Table = "Favourite";

    public static class columns {
        public movie m = new movie();
        public static final String ID = BaseColumns._ID;
        public static final String poster_path = ("poster_path");
        public static final String overview = ("overview");
        public static final String original_title = ("original_title");
        public static final String backdrop_path = ("backdrop_path");
        public static final String release_date = ("release_date");
        public static final String vote_average = ("vote_average");
        public static final String id = ("id");
    }
}
