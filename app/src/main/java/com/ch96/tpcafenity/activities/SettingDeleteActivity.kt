package com.ch96.tpcafenity.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.databinding.ActivitySettingDeleteBinding

class SettingDeleteActivity : AppCompatActivity() {

    val binding:ActivitySettingDeleteBinding by lazy { ActivitySettingDeleteBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.icon_action_back)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        var data = resources.getStringArray(R.array.logout)
        var adapter = ArrayAdapter<String>(this, R.layout.spinner_item_logout, data)

        binding.spinner.adapter = adapter
        binding.spinner.setSelection(0)
        adapter.setDropDownViewResource(R.layout.spiner_dropdown_item)
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 > 0) {
                    binding.tvDeleteInfo.setVisibility(View.VISIBLE)
                    binding.btn.setVisibility(View.VISIBLE)
                }
                if (p2 == 0) {
                    binding.tvDeleteInfo.setVisibility(View.INVISIBLE)
                    binding.btn.setVisibility(View.INVISIBLE)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        binding.tvNo.setOnClickListener { clickCancel() }
        binding.tvYes.setOnClickListener { clickDeleteAccount() }
    }

    private fun clickCancel() {
        finish()
    }
    private fun clickDeleteAccount() {
        val intent = Intent(this, DeleteAccountActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}