package com.ch96.tpcafenity.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    val binding:ActivitySignupBinding by lazy { ActivitySignupBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //툴바 설정
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_action_back)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //버튼 누르지 않고 타이핑시작시 매번 검사하도록
//        //닉네임 유효성 검사
//        if(nick.length > 8) {
//            binding.tvWrongNick.visibility = View.VISIBLE
//            binding.etNick.requestFocus()
//            binding.etNick.selectAll() //입력을 시작하면 다시 invisible로 만들기
//        }
//
//        //이메일 유효성 검사
//        if(!(email.contains('@')&&email.contains('.'))) {
//            binding.tvWrongEmail.visibility = View.VISIBLE
//            binding.etEmail.requestFocus()
//            binding.etPassword.selectAll() //입력을 시작하면 다시 invisible로 만들기
//        }
//
//        //비밀번호 유효성 검사
//        if(password != passwordConfirm) {
//            binding.tvWrongPassword.visibility = View.VISIBLE
//            binding.etPasswordConfirm.selectAll() //입력을 시작하면 다시 invisible로 만들기
//        }

        //회원가입
        binding.btnSignup.setOnClickListener { clickSignup() }

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    private fun clickSignup() {

        var nick:String = binding.etNick.text.toString()
        var email:String = binding.etEmail.text.toString()
        var password:String = binding.etPassword.text.toString()
        var passwordConfirm:String = binding.etPasswordConfirm.text.toString()


        //ManinActivity로 이동하면서 stack에 있는 task 지우기
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)

    }
}