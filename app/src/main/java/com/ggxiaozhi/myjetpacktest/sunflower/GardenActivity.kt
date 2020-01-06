package com.ggxiaozhi.myjetpacktest.sunflower

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ggxiaozhi.myjetpacktest.R
import com.ggxiaozhi.myjetpacktest.databinding.ActivityGardenBinding

class GardenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityGardenBinding>(this,R.layout.activity_garden)
    }
}
