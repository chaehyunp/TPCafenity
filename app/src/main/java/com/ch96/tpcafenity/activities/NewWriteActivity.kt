package com.ch96.tpcafenity.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.ch96.tpcafenity.GV
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.adapters.RecyclerSelectedImageAdapter
import com.ch96.tpcafenity.databinding.ActivityNewWriteBinding
import com.ch96.tpcafenity.databinding.RecyclerItemNewWriteBinding
import com.ch96.tpcafenity.fragments.CommunityFragment
import com.ch96.tpcafenity.network.RetrofitHelper
import com.ch96.tpcafenity.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class NewWriteActivity : AppCompatActivity() {

    val binding:ActivityNewWriteBinding by lazy { ActivityNewWriteBinding.inflate(layoutInflater) }
    var baseUrl:String = "http://testhue96.dothome.co.kr/"

    //스피너
    lateinit var postTag:String
    var postTagNum:Int = 0

    //이미지
    var images:MutableList<Uri> = mutableListOf()
    var imageAdapter = RecyclerSelectedImageAdapter(this, images)
    var imageSize = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //툴바 설정
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_action_close)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //스피너 아답터 설정
        var data = resources.getStringArray(R.array.post_tag)
        var spinnerAdapter = ArrayAdapter<String>(this, R.layout.spinner_item_posttag,data)

        binding.spinner.adapter = spinnerAdapter
        binding.spinner.setSelection(0)
        spinnerAdapter.setDropDownViewResource(R.layout.spiner_dropdown_item_posttag)
        binding.spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //Log.i("what_spinner", "$p0, $p1, $p2, $p3")
                postTagNum = p2
                postTag = data[postTagNum]
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        binding.recycler.adapter = imageAdapter


        binding.tvDone.setOnClickListener { clickDone() }
        binding.btnAddImg.setOnClickListener { clickAddImage() }

    }

    fun clickedItem(pos:Int){
        images.removeAt(pos)
        imageAdapter.notifyDataSetChanged()
        imageSize--
        binding.tvPhotoNum.text= "$imageSize"
    }

    //이미지 선택 결과 런처
    @SuppressLint("NotifyDataSetChanged")
    var imagePickLauncher:ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ActivityResultCallback {
        if (it.resultCode != RESULT_CANCELED) {
            var intent = it.data
            var clipData = intent?.clipData

            if(clipData != null) {
                var size = clipData.itemCount
                for(i in 0 until size!!) images?.add(clipData?.getItemAt(i)?.uri!!)
                imageSize += size
                binding.tvPhotoNum.text = "$imageSize"

            } else {
                images.add(intent?.data!!)
                imageSize += 1
                binding.tvPhotoNum.text = "$imageSize"
            }
            imageAdapter.notifyDataSetChanged()
        }
    })


    fun clickAddImage() {
        var intent = Intent(Intent.ACTION_OPEN_DOCUMENT).setType("image/*").putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        imagePickLauncher.launch(intent)
    }

    private fun clickDone() {

        //서버에 전송할 데이터 [postTag,title,nick,text,imgPath]
        val communityPost = mutableMapOf<String, String>()
        communityPost["postTag"] = postTag
        communityPost["title"] = binding.etTitle.text.toString()
        communityPost["nick"] = GV.loginUserNick ?: ""
        communityPost["text"] = binding.etText.text.toString()
        communityPost["imgPath"] = "null"

        Log.i("what_marked", "$communityPost")

        //postTag, title, text 필수 기입
        if(communityPost["title"] == "" || communityPost["text"] == "")
            Toast.makeText(this, "제목 또는 내용이 비어있습니다.", Toast.LENGTH_SHORT).show()
        
        else if (postTagNum == 0)
            Toast.makeText(this, "태그를 선택해주세요.", Toast.LENGTH_SHORT).show()

        else {
            val retrofit: Retrofit = RetrofitHelper.getRetrofitInstance(baseUrl)
            val retrofitService = retrofit.create(RetrofitService::class.java)

            //DB 저장하기
            retrofitService.savePost(communityPost).enqueue(object : Callback<String> {
                @SuppressLint("ResourceType")
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Toast.makeText(this@NewWriteActivity, "게시물이 등록되었습니다.", Toast.LENGTH_SHORT).show()
                    //커뮤니티로 다시 이동
                    finish()
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Toast.makeText(this@NewWriteActivity, "게시물 저장에 실패했습니다.", Toast.LENGTH_SHORT).show()
                    Log.i("what_savepost_failed", "${t.message}")
                }
            })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}