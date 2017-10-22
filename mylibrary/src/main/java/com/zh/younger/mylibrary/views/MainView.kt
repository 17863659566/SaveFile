package com.zh.younger.mylibrary.views

import android.content.Context
import android.support.annotation.AttrRes
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.zh.younger.mylibrary.util.MainViewSettings
import com.zh.younger.mylibrary.R
import com.zh.younger.mylibrary.adapter.RecyclerViewAdapter

/**
 * 主要的登录界面的组织
 */
class MainView : RelativeLayout {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        settings.handleAttrs(context,attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        settings.handleAttrs(context,attrs)
    }
    /*展示分类*/
    private var mToolbar : Toolbar? = null
    /*展示活动*/
    private  var mPager : ViewPager? = null
    /*展示数据项*/
    private  var mRecycler: RecyclerView? = null
    /*用来设置属性*/
    private val settings = MainViewSettings()

    override fun onFinishInflate() {
        super.onFinishInflate()
        val view = LayoutInflater.from(context).inflate(R.layout.layout_main, null)
        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        addView(view,params)
        mToolbar = findViewById(R.id.toolbar) as Toolbar
        mPager = findViewById(R.id.viewPager) as ViewPager
        mRecycler = findViewById(R.id.recyclerView) as RecyclerView

        /*设置Toolbar的布局文件*/
        if (settings.toolbarLayoutRes != -1){
            if (mToolbar != null){
                mToolbar!!.addView(
                        LayoutInflater.from(context).inflate(settings.toolbarLayoutRes,null),
                        ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,android.R.attr.actionBarSize ))
            }
        }
    }


    /*设置viewPager的适配器*/
    fun registe(onPagerAdapter: PagerAdapter){
            mPager!!.adapter = onPagerAdapter
    }
    /*设置RecyclerView的适配器*/
   fun registe(recyclerViewAdapter : RecyclerViewAdapter,layoutManager: RecyclerView.LayoutManager){
        mRecycler!!.adapter = recyclerViewAdapter
        mRecycler!!.layoutManager = layoutManager
    }
    /**
     * 这些方法必须进行重写，否则应该抛出编译异常
     * 这个只是作为文档存在
     */
    /*
    interface OnPagerAdapter{
        fun isViewFromObject(view: View?, `object`: Any?): Boolean
        fun getCount(): Int
        fun instantiateItem(container: ViewGroup?, position: Int): Any
        fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?)
    }*/

    fun getViewPager() : ViewPager = mPager!!
    fun getRecyclerView() : RecyclerView = mRecycler!!
    fun getToolbar() : Toolbar = mToolbar!!


}