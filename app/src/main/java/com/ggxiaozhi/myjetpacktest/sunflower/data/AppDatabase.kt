package com.ggxiaozhi.myjetpacktest.sunflower.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.ggxiaozhi.myjetpacktest.sunflower.utilities.DATABASE_NAME
import com.ggxiaozhi.myjetpacktest.sunflower.works.SeedDatabaseWorker
import com.google.samples.apps.sunflower.data.GardenPlantingDao
import com.google.samples.apps.sunflower.data.PlantDao
import kotlin.coroutines.CoroutineContext

/**
 * Created by gzg on 2019/12/31.
 * function:
 */
@Database(entities = [Plant::class, GardenPlanting::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gardenPlantingDao(): GardenPlantingDao
    abstract fun plantDao(): PlantDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }
        private fun buildDatabase(context: Context): AppDatabase {

            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(object : RoomDatabase.Callback() {

                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                        WorkManager.getInstance(context).enqueue(request)
                    }
                }).build()


        }
    }


}