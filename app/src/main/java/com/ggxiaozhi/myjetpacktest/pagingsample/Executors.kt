package com.ggxiaozhi.myjetpacktest.pagingsample

import java.util.concurrent.Executors

/**
 * Created by gzg on 2019/12/27.
 * function:
 */

private val IO_EXECUTOR = Executors.newSingleThreadExecutor()

fun ioThread(runnable: () -> Unit) {
    IO_EXECUTOR.execute(runnable)
}