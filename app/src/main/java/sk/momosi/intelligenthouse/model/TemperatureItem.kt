package sk.momosi.intelligenthouse.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlin.math.absoluteValue
import kotlin.math.truncate

@Parcelize
data class TemperatureItem(
    val value: Double,
    val timestamp: Long
) : Parcelable {

    constructor() : this(-999.9, 0L)

    fun toMap(): Map<String, Any> = mapOf(
        Pair("value", value),
        Pair("timestamp", timestamp)
    )

    companion object {
        fun fromMap(map: Map<String, Any>): TemperatureItem {
            val _val = map["value"]
            return TemperatureItem(
                value = if (_val is Long) _val.toDouble() else _val as Double,
                timestamp = if (map["timestamp"] == null) 0 else map["timestamp"] as Long
            )
        }
    }

    fun getTemperatureBig(): String {
        return String.format("%.0f", truncate(value))
    }

    fun getTemperatureSmall(): String {
        return String.format(".%03d", (value * 1000).toInt().absoluteValue % 1000)
    }
}
