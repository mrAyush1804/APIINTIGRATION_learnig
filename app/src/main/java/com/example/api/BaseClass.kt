package com.example.api

import android.app.Application
import dagger.hilt.android.HiltAndroidApp



// Hilt ka use karne ke liye ye class zaroori hai


@HiltAndroidApp
class BaseClass:Application() {

}