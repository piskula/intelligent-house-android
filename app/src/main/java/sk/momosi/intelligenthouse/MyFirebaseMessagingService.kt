package sk.momosi.intelligenthouse

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.PowerManager
import android.support.v4.app.NotificationCompat
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        val data = mapOf("firebaseId" to token)
        FirebaseDatabase.getInstance().reference.updateChildren(data)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        val title = message.data?.get("title")
        val subtitle = message.data?.get("subtitle")
        // TODO translate
        sendNotificationTranslated(title, subtitle)
    }

    private fun sendNotificationTranslated(title: String?, subtitle: String?) {
        val intent = Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notification = NotificationCompat.Builder(this, NOTIF_CHANNEL_OUT_OF_ORDER)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(title)
            .setContentText(subtitle)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        // Create notification
        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
            .notify(1, notification.build())

        // Turn on the screen for notification
        val powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
        if (!powerManager.isInteractive) {
            val wl_cpu = powerManager.newWakeLock(
                PowerManager.PARTIAL_WAKE_LOCK,
                "sk.momosi.intelligenthouse:notif_wake")
            wl_cpu.acquire(5000)
        }
    }
}
