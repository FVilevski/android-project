package vilevski.android.games.vimemory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vilevski.android.games.vimemory.classes.Broadcast;
import vilevski.android.games.vimemory.classes.DownloadScoresService;
import vilevski.android.games.vimemory.classes.DownloadTask;
import vilevski.android.games.vimemory.classes.HighScore;
import vilevski.android.games.vimemory.classes.HighScoreListRow;
import vilevski.android.games.vimemory.database.ScoresTable;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class HighScoresActivity extends Activity {

	private Intent service;
	private IntentFilter broadcast;
	private Broadcast receiver = new Broadcast();
	private Calendar calendar = Calendar.getInstance();
	private ListView listView;
	private List<HighScoreListRow> rowItems;
	private ScoresTable dataSource;
	
	@Override
	protected void onResume() {
		super.onResume();
		
		if(broadcast == null){
			IntentFilter filter = new IntentFilter(DownloadTask.HIGHSCORES_DOWNLOADED_ACTION);
			broadcast = new IntentFilter(filter);
		}
		registerReceiver(receiver, broadcast);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_high_scores);
	
		service = new Intent(this,DownloadScoresService.class);
		
		if (!isConnectedToInternet()) {
			Toast.makeText(this, "There is no Internet Connection, please connect for newest high scores", Toast.LENGTH_LONG)
					.show();
		} else {
			//Shared preferences 
			SharedPreferences shared = getSharedPreferences("lastDownload", Context.MODE_PRIVATE);
			int Yesterday = shared.getInt("lastDownload", 0);
			int Today = calendar.get(Calendar.DAY_OF_WEEK);

			
			if((Yesterday == 0)||(Today > Yesterday)){
				int day = calendar.get(Calendar.DAY_OF_WEEK);
				Editor editor = shared.edit();
				editor.putInt("lastDownload", day);
				editor.commit();
				// Starts service to get updated version of high scores
				startService(service);
			}
			
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		setupActionBar();
		
		dataSource = new ScoresTable(this);
		dataSource.open();
		
		List<HighScore> players = dataSource.getAllScores();
		
		rowItems = new ArrayList<HighScoreListRow>();
		for (HighScore elem : players) {
			HighScoreListRow item = 
			new HighScoreListRow(elem.getGamer(),elem.getGameLevel(), elem.getScore()+"");
			rowItems.add(item);
		}
		
		listView = (ListView) findViewById(R.id.highScoresList);
		
		HighScoresCustomAdapter adapter = new HighScoresCustomAdapter(this, rowItems);
		listView.setAdapter(adapter);
	}
	
	public boolean isConnectedToInternet() {
		boolean haveConnectedWifi = false;
		boolean haveConnectedMobile = false;

		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		for (NetworkInfo ni : netInfo) {
			if (ni.getTypeName().equalsIgnoreCase("WIFI"))
				if (ni.isConnected())
					haveConnectedWifi = true;
			if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
				if (ni.isConnected())
					haveConnectedMobile = true;
		}
		return haveConnectedWifi || haveConnectedMobile;
	}
	
	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		stopService(service);
		unregisterReceiver(receiver);
	}

}
