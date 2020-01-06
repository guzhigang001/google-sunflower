package com.ggxiaozhi.myjetpacktest.pagingsample

import android.app.DownloadManager
import android.graphics.SweepGradient
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent

import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ggxiaozhi.myjetpacktest.R
import com.ggxiaozhi.myjetpacktest.ktx.viewModels
import kotlinx.android.synthetic.main.activity_pagingsample_copy.*

@Suppress("UNCHECKED_CAST")
class PagingSampleCopyActivity : AppCompatActivity() {

/*    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T = CheeseViewModel(application) as T
        }).get(CheeseViewModel::class.java)
    }*/

    private val viewMode by viewModels<CheeseViewModel>()

    private lateinit var viewModel:CheeseViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagingsample_copy)

        val adapter = CheeseAdapter()
        cheeseList.adapter = adapter

        viewMode.allCheeses.observe(this, Observer {
            adapter.submitList(it)
        })

        initAddButtonListener()
        initSwipeToDelete()






    }

    private fun initSwipeToDelete() {
        ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
                makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                (viewHolder as CheeseViewHolder).cheese?.let {
                    viewMode.remove(it)
                }
            }

        }).attachToRecyclerView(cheeseList)
    }

    private fun initAddButtonListener() {

        addButton.setOnClickListener {
            addCheese()
        }
        inputText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_MASK_ACTION) {//如果按下回车键(enter)
                addCheese()
                return@setOnEditorActionListener true
            }
            false
        }

        inputText.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                addCheese()
                return@setOnKeyListener true
            }
            false
        }
    }

    private fun addCheese() {
        val text = inputText.text.toString().trim()
        if (text.isNotEmpty()) {
            viewMode.insert(text)
            inputText.setText("")
        }
    }
}
