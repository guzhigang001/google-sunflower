package com.ggxiaozhi.myjetpacktest.sunflower.data

import androidx.room.*
import java.util.*

/**
 * Created by gzg on 2019/12/31.
 * function: 我的花园中的花
 */
@Entity(
    tableName = "garden_plantings",
    //外键引用 使用Plant类中的id作为主表 GardenPlanting为从表 plant_id字段使用存id外键的值
    foreignKeys = [ForeignKey(entity = Plant::class, parentColumns = ["id"], childColumns = ["plant_id"])],
    //索引 用于优化查询
    indices =[Index("plant_id")]
    )
data class GardenPlanting(
    @ColumnInfo(name = "plant_id") val plantId: String,

    /**
     * Indicates when the [Plant] was planted. Used for showing notification when it's time
     * to harvest the plant.
     */
    @ColumnInfo(name = "plant_date") val plantDate: Calendar = Calendar.getInstance(),

    /**
     * Indicates when the [Plant] was last watered. Used for showing notification when it's
     * time to water the plant.
     */
    @ColumnInfo(name = "last_watering_date")
    val lastWateringDate: Calendar = Calendar.getInstance()
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var gardenPlantingId: Long = 0
}