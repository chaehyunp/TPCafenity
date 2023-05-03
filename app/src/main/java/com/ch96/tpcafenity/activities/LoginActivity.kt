package com.ch96.tpcafenity.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.ch96.tpcafenity.GV
import com.ch96.tpcafenity.databinding.ActivityLoginBinding
import com.ch96.tpcafenity.model.LoginUserData
import com.ch96.tpcafenity.network.RetrofitHelper
import com.ch96.tpcafenity.network.RetrofitService
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    val binding:ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    var baseUrl:String = "http://testhue96.dothome.co.kr/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var pref = getSharedPreferences("Data", MODE_PRIVATE)
        var savedEmail = pref.getString("email","")
        var savedPassword = pref.getString("password", "")

        //sharedPreferences에 저장되어있는 값이 있다면
        if (savedEmail != "") {
            binding.etEmail.text = Editable.Factory.getInstance().newEditable(savedEmail)
            binding.etPw.text = Editable.Factory.getInstance().newEditable(savedPassword)
            clickLoginBtn()
        }


        //로그인버튼
        binding.btnLogin.setOnClickListener { clickLoginBtn() }

        //회원가입버튼
        binding.btnSignup.setOnClickListener { clickSignupBtn() }

        //간편로그인
        binding.btnLoginKakao.setOnClickListener { clickKakaoBtn() }
    }

    private fun clickLoginBtn() {
        //소프트 키보드 없애기
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken,0)

        //서버에 전송할 데이터 [email, password]
        val emailUser = mutableMapOf<String, String>()
        emailUser["email"] = binding.etEmail.text.toString()
        emailUser["password"] = binding.etPw.text.toString()

        //입력한 회원정보와 일치하는 정보가 DB에 있는지 확인
        val retrofit = RetrofitHelper.getRetrofitInstance(baseUrl)
        val retrofitService = retrofit.create(RetrofitService::class.java)
        retrofitService.loginEmailAccount(emailUser).enqueue(object : Callback<MutableList<LoginUserData>>{
            override fun onResponse(call: Call<MutableList<LoginUserData>>, response: Response<MutableList<LoginUserData>>) {
                var res = response.body()

                if (res != null) {
                    //전역변수에 유저 닉네임 저장
                    GV.loginUserNo = res[0].no
                    GV.loginUserNick = res[0].nick

                    //sharedPreference에 저장
                    saveAccount()

                    Toast.makeText(this@LoginActivity, "${GV.loginUserNick}님, 반갑습니다! ", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                } else Toast.makeText(this@LoginActivity, "일치하는 회원정보가 없습니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<MutableList<LoginUserData>>, t: Throwable) {
                Log.i("what_login_failed","$t")
            }
        })

        //카카오 키 해시 값 얻어오기
        val keyHash:String = Utility.getKeyHash(this)
        Log.i("kakao_keyHash", keyHash)
    }

    private fun clickSignupBtn() {
        startActivity(Intent(this, SignupActivity::class.java))
    }


    private fun clickKakaoBtn() {
        //카카오 로그인 공동 callback 함수
        val callback:(OAuthToken?, Throwable?)->Unit = { token, error ->
            if (token != null) {
                Toast.makeText(this, "카카오 로그인 성공", Toast.LENGTH_SHORT).show()

                //사용자 정보 요청 [1.회원번호, 2.이메일주소]
                UserApiClient.instance.me { user, error ->
                    if (user != null) {
                        var id:String = user.id.toString()
                        var email:String = user.kakaoAccount?.email?:""

                        Toast.makeText(this, "$email", Toast.LENGTH_SHORT).show()
                        GV.kakaoEmail = email

                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                }
            } else {
                Toast.makeText(this, "카카오 로그인 실패", Toast.LENGTH_SHORT).show()
            }
        }
        //카카오톡이 설치되어있으면 카톡으로 로그인, 없으면 카카오계정 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }

    fun saveAccount(){
        var email = binding.etEmail.text.toString()
        var password = binding.etPw.text.toString()

        var editor = getSharedPreferences("Data", MODE_PRIVATE).edit()
        editor.putString("email", email)
        editor.putString("password", password)
        editor.commit()
    }

}