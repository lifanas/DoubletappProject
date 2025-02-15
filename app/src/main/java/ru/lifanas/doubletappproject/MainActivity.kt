package ru.lifanas.doubletappproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import ru.lifanas.doubletappproject.databinding.ActivityMainBinding
import ru.lifanas.doubletappproject.saverLog.SaverLog

class MainActivity : AppCompatActivity() {

    private var count = 0
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        incCount(savedInstanceState)
        SaverLog.log(this, "$ACTIVITY_TAG: onCreate")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(COUNT, count)
    }

    override fun onStart() {
        super.onStart()
        SaverLog.log(this, "$ACTIVITY_TAG: onStart")
    }

    override fun onResume() {
        super.onResume()
        SaverLog.log(this, "$ACTIVITY_TAG: onResume")
    }

    override fun onPause() {
        super.onPause()
        SaverLog.log(this, "$ACTIVITY_TAG: onPause")
    }

    override fun onStop() {
        super.onStop()
        SaverLog.log(this, "$ACTIVITY_TAG: onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        SaverLog.log(this, "$ACTIVITY_TAG: onDestroy")
    }

    private fun incCount(savedInstanceState: Bundle?) {
        count = savedInstanceState?.getInt(COUNT)?.inc() ?: 0
        binding.displayedNumber.text = count.toString()
    }

    private companion object {
        const val COUNT = "count"
        const val ACTIVITY_TAG = "MainActivity"
    }
}
