package com.example.android_case_study.presentation.ui.detail.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailModel(
    val imageUrl: String,
    val name: String,
    val description: String,
    val price: Double,
    val isFavorite: Boolean,
) : Parcelable