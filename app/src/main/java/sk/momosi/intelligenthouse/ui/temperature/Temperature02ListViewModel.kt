package sk.momosi.intelligenthouse.ui.temperature

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import sk.momosi.intelligenthouse.model.TemperatureItem
import sk.momosi.intelligenthouse.ui.BaseListViewModel

class Temperature02ListViewModel: BaseListViewModel<TemperatureItem>() {

    override fun loadData() {
        FirebaseDatabase.getInstance()
            .getReference("data/temp_room_2").orderByChild("timestamp")
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