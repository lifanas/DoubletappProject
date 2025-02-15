package ru.lifanas.doubletappproject

import android.content.Context
import android.content.Intent
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
        setupSquareButtonClickListener()
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

    private fun setupSquareButtonClickListener() = binding.openSquareActivity.setOnClickListener {
        startActivity(SquareActivity.newIntent(this, count))
    }

    private fun incCount(savedInstanceState: Bundle?) {
        count = savedInstanceState?.getInt(COUNT)?.inc() ?: intent.getIntExtra(EXTRA_NUMBER, 0)
        binding.displayedNumber.text = count.toString()
    }

    companion object {
        private const val COUNT = "count"
        private const val ACTIVITY_TAG = "MainActivity"
        private const val EXTRA_NUMBER = "number"

        fun newIntent(context: Context, number: Int) =
            Intent(context, MainActivity::class.java).apply { putExtra(EXTRA_NUMBER, number) }
    }
}
