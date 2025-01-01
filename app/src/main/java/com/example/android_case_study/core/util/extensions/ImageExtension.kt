package com.example.android_case_study.core.util.extensions

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android_case_study.R

fun ImageView.loadImage(url: String, placeHolder: CircularProgressDrawable) {
    val options = RequestOptions().placeholder(placeHolder).error(R.drawable.baseline_error_24)
    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)
}


fun placeHolder(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}