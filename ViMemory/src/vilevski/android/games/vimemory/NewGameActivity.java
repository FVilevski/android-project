package vilevski.android.games.vimemory;

import java.util.ArrayList;
import java.util.List;

import vilevski.android.games.vimemory.classes.LevelListRow;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class NewGameActivity extends Activity implements OnItemClickListener{

	public static final String[] levels = new String[]{
		"Very easy",
		"Easy",
		"Normal",
		"Hard",
		"Very hard" 
	};
	public static final Integer[] images = {
		R.drawable.veasy,
		R.drawable.easy,
		R.drawable.normal,
		R.drawable.hard,
		R.drawable.vhard,
	};
	
	private ListView listView;
	private List<LevelListRow> rowItems;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_game);
		setupActionBar();

		rowItems = new ArrayList<LevelListRow>();
		for (int i = 0; i < levels.length; i++) {
			LevelListRow item = new LevelListRow(images[i], levels[i]);
			rowItems.add(item);
		}
		
		listView = (ListView) findViewById(R.id.levelList);
		
		LevelCustomAdapter adapter = new LevelCustomAdapter(this, rowItems);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(this, PlayActivity.class);

		Context context = getApplicationContext();		
		CharSequence text = "You chose item: " + (position+1);
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();

		switch (position) {
		case 0:
			intent.addFlags(0); //Very easy level
			break;
		case 1:
			intent.addFlags(1); //Easy level
			break;	
		case 2:
			intent.addFlags(2); //Normal level
			break;	
		case 3:
			intent.addFlags(3); //Hard level
			break;
		case 4:
			intent.addFlags(4); //Very Hard level
			break;
		}
		startActivity(intent);
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
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	

}
