package com.zh.younger.mylibrary.util

import android.content.Context

/**
 *
 */
object DPUtil {
    /*dp to px*/
    fun dp2px(ctx : Context, dp : Int) = dp * ctx.resources.displayMetrics.density
    /*px to dp*/
    fun px2dp(ctx : Context, px : Int) = px / ctx.resources.displayMetrics.density
}