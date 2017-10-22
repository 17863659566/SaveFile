package com.zh.younger.shareschool.util

import android.content.Context

/**
 *
 */
class DPUtil( ctx : Context) {
     private val density : Float = ctx.resources.displayMetrics.density
    /*dp to px*/
    fun dp2px(dp : Int) = dp * density
    /*px to dp*/
    fun px2dp(px : Int) = px / density
}