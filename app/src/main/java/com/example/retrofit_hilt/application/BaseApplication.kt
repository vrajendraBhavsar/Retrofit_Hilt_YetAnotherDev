package com.example.retrofit_hilt.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application()   //don't forget to add this class into Manifest file

//This will tell hilt that this Application is using Hilt.