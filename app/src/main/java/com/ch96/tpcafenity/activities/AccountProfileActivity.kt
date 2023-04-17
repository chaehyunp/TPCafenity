package com.ch96.tpcafenity.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.ch96.tpcafenity.GV
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.adapters.ListCommunityAdapter
import com.ch96.tpcafenity.databinding.ActivityAccountProfileBinding
import com.ch96.tpcafenity.model.CommunityList
import com.ch96.tpcafenity.model.LoginUserAccount
import com.ch96.tpcafenity.network.RetrofitHelper
import com.ch96.tpcafenity.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountProfileActivity : AppCompatActivity() {

    val binding:ActivityAccountProfileBinding by lazy { ActivityAccountProfileBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_action_back)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.tvEditActive.setOnClickListener { clickEditBtn() }

        inputError()
        checkNick()
        loadData()
    }

    fun clickEditBtn() {
        //소프트 키보드 없애기
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken,0)

        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_edit, null)
        val builder = AlertDialog.Builder(this).setView(dialogView)
        val editAlertDialog = builder.show()
        val btnNo = dialogView.findViewById<TextView>(R.id.tv_no)
        btnNo.setOnClickListener {
            editAlertDialog.dismiss()
        }
        val btnYes = dialogView.findViewById<TextView>(R.id.tv_yes)
        btnYes.setOnClickListener {
            //수정할 계정 고유넘버 및 저장할 데이터
            var editedAccount = mutableMapOf<String, String>()
            editedAccount["no"] = GV.loginUserNo!!
            editedAccount["nick"] = binding.etNick.text.toString()
            editedAccount["password"] = binding.etPassword.text.toString()

            val retrofit = RetrofitHelper.getRetrofitInstance(GV.baseUrl)
            val retrofitService = retrofit.create(RetrofitService::class.java)
            retrofitService.saveEditedAccount(editedAccount).enqueue(object : Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    GV.loginUserNick = editedAccount["nick"]
                    Toast.makeText(this@AccountProfileActivity, "수정되었습니다.", Toast.LENGTH_SHORT).show()
                    editAlertDialog.dismiss()
                    finish()
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Toast.makeText(this@AccountProfileActivity, "수정에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    fun loadData() {
        //DB에 있는 계정 데이터 받아오기
        val retrofit = RetrofitHelper.getRetrofitInstance(GV.baseUrl)
        val retrofitService = retrofit.create(RetrofitService::class.java)
        retrofitService.getAccountProfile(GV.loginUserNo!!).enqueue(object : Callback<MutableList<LoginUserAccount>> {
            override fun onResponse(
                call: Call<MutableList<LoginUserAccount>>, response: Response<MutableList<LoginUserAccount>>) {
                var res = response.body()
                binding.etNick.text = Editable.Factory.getInstance().newEditable(res?.get(0)?.nick)
                binding.etEmail.text = Editable.Factory.getInstance().newEditable(res?.get(0)?.email)
                binding.etPassword.text = Editable.Factory.getInstance().newEditable(res?.get(0)?.password)
                binding.etPasswordConfirm.text = Editable.Factory.getInstance().newEditable(res?.get(0)?.password)
            }
            override fun onFailure(call: Call<MutableList<LoginUserAccount>>, t: Throwable) {
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    ////////////////////////계정수정시 체크사항 메소드//////////////////////////////////
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

    fun checkNick() {
        var nick = binding.etNick.text.toString()

        if (nick != GV.loginUserNick) {
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
    }


    private fun checkError() {

        var errorNick = binding.layoutNick.error
        var errorPW = binding.layoutPassword.error
        var errorPWC = binding.layoutPasswordConfirm.error

        var nick = binding.etNick.text.toString()
        var password:String = binding.etPassword.text.toString()
        var passwordConfirm:String = binding.etPassword.text.toString()

        if (errorNick == null && errorPW == null && errorPWC == null
            && nick != "" && password != "" && passwordConfirm != "") {
            binding.tvEditDisabled.visibility = View.INVISIBLE
            binding.tvEditActive.visibility = View.VISIBLE
        }
    }

}