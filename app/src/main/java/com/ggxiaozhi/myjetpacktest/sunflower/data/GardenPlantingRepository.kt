package com.ggxiaozhi.myjetpacktest.sunflower.data

import com.google.samples.apps.sunflower.data.GardenPlantingDao

/**
 * Created by gzg on 2020/1/3.
 * function: 花园仓库 主要的功能就是提供对数据库 操作的封装
 */
class GardenPlantingRepository private constructor(
    private val gardenPlantingDao: GardenPlantingDao
) {


    suspend fun createGardenPlanting(plantId: String) {
        val gardenPlanting = GardenPlanting(plantId)
        gardenPlantingDao.insertGardenPlanting(gardenPlanting)
    }

    suspend fun removeGardenPlanting(gardenPlanting: GardenPlanting) {
        gardenPlantingDao.deleteGardenPlanting(gardenPlanting)
    }

    fun isPlanted(plantId: String) =
        gardenPlantingDao.isPlanted(plantId)


    fun getPlantedGardens() = gardenPlantingDao.getPlantedGardens()

    companion object {
        @Volatile
        private var instance: GardenPlantingRepository? = null

        fun getInstance(gardenPlantingDao: GardenPlantingDao): GardenPlantingRepository =
            instance ?: synchronized(this) {
                instance ?: GardenPlantingRepository(gardenPlantingDao)
            }.also {
                instance = it
            }

    }

}