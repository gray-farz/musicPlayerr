package com.example.musicpalyermaterialdesign.presenter

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import com.example.musicpalyermaterialdesign.adapter.MusicAdapter
import com.example.musicpalyermaterialdesign.interfaces.AbstractView
import com.example.musicpalyermaterialdesign.model.SongInfo
import java.lang.Exception
import java.util.*

class PresenterImpl(val context: Context, val abstractView: AbstractView,
    val holder:MusicAdapter.ViewHolder)
{
    val TAG="aaa"
    var mediaPlayer:MediaPlayer ?= null

    fun clickPlayButton(songInfo: SongInfo)
    {
        Log.d(TAG, "clickPlay: ")

        var songProgressThread=SongProgressThread()
        songProgressThread.start()

        mediaPlayer= MediaPlayer()
        try{
            mediaPlayer!!.setDataSource(songInfo.SongURL)
        }
        catch (ex: Exception)
        {
            Log.d(TAG, "Exception: "+ex.toString())
        }

        mediaPlayer!!.prepare()
        mediaPlayer!!.setOnCompletionListener {
            Log.d(TAG, "ENDDD: ")
            abstractView.chnageImagePlayButton(holder,
                "ic_play")
            abstractView.setSeekBarProgress(holder,0)
            mediaPlayer!!.seekTo(0)
        }
        if(mediaPlayer!!.isPlaying())
        {
            mediaPlayer!!.pause();
            abstractView.chnageImagePlayButton(holder,
                "ic_play")
        }
        else {
            mediaPlayer!!.start()
            abstractView.chnageImagePlayButton(holder,
                "ic_pause")
        }
        abstractView.setSeekBarMax(holder,mediaPlayer!!.duration)
    }

    inner class SongProgressThread() :Thread()
    {
        override fun run()
        {
            while(true)
            {
                try {
                    Thread.sleep(20)

                }
                catch (ex:Exception)
                {
                    Log.d(TAG, "ex: "+ex.toString())
                }
                (context as Activity).runOnUiThread {
                    if(mediaPlayer!!.currentPosition != 0)
                        abstractView.setSeekBarProgress(holder,
                            mediaPlayer!!.currentPosition)
                }

            }
        }
    }

}