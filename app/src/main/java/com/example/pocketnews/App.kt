package com.example.pocketnews

import android.app.Application
import android.content.Context
import com.example.pocketnews.retrofit.NetRepoImp

class App : Application() {

    val repo by lazy {NetRepoImp()}
}

val Context.app: App get() = applicationContext as App