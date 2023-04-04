package com.ch96.tpcafenity.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
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

        //입력값 확인
        inputError()

        //회원가입
        binding.btnSignup.setOnClickListener { clickSignup() }

    }

    //입력값 부적합할 경우 (허용 글자수 검사 / 이메일형태 검사 (@.포함)/ 비밀번호 확인 검사)
    private fun inputError() {

        //닉네임 글자수 초과시
        binding.etNick.addTextChangedListener(object:TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.etNick.length() > 8)
                    binding.layoutNick.error = "닉네임의 글자수 최대 허용치를 초과했습니다."
                else binding.layoutNick.error = null
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        //비밀번호 글자수 초과시
        binding.etPassword.addTextChangedListener(object:TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.etPassword.length() > 16)
                    binding.layoutPassword.error = "비밀번호 글자수 최대 허용치를 초과했습니다."
                else binding.layoutPassword.error = null
            }

            override fun afterTextChanged(p0: Editable?) {}

        })

        //이메일 형식 확인
        binding.etEmail.addTextChangedListener(object:TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!(binding.etEmail.text!!.contains('@')&&binding.etEmail.text!!.contains('.')))
                    binding.layoutEmail.error = "올바른 이메일 형태를 입력해주세요."
                else binding.layoutEmail.error = null
            }

            override fun afterTextChanged(p0: Editable?) {}

        })

        //비밀번호 - 비밀번호 확인 문자 일치 여부
        binding.etPassword.addTextChangedListener(object:TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.etPassword.text != binding.etPasswordConfirm.text)
                    binding.layoutPasswordConfirm.error = "비밀번호가 다릅니다."
                else binding.layoutPasswordConfirm.error = null
            }

            override fun afterTextChanged(p0: Editable?) {}

        })

    }

    private fun clickSignup() {

        //서버에 전송할 데이터 [nick, email, password]
        var nick:String = binding.etNick.text.toString()
        var email:String = binding.etEmail.text.toString()
        var password:String = binding.etPassword.text.toString()
        //비밀번호 확인
        var passwordConfirm:String = binding.etPasswordConfirm.text.toString()

        //비밀번호 - 비밀번호 확인 검사
        if (password != passwordConfirm) {

            return

        } else {

        }

        //ManinActivity로 이동하면서 stack에 있는 task 지우기
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)

    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }


}