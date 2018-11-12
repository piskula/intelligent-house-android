package sk.momosi.intelligenthouse.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

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
            timestamp = map["timestamp"] as Long
        )
    }
}
