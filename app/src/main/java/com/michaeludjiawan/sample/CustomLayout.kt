package com.michaeludjiawan.sample

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class CustomLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var onBtnClickListener: () -> Unit = {}

    init {
        val textView = TextView(context).apply {
            text = "This is custom layout."
            textSize = 20F
            setTextColor(Color.GREEN)
        }
        val button = Button(context).apply {
            text = "Go back"
            textSize = 24F
            setOnClickListener { onBtnClickListener() }
        }
        addView(textView)
        addView(button)
    }
}