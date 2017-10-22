package com.zh.younger.shareschool.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.DebugUtils
import cn.bmob.v3.Bmob
import cn.bmob.v3.BmobUser
import com.zh.younger.shareschool.R
import java.util.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Bmob.initialize(this,"db2d676edbb07e4c265192aa809142ec")
        val loginActivity = Intent(this, LoginActivity::class.java)
        val mainActivityIntent = Intent(this, MainActivity::class.java)
        val timer = Timer()
        val task = object :TimerTask(){
            override fun run() {
                if(BmobUser.getCurrentUser() == null ) startActivity(loginActivity) else startActivity(mainActivityIntent)
                this@SplashActivity.finish()
            }
        }
        //TODO 加载时间调整
        timer.schedule(task,0)

    }
}
