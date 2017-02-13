package com.music.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created on 13-02-2017.
 */

public class MusicService extends Service implements MediaPlayer.OnCompletionListener {

    private MediaPlayer mp;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this, R.raw.music);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!mp.isPlaying()) {
            mp.start();
            Toast.makeText(this, "Music started..", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Music paused..", Toast.LENGTH_SHORT).show();
            mp.pause();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mp.isPlaying()) {
            mp.stop();
            Toast.makeText(this, "Music stopped..", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.release();
    }
}
