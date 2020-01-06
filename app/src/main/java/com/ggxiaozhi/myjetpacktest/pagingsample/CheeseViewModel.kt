package com.ggxiaozhi.myjetpacktest.pagingsample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Config
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.toLiveData

/**
 * Created by gzg on 2019/12/27.
 * function:
 */
class CheeseViewModel(app: Application) : AndroidViewModel(app) {

    val dao = CheeseDb.get(getApplication()).cheeseDao()

    // 使用Android-KTX API 这是一个用Kotlin封装后的API
/*    val allCheeses = dao.allCheesesByName().toLiveData(
        Config(
            pageSize = 30,//pageList分页每页请求个数 initialLoadSizeHint ()加载数是pageSize*3
            enablePlaceholders = true,
            maxSize = 200//pageList最大的缓存个数 200 超过这个数目将删除缓存中的旧数据
        )
    )*/

    val allCheeses = LivePagedListBuilder(
        dao.allCheesesByName(),
        PagedList.Config.Builder()
            .setEnablePlaceholders(ENABLE_PLACEHOLDERS)//是否请用占位符
            .setInitialLoadSizeHint(PAGE_SIZE)//首次最大初始化数量
            .setPageSize(PAGE_SIZE)//分页每次加载的数量
            .setPrefetchDistance(PREFETCH_DISTANCE_SIZE)//滑动item到多少出发load下一页
            .setMaxSize(MEMORY_MAX_SIZE)//最大缓存个数  向下滑动页面
            .build()
    ).build()

    fun insert(text: String) =
        ioThread {
            dao.insert(Cheese(0, text))
        }

    fun remove(cheese: Cheese) = ioThread {
        dao.delete(cheese)
    }


    companion object {
        private const val PAGE_SIZE = 30
        private const val ENABLE_PLACEHOLDERS = true
        private const val MEMORY_MAX_SIZE = 200
        private const val PREFETCH_DISTANCE_SIZE = PAGE_SIZE - 5
    }
}