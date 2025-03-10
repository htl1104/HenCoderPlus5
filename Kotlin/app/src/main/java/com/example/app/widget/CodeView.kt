package com.example.app.widget

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import com.example.app.R
import com.example.core.utils.Utils
import java.util.*

class CodeView : AppCompatTextView {
    constructor(context: Context) : this(context, null)


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        gravity = Gravity.CENTER
        setBackgroundColor(getContext().getColor(R.color.colorPrimary))
        setTextColor(Color.WHITE)

        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.color = getContext().getColor(R.color.colorAccent)
        paint.strokeWidth = Utils.dp2px(6f)

        updateCode()
    }

    private val codeList = arrayOf("kotlin",
            "android",
            "java",
            "http",
            "https",
            "okhttp",
            "retrofit",
            "tcp/ip")

    fun updateCode() {
        val random = Random().nextInt(codeList.size)
        val code: String = codeList.get(random)
        text = code
    }

    private val paint = Paint()
}