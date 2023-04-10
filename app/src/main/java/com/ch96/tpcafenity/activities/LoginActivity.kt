package com.ch96.tpcafenity.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ch96.tpcafenity.GV
import com.ch96.tpcafenity.databinding.ActivityLoginBinding
import com.ch96.tpcafenity.model.UserAccount
import com.ch96.tpcafenity.network.RetrofitHelper
import com.ch96.tpcafenity.network.RetrofitService
import com.kakao.util.maps.helper.Utility
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    val binding:ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    var baseUrl:String = "http://testhue96.dothome.co.kr/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // SKIP
        binding.btnSkip.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

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

        //서버에 전송할 데이터 [email, password]
        val emailUser = mutableMapOf<String, String>()
        emailUser["email"] = binding.etEmail.text.toString()
        emailUser["password"] = binding.etPw.text.toString()

        //입력한 회원정보와 일치하는 정보가 DB에 있는지 확인
        val retrofit = RetrofitHelper.getRetrofitInstance(baseUrl)
        val retrofitService = retrofit.create(RetrofitService::class.java)
        retrofitService.loginEmailAccount(emailUser).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                var res = response.body()
//                Log.i("what_loginCheck", "$res")
                if (res != null) {
                    //전역변수에 유저 닉네임 저장
                    GV.loginUserNick = res
                    Toast.makeText(this@LoginActivity, "반갑습니다!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                } else Toast.makeText(this@LoginActivity, "일치하는 회원정보가 없습니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
            }
        })

        //카카오 키 해시 값 얻어오기
//        val keyHash:String = Utility.getKeyHash(this)
//        Log.i("keyHash", keyHash)
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