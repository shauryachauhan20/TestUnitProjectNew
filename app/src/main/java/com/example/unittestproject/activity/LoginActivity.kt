package com.example.unittestproject.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.unittestproject.R
import com.example.unittestproject.databinding.ActivityLoginBinding
import com.example.unittestproject.util.LoginUtil.validateLoginInput

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.btnValidate.setOnClickListener {
            val validate = validateLoginInput(
                binding.etUserName.text.toString(),
                binding.etEmail.text.toString(),
                binding.etPass.text.toString()
            )
            if (validate == null) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else Toast.makeText(this@LoginActivity, validate, Toast.LENGTH_SHORT).show()
        }
    }
}