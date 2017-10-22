package com.zh.younger.mylibrary.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

/*
* 希望的是可以传递resId和url(url)目前没有做
* 通过resId创建需要的View
*
* 【注1】：不要在destory中销毁View，不然可能会造成空白
* 【注2】：在addView前要判断View是不是有parent，不然会报错
* */
object ViewPagerAdapter : PagerAdapter() {
    private  lateinit var context: Context
    private  lateinit var resIds : List<Int>
    private lateinit var mViews : ArrayList<View>
    /*
    * 从layoutRes加载View
    * */
    fun fromlayoutResIds(context: Context, resIds: List<Int>): ViewPagerAdapter {
        this.context = context
        this.resIds = resIds
        mViews = ArrayList(ViewPagerAdapter.resIds.size)
        (0 until resIds.size)
                .map { View.inflate(context, resIds[it], null) }
                .forEach { mViews.add(it) }
        Log.i("Thread", mViews.size.toString())
        return this

    }
    /*
    *
    * 从ImageRes直接加载View
    * */
    fun fromImageRes(context: Context, resIds: List<Int>): ViewPagerAdapter{
       this.context = context
        this.resIds = resIds
        mViews = ArrayList(ViewPagerAdapter.resIds.size)
        for (i in 0 until resIds.size){
            val view = ImageView(context)
            view.setImageResource(resIds[i])
            mViews.add(view)
        }

        return this

    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean = view == `object`

    override fun getCount(): Int = Integer.MAX_VALUE

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        /*没办法直接进行左划，如果需要可以使用手势的监听来做*/
        val reality = position % resIds.size
        Log.i("position",position.toString())
        val view = mViews[reality]
        (view.parent as ViewGroup?)?.removeView(view)

        container!!.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
    }

}