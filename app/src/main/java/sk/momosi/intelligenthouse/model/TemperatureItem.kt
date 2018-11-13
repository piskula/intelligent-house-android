package sk.momosi.intelligenthouse.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.text.DecimalFormat
import kotlin.math.absoluteValue

@Parcelize
data class TemperatureItem(
    val id: String = "",
    val value: Double,
    val timestamp: Long
) : Parcelable {

    fun toMap(): Map<String, Any> = mapOf(
        Pair("value", value),
        Pair("timestamp", timestamp)
    )

    companion object {
        fun fromMap(id: String, map: Map<String, Any>) = TemperatureItem(
            id = id,
            value = map["value"] as Double,
            timestamp = if (map["timestamp"] == null) 0 else map["timestamp"] as Long
        )
    }

    fun getTemperatureBig(): String {
        return DecimalFormat("#.#").format(((value * 10).toLong()) / 10.0)
    }

    fun getTemperatureSmall(): String {
        return String.format("%02d", (value * 1000).toInt().absoluteValue % 100)
    }
}
