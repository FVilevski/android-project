package vilevski.android.games.vimemory;

import java.util.List;

import vilevski.android.games.vimemory.classes.HighScoreListRow;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HighScoresCustomAdapter extends BaseAdapter {
	private List<HighScoreListRow> highScores;
	private Context ctx;
	private LayoutInflater inflater;
	
	
	public HighScoresCustomAdapter(Context ctx, List<HighScoreListRow> hs){
		this.ctx = ctx;
		this.highScores = hs;
	}
	
	private class ViewHolder{
		TextView txtGamer;
		TextView txtLevel;
		TextView txtScore;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		inflater = (LayoutInflater) ctx
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		
		if(convertView == null){
			convertView = inflater.inflate(R.layout.highscorerowlayout, null);
			holder = new ViewHolder();
			holder.txtGamer = (TextView) convertView.findViewById(R.id.txtGamer1);
			holder.txtLevel = (TextView) convertView.findViewById(R.id.txtLevel1);
			holder.txtScore = (TextView) convertView.findViewById(R.id.txtScore1);
			convertView.setTag(holder);
		
		}else{
			
			holder = (ViewHolder) convertView.getTag();
		}
		
		HighScoreListRow rowItem = (HighScoreListRow) getItem(position);
		
			
			holder.txtGamer.setText(rowItem.getTxtGamer());
			holder.txtScore.setText(rowItem.getTxtScore());
			holder.txtLevel.setText(rowItem.getTxtLevel());
		
		return convertView;
	}
	
	@Override
	public int getCount() {
		return highScores.size();
	}

	@Override
	public Object getItem(int position) {
		return highScores.get(position);
	}

	@Override
	public long getItemId(int position) {
		return highScores.indexOf(getItem(position));
	}

	
}
