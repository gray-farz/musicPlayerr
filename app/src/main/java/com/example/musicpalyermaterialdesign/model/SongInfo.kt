package com.example.musicpalyermaterialdesign.model

class SongInfo
{
    var Title:String ?= null
    var Singer:String ?= null
    var SongURL:String ?= null

    constructor(title:String,singer:String,songURL:String)
    {
        this.Title = title
        this.Singer = singer
        this.SongURL = songURL
    }
}