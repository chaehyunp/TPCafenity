package com.ch96.tpcafenity.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ch96.tpcafenity.GV
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.databinding.ActivitySignupBinding
import com.ch96.tpcafenity.network.RetrofitHelper
import com.ch96.tpcafenity.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


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

        //입력값 확인
        inputError()

        //회원가입
        binding.tvSignupActive.setOnClickListener { clickSignup() }

    }

    //입력값 부적합할 경우 (허용 글자수 검사 / 이메일형태 검사 / 비밀번호 확인 검사)
    private fun inputError() {
        //닉네임 글자수 초과 및 특수문자 포함 여부
        binding.etNick.addTextChangedListener(object:TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkNick()
                checkError()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        //비밀번호 글자수 초과 및 비밀번호 확인 문자 일치 여부
        binding.etPassword.addTextChangedListener(object:TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.etPassword.length() > 15)
                    binding.layoutPassword.error = "비밀번호 글자수 최대 허용치를 초과했습니다."
                else binding.layoutPassword.error = null

                if (binding.etPasswordConfirm.text.toString()==(binding.etPassword.text.toString()))
                    binding.layoutPasswordConfirm.error = null
                else binding.layoutPasswordConfirm.error = "비밀번호가 다릅니다."

                checkError()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })

        //이메일 형식 확인
        binding.etEmail.addTextChangedListener(object:TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                checkEmail()
                checkError()
            }
        })

        //비밀번호 - 비밀번호 확인 문자 일치 여부
        binding.etPasswordConfirm.addTextChangedListener(object:TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.etPasswordConfirm.text.toString()==(binding.etPassword.text.toString()))
                    binding.layoutPasswordConfirm.error = null
                else binding.layoutPasswordConfirm.error = "비밀번호가 다릅니다."
                checkError()
            }
            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun hasSpecialCharacter(input: String): Boolean {

        TODO("닉네임에 숫자 특수문자 입력시 에러메시지")
//        val exceptPattern = "^(?=.*[0-9])(?=.*[$@$!%*#?&.])[[0-9]$@$!%*#?&.]{8,20}$" //숫자, 특수문자
//        Pattern.compile(exceptPattern).matcher(exceptPattern)
//        return Pattern.matches(exceptPattern, input)

    }

    private fun clickSignup() {

        //서버에 전송할 데이터 [nick, email, password]
        val emailUser = mutableMapOf<String, String>()
        emailUser["nick"] = binding.etNick.text.toString()
        emailUser["email"] = binding.etEmail.text.toString()
        emailUser["password"] = binding.etPassword.text.toString()

        val retrofit:Retrofit = RetrofitHelper.getRetrofitInstance(GV.baseUrl)
        val retrofitService = retrofit.create(RetrofitService::class.java)

        //DB 저장하기
        retrofitService.saveEmailAccount(emailUser).enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {

                val res = response.body()

                Toast.makeText(this@SignupActivity, "${emailUser["nick"]}님 반갑습니다!", Toast.LENGTH_SHORT).show()
                Log.i("what","$res")

                //ManinActivity로 이동하면서 stack에 있는 task 지우기
                val intent = Intent(this@SignupActivity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(this@SignupActivity, "계정 가입 실패", Toast.LENGTH_SHORT).show()
                Log.i("what", "${t.message}")
            }

        })

    }

    //Error값이 null일 경우 && 모든 입력창에 입력되었을경우 -> 회원가입 버튼 활성화 메소드
    private fun checkError() {

        var errorNick = binding.layoutNick.error
        var errorEmail = binding.layoutEmail.error
        var errorPW = binding.layoutPassword.error
        var errorPWC = binding.layoutPasswordConfirm.error

        var nick = binding.etNick.text.toString()
        var email = binding.etEmail.text.toString()
        var password:String = binding.etPassword.text.toString()
        var passwordConfirm:String = binding.etPassword.text.toString()

        if (errorNick == null && errorEmail == null && errorPW == null && errorPWC == null
            && nick != "" && email != "" && password != "" && passwordConfirm != "") {
            binding.tvSignupDisabled.visibility = View.INVISIBLE
            binding.tvSignupActive.visibility = View.VISIBLE
        }
    }

    fun checkNick() {
        var nick = binding.etNick.text.toString()

        //입력한 회원정보와 일치하는 정보가 DB에 있는지 확인
        val retrofit = RetrofitHelper.getRetrofitInstance(GV.baseUrl)
        val retrofitService = retrofit.create(RetrofitService::class.java)
        retrofitService.checkNick(nick).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.body() != "0") binding.layoutNick.error = "이미 사용중인 닉네임입니다."
                else if (binding.etNick.length() > 8) binding.layoutNick.error = "닉네임의 글자수 최대 허용치를 초과했습니다."
                else binding.layoutNick.error = null

                checkError()
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
            }
        })
    }

    fun checkEmail() {
        var email = binding.etEmail.text.toString()

        //입력한 회원정보와 일치하는 정보가 DB에 있는지 확인
        val retrofit = RetrofitHelper.getRetrofitInstance(GV.baseUrl)
        val retrofitService = retrofit.create(RetrofitService::class.java)
        retrofitService.checkEmail(email).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.body() != "0") binding.layoutEmail.error = "이미 가입된 이메일입니다."
                else if(!Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text.toString()).matches()) binding.layoutEmail.error = "올바른 이메일 형태를 입력해주세요."
                else binding.layoutEmail.error = null
                checkError()
            }
            override fun onFailure(call: Call<String>, t: Throwable) {
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }


}