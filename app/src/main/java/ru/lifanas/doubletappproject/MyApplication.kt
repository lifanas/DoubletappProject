package ru.lifanas.doubletappproject

import android.app.Application
import com.google.android.gms.time.TrustedTime
import com.google.android.gms.time.TrustedTimeClient

class MyApplication : Application() {
    var trustedTimeClient: TrustedTimeClient? = null
        private set

    override fun onCreate() {
        super.onCreate()
        val initializeTrustedTimeClientTask = TrustedTime.createClient(this)
        initializeTrustedTimeClientTask.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                trustedTimeClient = task.result
            } else {
                val exception = initializeTrustedTimeClientTask.exception
                exception?.printStackTrace()
            }
        }
    }
}
