package sk.momosi.intelligenthouse

import android.annotation.TargetApi
import android.app.Application
import android.app.NotificationChannel
import com.google.firebase.database.FirebaseDatabase
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build

const val NOTIF_CHANNEL_OUT_OF_ORDER = "emergency_notif_channel"

class IntelligentHouse: Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        createNotificationChannels()
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun createNotificationChannels() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val name = getString(R.string.notif_out_of_order)

        val channel = NotificationChannel(NOTIF_CHANNEL_OUT_OF_ORDER, name, NotificationManager.IMPORTANCE_DEFAULT)
        channel.enableLights(true)
        channel.lightColor = Color.WHITE
        channel.enableVibration(true)
        channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)

        notificationManager.createNotificationChannel(channel)
    }
}
