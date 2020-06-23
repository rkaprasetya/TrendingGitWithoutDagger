package com.raka.trendinggitwithdaggerhilt.view.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.raka.trendinggitwithdaggerhilt.R
import javax.inject.Inject

/**
 * lINK FOR TUTORIAL
 * https://medium.com/@er.ankitbisht/mvvm-model-view-viewmodel-kotlin-google-jetpack-f02ec7754854
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

        NavigationUI.setupActionBarWithNavController(this,findNavController(R.id.main_nav_fragment))
    }

    override fun onSupportNavigateUp() = findNavController(R.id.main_nav_fragment).navigateUp()
}
