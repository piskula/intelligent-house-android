package sk.momosi.intelligenthouse.ui.listdays

import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import sk.momosi.intelligenthouse.ui.BaseListViewModel
import java.time.LocalDate

class DayListViewModel(private val sensorId: String): BaseListViewModel() {

    val days: ObservableList<LocalDate> = ObservableArrayList()

    override fun loadData() {
        FirebaseDatabase.getInstance()
            .getReference("data/$sensorId").orderByKey()
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    days.clear()

                    if (dataSnapshot.exists()) {
                        dataSnapshot.children.forEach {
                            days.add(LocalDate.parse(it.key))
                        }
                    }

                    isEmpty.set(days.isEmpty() || !dataSnapshot.exists())
                    isLoading.set(false)
                }

                override fun onCancelled(p0: DatabaseError) {
                    isError.set(true)
                }
            })
    }

}
