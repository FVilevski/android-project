package vilevski.android.games.vimemory.classes;

import java.util.ArrayList;
import java.util.List;

import vilevski.android.games.vimemory.database.ScoresTable;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class Broadcast extends BroadcastReceiver {

	static List<HighScore>  listScore = new ArrayList<HighScore>();
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		new ReadMobileDB(arg0).execute();
	}
	
	class ReadMobileDB extends AsyncTask<String, String, String>{
		Context mContext;
		ProgressDialog dialog;
		ScoresTable dbHelper;
		public ReadMobileDB(Context context){
			mContext = context;
			dialog = new ProgressDialog(context);
			dbHelper = new ScoresTable(context);
		}
		
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dbHelper.open();
			dialog.setMessage("Loading, please wait");
			dialog.show();
			
		}
		@Override
		protected String doInBackground(String... params) {
			listScore = dbHelper.getAllScores();
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			dbHelper.close();
			//TUKA SE PRAVAT SITE POLNEJNA VO ADAPTEROT I REFRESH NA ADAPTEROT...
			// Ova ke se doimplenatira koga ke ja dopravam igrata sega za sega ne treba
			for (HighScore elem : listScore) {
				Log.e("TAG","Gamer: "+ elem.getGamer());
			}
			
			
			try{
				dialog.dismiss();
				
			}catch(Exception err){}
		}
		
	}

}
