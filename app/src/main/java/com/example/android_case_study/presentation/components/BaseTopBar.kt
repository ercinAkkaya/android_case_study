package com.example.android_case_study.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.android_case_study.databinding.BaseTopBarBinding

class BaseTopBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: BaseTopBarBinding = BaseTopBarBinding.inflate(LayoutInflater.from(context), this)


    fun setTitle(title: String) {
        binding.titleTxt.text = title
    }

    fun isHasIcon(hasIcon: Boolean) {
        if(hasIcon) {
            binding.apply {
                btnBack.visibility = VISIBLE
            }
        } else {
            binding.apply {
                btnBack.visibility = GONE
            }
        }
    }

    fun setOnBackClickListener(listener: () -> Unit) {
        binding.btnBack.setOnClickListener { listener.invoke() }
    }

}