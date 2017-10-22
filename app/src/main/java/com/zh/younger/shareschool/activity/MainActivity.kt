package com.zh.younger.shareschool.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.zh.younger.mylibrary.adapter.RecyclerViewAdapter
import com.zh.younger.mylibrary.views.MainView
import com.zh.younger.shareschool.R
import com.zh.younger.mylibrary.adapter.ViewPagerAdapter
import com.zh.younger.mylibrary.data.DisplayItem


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViews()


    }

    private fun findViews() {
        val mainView = findViewById(R.id.mainView) as MainView

        /*测试数据*/
        val data = listOf(
              R.drawable.ali_1,
              R.drawable.ali_2,
              R.drawable.ali_3
        )
        mainView.registe(ViewPagerAdapter.fromImageRes(this,data))

        /*测试数据*/
        val items =  listOf(
           DisplayItem("阿狸",R.drawable.ali_1,"喜欢阿狸","真的特别喜欢"),
           DisplayItem("阿狸",R.drawable.ali_2,"喜欢阿狸","真的特别喜欢"),
           DisplayItem("阿狸",R.drawable.ali_3,"喜欢阿狸","真的特别喜欢"),
           DisplayItem("阿狸",R.drawable.ali_1,"喜欢阿狸","真的特别喜欢"),
           DisplayItem("阿狸",R.drawable.ali_2,"喜欢阿狸","真的特别喜欢"),
           DisplayItem("阿狸",R.drawable.ali_3,"喜欢阿狸","真的特别喜欢"),
           DisplayItem("阿狸",R.drawable.ali_1,"喜欢阿狸","真的特别喜欢"),
           DisplayItem("阿狸",R.drawable.ali_2,"喜欢阿狸","真的特别喜欢"),
           DisplayItem("阿狸",R.drawable.ali_3,"喜欢阿狸","真的特别喜欢")

        )
        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL

        mainView.registe(RecyclerViewAdapter.create(this,R.layout.recycler_item,items),manager)
    }
}

