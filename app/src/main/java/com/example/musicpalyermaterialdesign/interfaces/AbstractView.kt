package com.example.musicpalyermaterialdesign.interfaces

import com.example.musicpalyermaterialdesign.adapter.MusicAdapter

interface AbstractView
{
    fun showToast(str:String)
    fun runProgressDialog()
    fun progressDialogDismiss()
    fun chnageImagePlayButton(holder: MusicAdapter.ViewHolder,
                              strImgDrawable:String)
    fun setSeekBarMax(holder: MusicAdapter.ViewHolder, duration:Int)
    fun setSeekBarProgress(holder: MusicAdapter.ViewHolder, currentTime:Int)
    fun setTextSongDuration(holder: MusicAdapter.ViewHolder,
                            strDuration:String)
}