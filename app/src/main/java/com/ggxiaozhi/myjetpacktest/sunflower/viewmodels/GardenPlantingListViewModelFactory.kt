package com.ggxiaozhi.myjetpacktest.sunflower.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ggxiaozhi.myjetpacktest.sunflower.data.GardenPlantingRepository

/**
 * Created by gzg on 2020/1/3.
 * function:
 */
class GardenPlantingListViewModelFactory(
    private val gardenPlantingRepository: GardenPlantingRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return GardenPlantingListViewModel(gardenPlantingRepository) as T
    }
}