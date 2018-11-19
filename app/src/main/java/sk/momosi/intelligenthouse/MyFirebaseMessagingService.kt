package sk.momosi.intelligenthouse

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.PowerManager
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationCompat.MessagingStyle.Message
import android.support.v4.app.NotificationCompat.InboxStyle
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.json.JSONArray
import java.sql.Timestamp

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        val data = mapOf("firebaseId" to token)
        FirebaseDatabase.getInstance().reference.updateChildren(data)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        val json = JSONArray(message.data?.get("subtitle"))

        val errorSensorIds: MutableSet<String> = mutableSetOf()

        if (json.length() > 0)
            for (i in 0..(json.length() - 1))
                errorSensorIds.add(json.getString(i))

        // TODO use translations
        sendNotificationTranslated(errorSensorIds)
    }

    private fun sendNotificationTranslated(errorSensors: Set<String>) {
        val intent = Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_ONE_SHOT
        )

        val styleNotEmpty = InboxStyle()
        errorSensors.forEach { styleNotEmpty.addLine(it) }

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notification = NotificationCompat.Builder(this, NOTIF_CHANNEL_OUT_OF_ORDER)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setAutoCancel(true)
            .setContentTitle(if (errorSensors.isEmpty()) getString(R.string.notif_title_error_empty) else getString(R.string.notif_title_error_exists))
            .setContentText(if (errorSensors.isEmpty()) getString(R.string.notif_error_empty) else getString(R.string.notif_error_exists))
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)
            .setStyle(if (errorSensors.isEmpty()) null else styleNotEmpty)

        // Create notification
        (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
            .notify(1, notification.build())

        // Turn on the screen for notification
        val powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
        if (!powerManager.isInteractive) {
            val wl_cpu = powerManager.newWakeLock(
                PowerManager.PARTIAL_WAKE_LOCK,
                "sk.momosi.intelligenthouse:notif_wake"
            )
            wl_cpu.acquire(5000)
        }
    }
}
