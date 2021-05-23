package kz.example.mvpatterns.mvp

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kz.example.mvpatterns.R

class MainMVPActivity : AppCompatActivity(), MainMvpView {

    private val presenter = MainPresenter(this)

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
            presenter.onLoginClicked(etUsername?.text.toString(), etPassword?.text.toString())
        }
    }

    //region Overridden MVP Methods
    override fun showProgressBar() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar?.visibility = View.GONE
    }

    override fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    override fun onError(e: Throwable) {
        Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show()
    }
    //endregion


}