package com.jadhavrupesh.techstalwarts

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.jadhavrupesh.techstalwarts.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setContentView(binding.root)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val navController = navHostFragment.navController
        binding.navBottom.setupWithNavController(navController)

    }
}