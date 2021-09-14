package com.example.retrofit_hilt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofit_hilt.databinding.ActivityMainBinding
import com.example.retrofit_hilt.ui.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint

// yt-Yet Another Dev
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        if (savedInstanceState == null) {    //fragment no Instance.
            supportFragmentManager.beginTransaction()
                .replace(activityMainBinding.container.id, MainFragment.newInstance())
                .commit()
        }

    }
}

//To use Hilt in project -
//1. create Application class. Define this class in Manifest.