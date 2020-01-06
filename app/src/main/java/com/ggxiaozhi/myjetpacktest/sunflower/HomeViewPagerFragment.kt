package com.ggxiaozhi.myjetpacktest.sunflower


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity

import com.ggxiaozhi.myjetpacktest.R
import com.ggxiaozhi.myjetpacktest.databinding.FragmentViewPagerBinding
import com.ggxiaozhi.myjetpacktest.sunflower.adapters.MY_GARDEN_PAGE_INDEX
import com.ggxiaozhi.myjetpacktest.sunflower.adapters.PLANT_LIST_PAGE_INDEX
import com.ggxiaozhi.myjetpacktest.sunflower.adapters.SunflowerPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.text.FieldPosition

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager2 = binding.viewPager


        viewPager2.adapter = SunflowerPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = getTabTitle(position)
            tab.setIcon(getTabIcon(position))

        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            MY_GARDEN_PAGE_INDEX -> R.drawable.garden_tab_selector
            PLANT_LIST_PAGE_INDEX -> R.drawable.plant_list_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    fun getTabTitle(position: Int): String? {
        return when (position) {
            MY_GARDEN_PAGE_INDEX -> getString(R.string.my_garden_title)
            PLANT_LIST_PAGE_INDEX -> getString(R.string.plant_list_title)
            else -> null
        }
    }

}
