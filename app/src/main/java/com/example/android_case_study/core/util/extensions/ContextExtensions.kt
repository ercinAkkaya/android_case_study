package com.example.android_case_study.core.util.extensions

import android.content.Context

fun Context.showToast(message: String) {
    android.widget.Toast.makeText(this, message, android.widget.Toast.LENGTH_SHORT).show()
}
