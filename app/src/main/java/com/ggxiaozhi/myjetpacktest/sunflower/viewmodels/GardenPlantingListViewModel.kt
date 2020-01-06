package com.ggxiaozhi.myjetpacktest.sunflower.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ggxiaozhi.myjetpacktest.sunflower.data.GardenPlantingRepository
import com.ggxiaozhi.myjetpacktest.sunflower.data.PlantAndGardenPlantings

/**
 * Created by gzg on 2020/1/3.
 * function:
 */
class GardenPlantingListViewModel internal constructor(
    gardenPlantingRepository: GardenPlantingRepository
) : ViewModel() {

    val plantAndGardenPlantings: LiveData<List<PlantAndGardenPlantings>> =
        gardenPlantingRepository.getPlantedGardens()

}