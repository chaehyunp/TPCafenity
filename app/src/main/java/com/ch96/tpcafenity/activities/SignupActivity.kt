package com.ch96.tpcafenity.activities

import android.R.string
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.databinding.ActivitySignupBinding
import com.ch96.tpcafenity.model.UserAccount
import com.ch96.tpcafenity.network.RetrofitHelper
import com.ch96.tpcafenity.network.RetrofitService
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.regex.Pattern


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
        binding.btnSignupActive.setOnClickListener { clickSignup() }

    }

    //입력값 부적합할 경우 (허용 글자수 검사 / 이메일형태 검사 (@.포함)/ 비밀번호 확인 검사)
    private fun inputError() {


        //닉네임 글자수 초과 및 특수문자 포함 여부
        binding.etNick.addTextChangedListener(object:TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if (binding.etNick.length() > 8)
                    binding.layoutNick.error = "닉네임의 글자수 최대 허용치를 초과했습니다."
                else binding.layoutNick.error = null

//                if (hasSpecialCharacter(binding.etNick.text.toString()))
//                    binding.layoutNick.error = "특수문자는 포함할 수 없습니다."
//                else binding.layoutNick.error =null
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

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if(!Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text.toString()).matches())
                    binding.layoutEmail.error = "올바른 이메일 형태를 입력해주세요."
                else binding.layoutEmail.error = null
                checkError()
            }
            
            override fun afterTextChanged(p0: Editable?) {}
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

    // 특수문자 존재 여부를 확인하는 메소드
//    private fun hasSpecialCharacter(input: String): Boolean {
//        val exceptPattern = "^(?=.*[0-9])(?=.*[$@$!%*#?&.])[[0-9]$@$!%*#?&.]{8,20}$" //숫자, 특수문자
//        Pattern.compile(exceptPattern).matcher(exceptPattern)
//        return Pattern.matches(exceptPattern, input)
//
//    }

    //Error값이 null일 경우 && 모든 입력창에 입력되었을경우 -> 회원가입 버튼 활성화 메소드
    private fun checkError() {

        var nick = binding.etNick.text.toString()
        var email = binding.etEmail.text.toString()
        var password:String = binding.etPassword.text.toString()
        var passwordConfirm:String = binding.etPassword.text.toString()

        if (binding.layoutNick.error == null && binding.layoutEmail.error == null && binding.layoutPassword.error == null && binding.layoutPasswordConfirm.error == null
            && nick != "" && email != "" && password != "" && passwordConfirm != "") {
            binding.btnSignupDisabled.visibility = View.INVISIBLE
            binding.btnSignupActive.visibility = View.VISIBLE
        }
    }

    var baseUrl:String = "http://cafenity.dothome.co.kr"

    private fun clickSignup() {

        //서버에 전송할 데이터 [nick, email, password]
        var nick:String = binding.etNick.text.toString()
        var email:String = binding.etEmail.text.toString()
        var password:String = binding.etPassword.text.toString()

        //inputError()에서가 에러가 없는지 확인
        //if (binding.layoutNick.error == null && binding.layoutEmail.error == null && binding.layoutPassword.error == null && binding.layoutPasswordConfirm.error == null) {
            //모든 항목의 error값이 null = 올바르게 기입한 경우 -> 가입데이터 저장 및 메인화면 이동

            val retrofit:Retrofit = RetrofitHelper.getRetrofitInstance(baseUrl)
            val retrofitService = retrofit.create(RetrofitService::class.java)
            retrofitService.saveEmailAccount(nick, email, password).enqueue(object : Callback<UserAccount>{
                override fun onResponse(call: Call<UserAccount>, response: Response<UserAccount>) {
                    Toast.makeText(this@SignupActivity, "${response.body()?.nick}님 반갑습니다!", Toast.LENGTH_SHORT).show()
                }


                override fun onFailure(call: Call<UserAccount>, t: Throwable) {
                    Toast.makeText(this@SignupActivity, "계정 가입 실패", Toast.LENGTH_SHORT).show()
                }

            })

            //ManinActivity로 이동하면서 stack에 있는 task 지우기
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        //}

    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }


}