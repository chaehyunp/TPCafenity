package com.ch96.tpcafenity.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ch96.tpcafenity.databinding.ActivityLoginBinding
import com.kakao.util.maps.helper.Utility

class LoginActivity : AppCompatActivity() {

    val binding:ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //로그인버튼
        binding.btnLogin.setOnClickListener { clickLoginBtn() }

        //회원가입버튼
        binding.btnSignup.setOnClickListener { clickSignupBtn() }

        //라디오버튼(자동로그인,아이디저장)
        autoLogin()
        saveId()

        //간편로그인
        binding.btnLoginKakao.setOnClickListener { clickKakaoBtn() }
        binding.btnLoginGoogle.setOnClickListener { clickGoogleBtn() }
        binding.btnLoginNaver.setOnClickListener { clickNaverBtn() }
    }

    private fun clickLoginBtn() {
        startActivity(Intent(this, MainActivity::class.java))

        //카카오 키 해시 값 얻어오기
//        val keyHash:String = Utility.getKeyHash(this)
//        Log.i("keyHash", keyHash)

        finish()
    }
    private fun clickSignupBtn() {
        startActivity(Intent(this, SignupActivity::class.java))
    }

    private fun autoLogin() {
        binding.radioBtnAutoLogin.isChecked()
    }
    private fun saveId() {
        binding.radioBtnSaveId.isChecked()
    }

    private fun clickKakaoBtn() {}
    private fun clickGoogleBtn() {}
    private fun clickNaverBtn() {}
}