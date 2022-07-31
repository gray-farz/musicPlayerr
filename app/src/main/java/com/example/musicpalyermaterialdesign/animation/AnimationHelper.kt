package com.example.musicpalyermaterialdesign.animation

import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.TranslateAnimation

object AnimationHelper{

    fun animate(itemView: View)
    {
        val alphaAnim = AlphaAnimation(0.0f , 1.0f)
        alphaAnim.duration = 2000

//        val translateAnimation = TranslateAnimation(-100f, 0.0f, 0.0f,0.0f)
//        translateAnimation.duration = 1000

        itemView.startAnimation(alphaAnim)

    }

}