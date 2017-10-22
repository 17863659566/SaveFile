package com.zh.younger.mylibrary.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.zh.younger.mylibrary.data.DisplayItem
import com.zh.younger.mylibrary.util.DPUtil
import com.zh.younger.mylibrary.util.ToastUtil

/**
 * 创建RecyclerView的Adapter
 */
object RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    /*
    * 用来加载View
    * */
    private lateinit var context : Context
    /*
    * View 的 resourse id
    * */
    private var itemRes : Int = 0
    /*要填充的数据集合*/
    private lateinit var data : List<DisplayItem>
    fun create(context:Context,itemRes:Int,data : List<DisplayItem>):RecyclerViewAdapter{
        this.context = context
        this.itemRes = itemRes
        this.data = data
        return this
    }
    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val value = data[position]
        val bitmap = BitmapFactory.decodeResource(context.resources, value.headerImageRes)
        var leftDrawable = BitmapDrawable(context.resources,bitmap)
        leftDrawable = processlDrawableLeft(leftDrawable)
        holder.usernameView.setCompoundDrawables(leftDrawable,null,null,null)
        holder.usernameView.text = value.username
        holder.itemTitleView.text = value.itemTitle
        holder.itemContentView.text = value.itemContent
    }

    private fun processlDrawableLeft(leftDrawable: BitmapDrawable) : BitmapDrawable {
        leftDrawable.bounds.apply {
            left = 0
            top = 0
            right = DPUtil.dp2px(context, 50).toInt()
            bottom = DPUtil.dp2px(context, 50).toInt()
        }
        return leftDrawable
    }
    //这个parent是xml文件中的ViewGroup,如果不指定应该是RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = ViewHolder(LayoutInflater.from(parent.context).inflate(itemRes,parent,false) as ViewGroup)

    /*
    * 保存
    * username 用户名信息
    * headerImageRes 用户头像展示
    * itemTitle 信息标题
    * itemContent 信息内容
    * */
    /**
     * item的第一项放置username
     * item的第二项放置itemTitle
     * item的第三项放置itemContent
     */
    class ViewHolder(view: ViewGroup) : RecyclerView.ViewHolder(view){
        val usernameView   = view.getChildAt(0) as TextView
        val itemTitleView = view.getChildAt(1) as TextView
        val itemContentView = view.getChildAt(2) as TextView
    }
}