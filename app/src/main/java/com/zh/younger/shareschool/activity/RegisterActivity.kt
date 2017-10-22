package com.zh.younger.shareschool.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.zh.younger.shareschool.R
import com.zh.younger.shareschool.util.ToastUtil

class RegisterActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var usernameView : EditText
    private lateinit var passwordView : EditText
    override fun onClick(v: View) {
        when(v.id){
            R.id.register -> {
                //注册
                val user = BmobUser()
                user.username = usernameView.text.toString()
                user.setPassword(passwordView.text.toString())
                user.email = usernameView.text.toString()
                user.signUp(object : SaveListener<BmobUser>(){
                    override fun done(p0: BmobUser?, p1: BmobException?) {
                        if(p0 != null){
                            ToastUtil.show(this@RegisterActivity,"登陆成功，我们给您发送了一封确认邮件，请您确认")
                            val currentUser = BmobUser.getCurrentUser()
                            val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                            intent.putExtra("userInfo",currentUser)
                            startActivity(intent)
                            this@RegisterActivity.finish()
                        }else{
                            when(p1!!.errorCode){
                                301 ->  ToastUtil.show(this@RegisterActivity,"邮箱格式错误")
                                304 ->  ToastUtil.show(this@RegisterActivity,"用户名或密码为空")
                            }
                            p1.printStackTrace()
                        }
                    }

                })
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        findViews()
    }
    private fun findViews() {
        usernameView = findViewById(R.id.username) as EditText
        passwordView = findViewById(R.id.password) as EditText
        findViewById(R.id.register).setOnClickListener(this)
    }
}
