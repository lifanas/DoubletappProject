package ru.lifanas.doubletappproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import ru.lifanas.doubletappproject.databinding.ActivitySquareBinding

class SquareActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySquareBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySquareBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
