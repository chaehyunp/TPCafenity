package com.ch96.tpcafenity.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.databinding.ActivitySettingDeleteBinding

class SettingDeleteActivity : AppCompatActivity() {

    val binding:ActivitySettingDeleteBinding by lazy { ActivitySettingDeleteBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        var data = resources.getStringArray(R.array.logout)
        var adapter = ArrayAdapter<String>(this, R.layout.spinner_item_logout, data)

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }
}