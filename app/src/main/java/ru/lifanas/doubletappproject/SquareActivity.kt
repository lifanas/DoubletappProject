package ru.lifanas.doubletappproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import ru.lifanas.doubletappproject.databinding.ActivitySquareBinding
import ru.lifanas.doubletappproject.saverLog.SaverLog

class SquareActivity : AppCompatActivity() {

    private var count = 0
    private lateinit var binding: ActivitySquareBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySquareBinding.inflate(layoutInflater)
        setContentView(binding.root)
        restoreOrCalculateCount(savedInstanceState)
        setupSquareButtonClickListener()
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

    private fun setupSquareButtonClickListener() = binding.openMainActivity.setOnClickListener {
        startActivity(MainActivity.newIntent(this, count))
    }

    private fun restoreOrCalculateCount(savedInstanceState: Bundle?) {
        count = savedInstanceState?.getInt(COUNT) ?: intent.getIntExtra(EXTRA_NUMBER, 0).square()
        binding.displayedNumber.text = count.toString()
    }

    private fun Int.square(): Int = this * this

    companion object {
        private const val COUNT = "count"
        private const val EXTRA_NUMBER = "number"
        private const val ACTIVITY_TAG = "SecondActivity"

        fun newIntent(context: Context, number: Int) =
            Intent(context, SquareActivity::class.java).apply { putExtra(EXTRA_NUMBER, number) }
    }
}
