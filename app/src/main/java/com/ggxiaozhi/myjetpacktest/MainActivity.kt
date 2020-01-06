package com.ggxiaozhi.myjetpacktest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ggxiaozhi.myjetpacktest.pagingsample.PagingSampleCopyActivity
import com.ggxiaozhi.myjetpacktest.sunflower.GardenActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        page_cheese.setOnClickListener {
            startActivity(Intent(this, PagingSampleCopyActivity::class.java))
        }

        sun_flower.setOnClickListener {
            startActivity(Intent(this, GardenActivity::class.java))
        }
    }
}
