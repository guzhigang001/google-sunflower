package com.ggxiaozhi.myjetpacktest.sunflower.works

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ggxiaozhi.myjetpacktest.sunflower.data.AppDatabase
import com.ggxiaozhi.myjetpacktest.sunflower.data.Plant
import com.ggxiaozhi.myjetpacktest.sunflower.utilities.PLANT_DATA_FILENAME
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.coroutineScope
import java.lang.Exception

/**
 * Created by gzg on 2020/1/2.
 * function: 利用workManager 创建数据路默认数据 doWork()方法中
 */
class SeedDatabaseWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(PLANT_DATA_FILENAME).use { inputstream ->
                JsonReader(inputstream.reader()).use {jsonRead->

                    val plantType = object : TypeToken<List<Plant>>() {}.type

                    val plantList: List<Plant> = Gson().fromJson(jsonRead, plantType)

                    val database = AppDatabase.getInstance(applicationContext)
                    database.plantDao().insertAll(plantList)
                    Result.success()
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error seeding database", e)
            Result.failure()
        }
    }
    companion object {
        private val TAG = SeedDatabaseWorker::class.java.simpleName
    }
}


