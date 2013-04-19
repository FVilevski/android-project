package vilevski.android.games.vimemory.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ViMemoryDatabaseHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "vimemory.db";
	public static int DATABASE_VERSION = 1;

	public ViMemoryDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		ScoresTable.onCreate(database);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		ScoresTable.onUpgrade(db, oldVersion, newVersion);
	}

}
