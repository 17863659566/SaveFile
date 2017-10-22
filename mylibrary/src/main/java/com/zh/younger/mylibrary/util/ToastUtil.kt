package com.zh.younger.mylibrary.util

import android.content.Context
import android.view.Gravity
import android.widget.Toast


 object ToastUtil{
     fun show(context : Context,text : CharSequence,long : Int = Toast.LENGTH_SHORT){
         val toast = Toast.makeText(context, text, long)
         toast.setGravity(Gravity.CENTER_HORIZONTAL,0,40)
         toast.show()
     }
 }