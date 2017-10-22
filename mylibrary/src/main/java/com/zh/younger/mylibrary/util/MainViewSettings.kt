package com.zh.younger.mylibrary.util

import android.content.Context
import android.util.AttributeSet
import com.zh.younger.mylibrary.R

/*用来处理自定义的属性*/
class MainViewSettings {
     var toolbarLayoutRes : Int = -1
    fun handleAttrs(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MainView)

        toolbarLayoutRes = typedArray.getResourceId(R.styleable.MainView_toolbarLayoutRes, -1)
        if (toolbarLayoutRes == -1){
            //设置默认的布局
        }
    }
}