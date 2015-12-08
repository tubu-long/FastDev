package com.github.longqiany.fastdev.core.utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.media.SoundPool;
import android.net.Uri;

import java.io.IOException;

/**
 * Created by zzz on 12/8/15.
 */
public class MediaUtils {
    private static SoundPool soundPool;
    /*public static MediaPlayer mMediaPlayer;
    static long Soundm;*/
    public static MediaPlayer ring(Context context) throws Exception, IOException {
        Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        MediaPlayer player = new MediaPlayer();
        player.setDataSource(context, alert);
        final AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION) != 0) {
            player.setAudioStreamType(AudioManager.MODE_RINGTONE);
            player.setLooping(false);
            player.prepare();
            player.start();
        }
        return player;
    }

    public void soundRing(Context context) throws IllegalArgumentException, SecurityException, IllegalStateException, IOException {

        MediaPlayer mp = new MediaPlayer();
        mp.reset();
        mp.setDataSource(context, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        mp.prepare();
        mp.start();
    }

    public static int playPre(Context context, int id) {
        soundPool = new SoundPool(10, AudioManager.STREAM_RING, 5);
        int sourceId = soundPool.load(context, id, 0);
        return sourceId;
    }

    public static void playS(int sourceId) {
        int play = soundPool.play(sourceId, 1f, 1f, 1, 0, 1);
//        soundPool.setVolume(play,1.0f,1.0f);
    }
}
