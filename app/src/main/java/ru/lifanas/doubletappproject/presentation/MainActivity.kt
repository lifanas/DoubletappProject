package ru.lifanas.doubletappproject.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import ru.lifanas.doubletappproject.databinding.ActivityMainBinding
import ru.lifanas.doubletappproject.saverLog.SaverLog

class MainActivity : AppCompatActivity() {

    private var count = 0L
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        incCount(savedInstanceState)
        setupSquareButtonClickListener()
        setupView()
        SaverLog.log(this, "$ACTIVITY_TAG: onCreate")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(COUNT, count)
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
        count = savedInstanceState?.getLong(COUNT)?.inc() ?: intent.getLongExtra(EXTRA_NUMBER, 0)
        binding.displayedNumber.text = count.toString()
    }

    private fun setupView() {
        when (count) {
            0L, 1L -> {
                binding.upendToStart.isVisible = true
                binding.openSquareActivity.isVisible = false
            }

            else -> {
                binding.upendToStart.isVisible = false
                binding.openSquareActivity.isVisible = true
            }
        }
    }

    companion object {
        private const val COUNT = "count"
        private const val ACTIVITY_TAG = "MainActivity"
        private const val EXTRA_NUMBER = "number"

        fun newIntent(context: Context, number: Long) =
            Intent(context, MainActivity::class.java).apply {
                putExtra(EXTRA_NUMBER, number)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
    }
}
