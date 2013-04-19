package vilevski.android.games.vimemory.database;

import java.util.ArrayList;
import java.util.List;

import vilevski.android.games.vimemory.classes.HighScore;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ScoresTable {

	//Table name
	public static final String TABLE_SCORES = "scores";
	//Table columns
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_GAMER = "gamer";
	public static final String COLUMN_SCORE = "score";
	public static final String COLUMN_GAMELEVEL = "gamelevel";
	///
	private ViMemoryDatabaseHelper dbHelper;
	public SQLiteDatabase database;
	private String[] allColumns = {
			COLUMN_ID,
			COLUMN_GAMER, 
			COLUMN_SCORE,
			COLUMN_GAMELEVEL
	};
	///
	private static final String DATABASE_CREATE = "CREATE TABLE "
	+ TABLE_SCORES
	+ "("
	+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
	+ COLUMN_GAMER + " TEXT NOT NULL, "
	+ COLUMN_SCORE + " INTEGER NOT NULL, "
	+ COLUMN_GAMELEVEL + " TEXT NOT NULL"
	+ ");";

	public ScoresTable(Context context){
		dbHelper = new ViMemoryDatabaseHelper(context);	
	}
	
	public void open() throws SQLException{
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		database.close();
		dbHelper.close();
	}
	
	public boolean insert(HighScore highScore){
		if(highScore.getId() != null){
			return update(highScore);
		}
		
		long insertId = database.insert(TABLE_SCORES, null,
				highScoreToContentValues(highScore));
	
		if (insertId > 0) {
			highScore.setId(insertId);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean update(HighScore highScore){
		long numRowsAffected = database.update(TABLE_SCORES,
				highScoreToContentValues(highScore), COLUMN_ID + " = "
						+ highScore.getId(), null);
		return numRowsAffected > 0;
	}
	
	public List<HighScore> getAllScores() {
		List<HighScore> highScores = new ArrayList<HighScore>();

		Cursor cursor = database.query(TABLE_SCORES, allColumns,
				null, null, null, null, null);

		if (cursor.moveToFirst()) {
			do {
				highScores.add(cursorToHighScore(cursor));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return highScores;
	}

	public HighScore getById(long id) {

		Cursor cursor = database
				.query(TABLE_SCORES, allColumns,
						COLUMN_ID + " = " + id, null, null,
						null, null);
		try {
			if (cursor.moveToFirst()) {
				return cursorToHighScore(cursor);
			} else {
				// no highScores found
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			cursor.close();
		}

	}

	protected HighScore cursorToHighScore(Cursor cursor) {
		HighScore highScore = new HighScore();
		highScore.setId(cursor.getLong(cursor
				.getColumnIndex(COLUMN_ID)));

		highScore.setGamer(cursor.getString(cursor
				.getColumnIndex(COLUMN_GAMER)));

		highScore.setGameLevel(cursor.getString(cursor
				.getColumnIndex(COLUMN_GAMELEVEL)));

		highScore.setScore(cursor.getInt(cursor
				.getColumnIndex(COLUMN_SCORE)));

		return highScore;
	}
	
	public static void onCreate(SQLiteDatabase database){
		database.execSQL(DATABASE_CREATE);
	}
	
	public static void onUpgrade(SQLiteDatabase database, int oldVerison, int newVerison){	
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);
		onCreate(database);
	}
	public void deleteAll(){
		open();
		String deleteSQL = "DELETE * FROM " + TABLE_SCORES;

		database.execSQL(deleteSQL);
	}
	
	protected ContentValues highScoreToContentValues(HighScore highScore) {
		ContentValues values = new ContentValues();
		if (highScore.getId() != null) {
			values.put(COLUMN_ID, highScore.getId());
		}
		values.put(COLUMN_GAMER, highScore.getGamer());
		values.put(COLUMN_GAMELEVEL, highScore.getGameLevel());
		values.put(COLUMN_SCORE, highScore.getScore());
		return values;
	}
}
