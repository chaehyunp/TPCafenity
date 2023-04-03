package com.ch96.tpcafenity.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.databinding.ActivityAccountSettingBinding

class AccountSettingActivity : AppCompatActivity() {

    val binding:ActivityAccountSettingBinding by lazy { ActivityAccountSettingBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_action_back)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.tvLogout.setOnClickListener { clickLogout() }
        binding.tvDelete.setOnClickListener { clickDelete() }

    }

    private fun clickLogout() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_logout, null)
        val builder = AlertDialog.Builder(this)
            .setView(dialogView)

        val logoutAlertDialog = builder.show()

        val btnNo = dialogView.findViewById<TextView>(R.id.tv_no)
        btnNo.setOnClickListener {
            logoutAlertDialog.dismiss()
        }

        val btnYes = dialogView.findViewById<TextView>(R.id.tv_yes)
        btnYes.setOnClickListener {
            Toast.makeText(this, "로그아웃되었습니다.", Toast.LENGTH_SHORT).show()

            val intent = Intent(this,LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    private fun clickDelete() {
        startActivity(Intent(this, SettingDeleteActivity::class.java))
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}