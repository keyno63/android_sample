package jp.co.who.kthelloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

class HttpActivity : AppCompatActivity() {

    val url: String = "http://example.com"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)
    }

    fun sendAsyncHttp() {
        // 非同期処理
        "https://www.casareal.co.jp/".httpGet().response { request, response, result ->
            when (result) {
                is Result.Success -> {
                    // レスポンスボディを表示
                    println("非同期処理の結果：" + String(response.data))
                }
                is Result.Failure -> {
                    println("通信に失敗しました。")
                }
            }
        }
    }

    fun sendSyncHttp(url: String): String {
        val triple = url.httpGet().response()
        return String(triple.second.data)
    }

    fun syncHttp(view: View) {
        val textView: TextView = findViewById(R.id.textView)
        val str = sendSyncHttp(url)
        println(str)
        textView.setText(str)
    }
}
