package com.bognerdranch.android.hellomoon;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlayer {
	private MediaPlayer mPlayer;
	
	public void stop() {
		if (mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}	
	}
	
	
	//Also need audioplayer.stop in HelloMoonFragment to prevent MediaPlayer from continuing
	//playback after the fragment hasbeen destroyed.
	public void play(Context c) {
		//initial call to stop.  This prevents the creation of multiple instances,
		//like if play button hit twice
		stop();
		
		mPlayer = MediaPlayer.create(c, R.raw.one_small_step);
		//set a listener to call stop(above) when audio has finished playing
		mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				//Calling stop when the file has finished releases hold on mediaplayer
				stop();
			}
		});
		
		mPlayer.start();
	}
}
