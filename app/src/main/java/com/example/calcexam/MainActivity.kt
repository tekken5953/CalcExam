package com.example.calcexam

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.calcexam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val tableRowList by lazy {
        listOf(binding.tr1,binding.tr2,binding.tr3, binding.tr4,binding.tr5,binding.tr6)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        AddClickListener().add(tableRowList, binding.mainNumberText, binding.mainResultText)

    }
}