package com.zh.younger.shareschool.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.EditText
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.zh.younger.shareschool.R
import com.zh.younger.shareschool.util.ToastUtil

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var usernameView : EditText
    private lateinit var passwordView : EditText
    override fun onClick(v: View) {
        when(v.id){
            R.id.login -> {
                //登录
                val user = BmobUser()
                user.username = usernameView.text.toString()
                user.setPassword(passwordView.text.toString())
                user.login(object : SaveListener<BmobUser>(){
                    override fun done(p0: BmobUser?, p1: BmobException?) {
                        if(p0 != null){
                            ToastUtil.show(this@LoginActivity,"登陆成功")
                            val currentUser = BmobUser.getCurrentUser()
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            intent.putExtra("userInfo",currentUser)
                            startActivity(intent)
                            this@LoginActivity.finish()
                        }else{
                            when(p1!!.errorCode){
                                301 ->  ToastUtil.show(this@LoginActivity,"邮箱格式错误")
                                304 ->  ToastUtil.show(this@LoginActivity,"用户名或密码为空")
                            }
                            p1.printStackTrace()
                        }
                    }

                })
            }
            R.id.userMustNeed -> {
                //用户须知,找人来写
                val alert = AlertDialog.Builder(this)
                        .setMessage("test")
                        .create()
                alert.show()
            }
            R.id.register -> {
                //注册
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        findViews()
    }

    private fun findViews() {
        usernameView = findViewById(R.id.username) as EditText
        passwordView = findViewById(R.id.password) as EditText
        findViewById(R.id.login).setOnClickListener(this)
        findViewById(R.id.userMustNeed).setOnClickListener(this)
        findViewById(R.id.login).setOnClickListener(this)
    }
}
