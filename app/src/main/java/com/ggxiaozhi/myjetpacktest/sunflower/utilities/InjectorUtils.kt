package com.ggxiaozhi.myjetpacktest.sunflower.utilities

import android.content.Context
import com.ggxiaozhi.myjetpacktest.sunflower.data.AppDatabase
import com.ggxiaozhi.myjetpacktest.sunflower.data.GardenPlantingRepository
import com.ggxiaozhi.myjetpacktest.sunflower.viewmodels.GardenPlantingListViewModelFactory
import com.google.samples.apps.sunflower.data.GardenPlantingDao

/**
 * Created by gzg on 2020/1/3.
 * function:
 */
object InjectorUtils {


    fun getGardenPlantingRepository(context: Context): GardenPlantingRepository {
        return GardenPlantingRepository.getInstance(
            AppDatabase.getInstance(context).gardenPlantingDao()
        )
    }
    fun provideGardenPlantingListViewModelFactory(
        context: Context
    ): GardenPlantingListViewModelFactory {

        return GardenPlantingListViewModelFactory(getGardenPlantingRepository(context))
    }
}