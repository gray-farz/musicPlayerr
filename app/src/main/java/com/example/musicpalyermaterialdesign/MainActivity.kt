package com.example.musicpalyermaterialdesign

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicpalyermaterialdesign.adapter.MusicAdapter
import com.example.musicpalyermaterialdesign.interfaces.AbstractView
import com.example.musicpalyermaterialdesign.model.SongInfo
import com.example.musicpalyermaterialdesign.presenter.PresenterImpl
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity(){
    lateinit var musicAdapter:MusicAdapter
    var listOfSongs = ArrayList<SongInfo>()
    val TAG="aaa"
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LoadSongsFromLocal()
        musicAdapter= MusicAdapter(this,listOfSongs)
        rec.adapter=musicAdapter
        rec.layoutManager=LinearLayoutManager(this)
        rec.setHasFixedSize(true)

    }

    @SuppressLint("Range")
    fun LoadSongsFromLocal()
    {
        val allSongs = MediaStore.Audio.Media.INTERNAL_CONTENT_URI
        val selection = MediaStore.Audio.Media.IS_MUSIC + "!=0"

        val cursor = contentResolver.query(allSongs,
            null,selection,null,null)

        if (cursor != null)
        {
            if (cursor.moveToFirst() == true)
            {
                do {
                    val songURL = cursor!!.getString(cursor!!.getColumnIndex(MediaStore.Audio.Media.DATA))
                    val songAuthor = cursor!!.getString(cursor!!.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                    val songName = cursor!!.getString(cursor!!.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME))

                    listOfSongs.add(SongInfo(songName,songAuthor,songURL))


                }while (cursor.moveToNext() == true)
            }
            cursor!!.close()
        }


    }

}