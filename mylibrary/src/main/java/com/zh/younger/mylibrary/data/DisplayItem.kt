package com.zh.younger.mylibrary.data

/**
 * 在RecyclerView中展示的item的数据封装
 * @param username 用户名信息
 * @param headerImageRes 用户头像展示
 * @param itemTitle 信息标题
 * @param itemContent 信息内容
 */
data class DisplayItem( val username : String,  val headerImageRes: Int,  val itemTitle : String,  val itemContent:String)