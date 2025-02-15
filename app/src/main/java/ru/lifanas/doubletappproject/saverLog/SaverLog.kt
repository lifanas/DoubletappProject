package ru.lifanas.doubletappproject.saverLog

import android.content.Context
import android.os.Environment
import android.util.Log
import ru.lifanas.doubletappproject.MyApplication
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object SaverLog {
    private const val FILE_NAME = "lifecycle_log.txt"
    private const val LOGCAT_TAG = "SaverLog"

    fun log(context: Context, message: String) {
        val myApp = context.applicationContext as? MyApplication
        val currentTimeMillis = myApp?.trustedTimeClient?.computeCurrentUnixEpochMillis()
            ?: System.currentTimeMillis()

        val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            .format(Date(currentTimeMillis))
        val line = "$timestamp: $message\n"

        Log.d(LOGCAT_TAG, message)

        val publicDir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
        if (!publicDir.exists()) {
            publicDir.mkdirs()
        }
        val logFile = File(publicDir, FILE_NAME)
        var output: FileOutputStream? = null
        try {
            output = FileOutputStream(logFile, true)
            output.write(line.toByteArray())
            output.flush()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                output?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
