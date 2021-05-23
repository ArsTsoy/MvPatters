package kz.example.mvpatterns.mvc

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kz.example.mvpatterns.R

class MainMVCActivity : AppCompatActivity() {

    private val modelListener: MainModelListener = object: MainModelListener {
        override fun onLoginResult(result: Boolean) {
            Handler(Looper.getMainLooper()).post { // перенос вызова кода в главный поток (getMainLooper())
                progressBar?.visibility = View.GONE
                Toast.makeText(this@MainMVCActivity, "RESULT: $result", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
    private val controller = MainController(modelListener)
    private var btnLogin: TextView? = null
    private var etUsername: EditText? = null
    private var etPassword: EditText? = null
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin = findViewById(R.id.tvLogin)
        etPassword = findViewById(R.id.password)
        etUsername = findViewById(R.id.username)
        progressBar = findViewById(R.id.loading)


        btnLogin?.setOnClickListener {
            Log.i("myMain", "click")
            progressBar?.visibility = View.VISIBLE
            controller.onLoginClicked(etUsername?.text.toString(), etUsername?.text.toString())
        }
    }


}