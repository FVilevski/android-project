package vilevski.android.games.vimemory.classes;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DownloadScoresService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		DownloadTask task = new DownloadTask(this);
		task.execute();
		return super.onStartCommand(intent, flags, startId);
	}
}
