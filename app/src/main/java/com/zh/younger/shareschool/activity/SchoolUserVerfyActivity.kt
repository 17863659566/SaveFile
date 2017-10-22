package com.zh.younger.shareschool.activity

import android.os.*
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.ImageView
import java.util.concurrent.TimeUnit
import android.graphics.BitmapFactory
import android.util.Log
import android.view.View
import android.widget.Toast
import com.zh.younger.shareschool.R
import okhttp3.*
import org.jsoup.Jsoup
import java.io.IOException


class SchoolUserVerfyActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var checkCode : ImageView
    private lateinit var username : EditText
    private lateinit var password : EditText
    private lateinit var checkCodeView : EditText
    private lateinit var requestFirst : Request
    private lateinit var requestSecond: Request
    private lateinit var requestThird : Request
    private lateinit var form : FormBody
    private val map = HashMap<String,String>()
    private val client = OkHttpClient.Builder().apply {
        connectTimeout(3,TimeUnit.SECONDS)
        retryOnConnectionFailure(true)
        cookieJar(object : CookieJar{
            private val cookieStore = HashMap<String,MutableList<Cookie>>()
            override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
                cookieStore.put(url.host(), cookies)
            }

            override fun loadForRequest(url: HttpUrl?): MutableList<Cookie> =
                    cookieStore[url!!.host()] ?: ArrayList()

        })
    }.build()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verfy)
        findViews()



    }

    private fun findViews() {
        checkCode = findViewById(R.id.checkCode) as ImageView
        username = findViewById(R.id.username) as EditText
        password = findViewById(R.id.password) as EditText
        checkCodeView = findViewById(R.id.checkCodeText) as EditText
        findViewById(R.id.submit).setOnClickListener(this)
        checkCode.setOnClickListener(this)
        checkCodeView.setOnClickListener(this)


    }

    override fun onStart() {
        super.onStart()
        createRequest()
        requestParams()
    }

    private fun requestParams() {

        val callForParams = client.newCall(requestSecond)
        callForParams.enqueue(object : Callback{
            override fun onFailure(call: Call?, e: IOException?) {

            }

            override fun onResponse(call: Call?, response: Response) {
                val source = response.body()!!.string()
                val document = Jsoup.parse(source)
                val __VIEWSTATE = document.select("input[name=__VIEWSTATE]").`val`()
                val __VIEWSTATEGENERATOR = document.select("input[name=__VIEWSTATEGENERATOR]").`val`()
                map.put("__VIEWSTATE",__VIEWSTATE)
                map.put("__VIEWSTATEGENERATOR",__VIEWSTATEGENERATOR)
            }

        })
    }

    private fun requestCheckCode() {
        val callForImage = client.newCall(requestFirst)
        callForImage.enqueue(object : Callback{
            override fun onFailure(call: Call?, e: IOException?) {
                runOnUiThread {
                    Toast.makeText(applicationContext,"网络错误",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call?, response: Response) {
                val bytes = response.body()!!.bytes()
                val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                runOnUiThread {
                    checkCode.setImageBitmap(bitmap)
                }
            }

        })
    }

    override fun onResume() {
        super.onResume()
        requestCheckCode()
    }
    private fun createRequest() {
        requestFirst = Request.Builder().apply {
            url("http://210.44.64.154/CheckCode.aspx")
        }.build()
        requestSecond = Request.Builder().apply {
            url("http://210.44.64.154/default2.aspx")
        }.build()


    }
    override fun onClick(v: View) {
        when(v.id){
            R.id.checkCode -> {
                requestCheckCode()
            }
            R.id.submit -> {

                requestLogin(username.text.toString(),password.text.toString(),checkCodeView.text.toString())
            }
            R.id.checkCodeText -> {
                requestCheckCode()
                checkCodeView.setOnClickListener(null)
            }
        }
    }

    private fun requestLogin(username: String, password: String, checkCode: String) {

        form = FormBody.Builder().apply {
            for((key,value) in map){
                add(key,value)
            }
            add("txtUserName",username)
            add("TextBox2",password)
            add("txtSecretCode",checkCode)
            add("RadioButtonList1","%D1%A7%C9%FA")
            add("Button1","")
            add("lbLanguage","")
            add("hidPdrs","")
            add("hidsc","")
        }.build()
        requestThird = Request.Builder().apply {
            url("http://210.44.64.154/default2.aspx")
            method("POST",form)
        }.build()
        val callForLogin = client.newCall(requestThird)
        callForLogin.enqueue(object : Callback{
            override fun onFailure(call: Call?, e: IOException?) {
                runOnUiThread {
                    Toast.makeText(applicationContext,"网络错误",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: Call?, response: Response) {
                val string = response.body()!!.string()
                Log.i("tag",response.headers().toMultimap().toString() + " code : " + response.code() + "\n source : \n" + string)
                val document = Jsoup.parse(string)
                val select = document.select("form[action=xs_main.aspx?xh=$username]")
                if(!select.isEmpty()){
                    runOnUiThread {
                        Toast.makeText(applicationContext,"欢迎您$username",Toast.LENGTH_SHORT).show()
                    }
                }else{
                    runOnUiThread {
                        Toast.makeText(applicationContext,"输入有误,请检查输入",Toast.LENGTH_SHORT).show()
                        requestCheckCode()
                    }
                }
            }

        })
    }

}
