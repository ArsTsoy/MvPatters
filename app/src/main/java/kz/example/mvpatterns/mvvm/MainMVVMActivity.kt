package kz.example.mvpatterns.mvvm

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kz.example.mvpatterns.R

class MainMVVMActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

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
            viewModel.onLoginClicked(etUsername?.text.toString(), etPassword?.text.toString())
        }

        viewModel.observeAuthResult().observe(this, { result ->
            if (result is AuthResult.Success) {
                Toast.makeText(this, result.text, Toast.LENGTH_LONG).show()
            } else if (result is AuthResult.Error) {
                Toast.makeText(this, result.error.toString(), Toast.LENGTH_LONG).show()
            }
        })
        viewModel.observeLoadingState().observe(this, { state ->
            if (state == LoadingState.LOADING) progressBar?.visibility = View.VISIBLE
            else if (state == LoadingState.NOT_LOADING) progressBar?.visibility = View.GONE
        })
    }


}