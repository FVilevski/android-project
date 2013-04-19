package vilevski.android.games.vimemory.classes;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import vilevski.android.games.vimemory.database.ScoresTable;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class DownloadTask extends AsyncTask<String, String, String> {

		JSONObject json = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		JSONArray products = null;
		
		
		public static final String HIGHSCORES_DOWNLOADED_ACTION = "vilevski.android.games.vimemory.classes.HIGHSCORES_DOWNLOADED_ACTION";
		public static final String URL = "http://50.116.84.63/~risto/sharedrive/Filip/getScores.php";

		ProgressDialog dialog;
		Context mContext;
		ScoresTable dbHelper;
		public DownloadTask(Context context) {
			mContext = context;
			dialog = new ProgressDialog(context);
				
		    dbHelper = new ScoresTable(mContext);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			//Vo ovoj metod se brisat site predhodni scorovi sto bile vo bazata za da
			//se napravi prostor za novite skorovi i pritoa da nema povtoruvanje na istite
			dbHelper.open();
			dbHelper.deleteAll();
			dbHelper.close();
		}
		
		
		@Override
		protected String doInBackground(String... arg0) {
			try {
				
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				
				json = jsonParser.makeHttpRequest(URL, "GET", params);

			} catch (Exception err) {
				Log.d("Greska  doInBackground() ", err.getMessage());
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			try {
				int success = json.getInt("success");

				if (success == 1) {
					products = json.getJSONArray("username");
					dbHelper = new ScoresTable(mContext);
					dbHelper.open();
					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);

						String gamer = c.getString("gamer");
						int score = Integer.parseInt(c.getString("score"));
						String gamelevel = c.getString("gamelevel");

						HighScore temp = new HighScore(gamer, score, gamelevel);

						dbHelper.insert(temp);					
					}
					dbHelper.close();
					Intent intent = new Intent(HIGHSCORES_DOWNLOADED_ACTION);
					mContext.sendBroadcast(intent);
				} else {
					dialog.setMessage("There are no high scores");
					dialog.setIndeterminate(false);
					dialog.setCancelable(true);
					dialog.show();
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			try {
				dialog.dismiss();
			} catch (Exception err) {
			}

		}

	}

/***	
 *	Pomoshen kod za insert na HighScores po zavrsenoto igranje 
 */
/*
	class InsertToDB extends AsyncTask<String, String, String> {

		public static final String URL = "http://50.116.84.63/~risto/sharedrive/Filip/insertScore.php";
		
		JSONObject json = new JSONObject();
		JSONParser jsonParser = new JSONParser();
		JSONArray products = null;

		
		ProgressDialog dialog;
		Context mContext;
		public InsertToDB(Context context){
			mContext = context;
			dialog = new ProgressDialog(mContext);
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			dialog.setMessage("Ве молиме почека�?те...");
			dialog.setIndeterminate(false);
			dialog.setCancelable(true);
			dialog.show();
		}
		
		@Override
		protected String doInBackground(String... p) {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("gamer", "gamerNAME"));
			params.add(new BasicNameValuePair("score", "gamerSCORE"));
			params.add(new BasicNameValuePair("gamelevel", "gamerLEVEL"));
			
			json = jsonParser.makeHttpRequest(URL, "GET", params);
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
			try {
				int success = json.getInt("success");
				
				if(success == 1){
					Log.e("TAG", "Uspesno");
				}else{
					Log.e("TAG", "NeUspesno");
				}				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			try{
				dialog.dismiss();
			}
			catch(Exception err){}
		}
	}
*/
