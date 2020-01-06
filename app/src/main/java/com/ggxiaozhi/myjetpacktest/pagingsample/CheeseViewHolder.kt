package com.ggxiaozhi.myjetpacktest.pagingsample

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ggxiaozhi.myjetpacktest.R

/**
 * Created by gzg on 2019/12/27.
 * function:
 */
class CheeseViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.cheese_item, parent, false)
) {

    private val nameView = itemView.findViewById<TextView>(R.id.name)
    // 必须是可为空的  因为如果你要启用占位符的话 item是预先加载出来的
    // 但是数据此时可能还没加载出来 这是cheese可能就是一个空的对象
    var cheese: Cheese? = null


    fun bindTo(cheese: Cheese?) {
        this.cheese = cheese
        nameView.text = cheese?.name
    }
}