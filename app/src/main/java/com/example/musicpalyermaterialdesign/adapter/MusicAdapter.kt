package com.example.musicpalyermaterialdesign.adapter

import android.app.ProgressDialog
import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.musicpalyermaterialdesign.R
import com.example.musicpalyermaterialdesign.animation.AnimationHelper
import com.example.musicpalyermaterialdesign.interfaces.AbstractView
import com.example.musicpalyermaterialdesign.model.SongInfo
import com.example.musicpalyermaterialdesign.presenter.PresenterImpl

class MusicAdapter(val context:Context,val listSongs:List<SongInfo>):
    RecyclerView.Adapter<MusicAdapter.ViewHolder>(),AbstractView
{
    val TAG="aaa"
    var progressDialog:ProgressDialog ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {

        val view = LayoutInflater.from(context).inflate(R.layout.layout_one_song_all,
            parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindWigets(listSongs[position])
        AnimationHelper.animate(holder.itemView)
        holder.btnPlay.setOnClickListener {
            val presenterImpl = PresenterImpl(context,this,holder)
            presenterImpl.clickPlayButton(listSongs[position])
        }
    }

    override fun getItemCount(): Int {
        return listSongs.count()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {

        val songTitle : TextView=itemView.findViewById(R.id.songTitle)
        val songSinger : TextView =itemView.findViewById(R.id.txtSinger)
        val btnPlay:ImageView=itemView.findViewById(R.id.button_play)
        val seekbar: SeekBar =itemView.findViewById(R.id.seek_bar)
        val txtDuration:TextView=itemView.
            findViewById(R.id.text_music_duration)
        fun bindWigets(songInfo: SongInfo)
        {
            songTitle.text=songInfo.Title
            songSinger.text=songInfo.Singer
        }
    }


    override fun showToast(str: String) {
        Log.d("aaa",str)
        Toast.makeText(context,str, Toast.LENGTH_LONG).show()
    }

    override fun runProgressDialog() {
        progressDialog = ProgressDialog(context);
        progressDialog!!.setTitle("Loading");
        progressDialog!!.setMessage("Please wait...");
        progressDialog!!.setCancelable(false);
        progressDialog!!.show();
    }

    override fun progressDialogDismiss() {
        progressDialog?.dismiss()
    }

    override fun chnageImagePlayButton(holder: ViewHolder,
                                       strImgDrawable:String)
    {
        val resourceID=context.resources.getIdentifier(strImgDrawable,
            "drawable",context.packageName)
        holder.btnPlay.setImageResource(resourceID)
    }

    override fun setSeekBarMax(holder: ViewHolder,duration: Int) {
        holder.seekbar.max=duration
    }

    override fun setSeekBarProgress(holder: ViewHolder, currentTime: Int) {
        holder.seekbar.progress=currentTime
    }

    override fun setTextSongDuration(holder: ViewHolder, strDuration: String) {
        holder.txtDuration.text=strDuration
    }
}