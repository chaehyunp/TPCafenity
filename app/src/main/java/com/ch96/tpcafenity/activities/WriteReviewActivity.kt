package com.ch96.tpcafenity.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.opengl.Visibility
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.loader.content.CursorLoader
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestCoordinator.RequestState
import com.ch96.tpcafenity.GV
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.adapters.RecyclerSelectedImageAdapter
import com.ch96.tpcafenity.databinding.ActivityNewWriteBinding
import com.ch96.tpcafenity.databinding.ActivityWriteReviewBinding
import com.ch96.tpcafenity.databinding.RecyclerItemNewWriteBinding
import com.ch96.tpcafenity.fragments.CommunityFragment
import com.ch96.tpcafenity.network.RetrofitHelper
import com.ch96.tpcafenity.network.RetrofitService
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.File

class WriteReviewActivity : AppCompatActivity() {

    val binding:ActivityWriteReviewBinding by lazy { ActivityWriteReviewBinding.inflate(layoutInflater) }
    var baseUrl:String = "http://testhue96.dothome.co.kr/"

    //이미지
//    var images:MutableList<Uri> = mutableListOf()
//    var imageAdapter = RecyclerSelectedImageAdapter(this, images)
//    var imageSize = 0
//    var imgPath:MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //툴바 설정
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_action_close)
        supportActionBar?.setDisplayShowTitleEnabled(false)

//        binding.recycler.adapter = imageAdapter

        binding.tvDone.setOnClickListener { clickDone() }
//        binding.btnAddImg.setOnClickListener { clickAddImage() }
    }

//    fun clickedDeleteItem(pos:Int){
//        images.removeAt(pos)
//        imageAdapter.notifyDataSetChanged()
//        imageSize--
//        binding.tvPhotoNum.text= "$imageSize"
//    }

    //이미지 선택 결과 런처
//    @SuppressLint("NotifyDataSetChanged")
//    var imagePickLauncher:ActivityResultLauncher<Intent> = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), ActivityResultCallback {
//        if (it.resultCode != RESULT_CANCELED) {
//            var intent = it.data
//            var clipData = intent?.clipData
//
//            if(clipData != null) {
//                var size = clipData.itemCount
//                for(i in 0 until size!!) images?.add(clipData?.getItemAt(i)?.uri!!)
//                imageSize += size
//                binding.tvPhotoNum.text = "$imageSize"
//
//            } else {
//                images.add(intent?.data!!)
//                imageSize++
//                binding.tvPhotoNum.text = "$imageSize"
//            }
//            imageAdapter.notifyDataSetChanged()
//        }
//    })


//    fun clickAddImage() {
//        var intent = Intent(Intent.ACTION_PICK).setType("image/*").putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
//        imagePickLauncher.launch(intent)
//    }
//
//    //uri -> path 변환
//    fun getFilePathFormUri(uri:Uri):String? {
//        var proj:Array<String> = arrayOf(MediaStore.Images.Media.DATA)
//        var cursor = contentResolver.query( uri, proj, null, null, null)
//        cursor?.moveToFirst()
//
//        return cursor?.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
//    }

    private fun clickDone() {
        //소프트 키보드 없애기
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken,0)

        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_save_review, null)
        val builder = AlertDialog.Builder(this).setView(dialogView)
        val saveAlertDialog = builder.show()
        val btnNo = dialogView.findViewById<TextView>(R.id.tv_no)
        btnNo.setOnClickListener {
            saveAlertDialog.dismiss()
        }
        val btnYes = dialogView.findViewById<TextView>(R.id.tv_yes)
        btnYes.setOnClickListener {

            //서버에 전송할 데이터 [nick,text,imgPath]
            val communityPost = mutableMapOf<String, String>()
            communityPost["accountNo"] = GV.loginUserNo ?: ""
            communityPost["shopId"] = intent.getStringExtra("shopId").toString()
            communityPost["nick"] = GV.loginUserNick ?: ""
            communityPost["text"] = binding.etText.text.toString()

            //text 필수 기입
            if(communityPost["text"] == ""){
                saveAlertDialog.dismiss()
                Toast.makeText(this, "내용이 비어있습니다.", Toast.LENGTH_SHORT).show()
            } else {
//                //이미지가 있을경우 이미지 uri -> path
//                if (imageSize != 0) {
//                    for (i in 0 until images.size) {
//                        var uri = images[i]
//                        Log.i("what_uri", "$uri")
//                        imgPath.add(getFilePathFormUri(uri)?:"")
//                        Log.i("what_path", "$imgPath")
//                    }
//                    saveAlertDialog.dismiss()
//                }

                val retrofit: Retrofit = RetrofitHelper.getRetrofitInstance(baseUrl)
                val retrofitService = retrofit.create(RetrofitService::class.java)

                //이미지가 있으면
//                var filePart:MutableList<MultipartBody.Part> = mutableListOf()
//                if (imageSize != 0) {Log.i("what_image_size","$imageSize")
//                    for (i in 0 until imageSize) {
//                        val file = File(imgPath[i])
//                        Log.i("what_path_i","${imgPath[i]}") ///storage/emulated/0/DCIM/zzang_gu/zzang_whithpanti.jpg
//                        val body = file.toString().toRequestBody("image/*".toMediaType())
////                        filePart[i] = MultipartBody.Part.createFormData("img$i", file.name, body)
////                        Log.i("what_file","${filePart[i]}")
//                        val part = MultipartBody.Part.createFormData("img$i", file.name, body)
//                        filePart.add(part)
//                        Log.i("what_part","$filePart")
//                    }
//                }
                retrofitService.saveReview(communityPost).enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        Toast.makeText(this@WriteReviewActivity, "리뷰가 등록되었습니다.", Toast.LENGTH_SHORT).show()
                        //리뷰보기로 다시 이동
                        saveAlertDialog.dismiss()
                        finish()
                    }
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Toast.makeText(this@WriteReviewActivity, "리뷰 등록에 실패했습니다.", Toast.LENGTH_SHORT).show()
                        saveAlertDialog.dismiss()
                        Log.i("what_savepost_failed", "${t.message}")
                    }

                })
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}