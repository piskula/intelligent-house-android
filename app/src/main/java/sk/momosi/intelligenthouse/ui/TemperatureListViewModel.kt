package sk.momosi.intelligenthouse.ui

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import sk.momosi.intelligenthouse.model.TemperatureItem

class TemperatureListViewModel: ViewModel() {

    val temperatures: ObservableList<TemperatureItem> = ObservableArrayList()

    val isLoading = ObservableBoolean(true)

    val isEmpty = ObservableBoolean(false)

    val isError = ObservableBoolean(false)

    fun loadData() {
        FirebaseDatabase.getInstance()
            .getReference("data/temp_room_2")
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    temperatures.clear()

                    if (dataSnapshot.exists()) {
                        dataSnapshot.children.forEach {
                            temperatures.add(TemperatureItem.fromMap(it.key!!, it.value as Map<String, Any>))
                        }
                    }

                    isEmpty.set(temperatures.isEmpty() || !dataSnapshot.exists())
                    isLoading.set(false)
                }

                override fun onCancelled(p0: DatabaseError) {
                    isError.set(true)
                }
            })
    }

}