package sk.momosi.intelligenthouse

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import sk.momosi.intelligenthouse.model.TemperatureItem
import sk.momosi.intelligenthouse.ui.temperature.Temperature01Activity
import sk.momosi.intelligenthouse.ui.temperature.Temperature02Activity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_temp_01.setOnClickListener {
            intent = Intent(this, Temperature01Activity::class.java)
            startActivity(intent)
        }

        button_temp_02.setOnClickListener {
            intent = Intent(this, Temperature02Activity::class.java)
            startActivity(intent)
        }

        setupDashboardSensors()
    }

    fun setupDashboardSensors() {
        setTemp01()
        setTemp02()
    }

    fun setTemp02() {
        FirebaseDatabase.getInstance()
            .getReference("data/temp_room_2").orderByChild("timestamp")
            .limitToLast(1)
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val item = dataSnapshot.children.first().getValue(TemperatureItem::class.java)
                        if (item != null) {
                            temp_02_value.text = "${item.value} \u00B0C"
                            return
                        }
                    }
                    temp_02_value.text = "--"
                }

                override fun onCancelled(p0: DatabaseError) {
                    temp_02_value.text = "--"
                }

            })
    }

    fun setTemp01() {
        FirebaseDatabase.getInstance()
            .getReference("data/temp_room_1").orderByChild("timestamp")
            .limitToLast(1)
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        val item = dataSnapshot.children.first().getValue(TemperatureItem::class.java)
                        if (item != null) {
                            temp_01_value.text = "${item.value} \u00B0C"
                            return
                        }
                    }
                    temp_01_value.text = "--"
                }

                override fun onCancelled(p0: DatabaseError) {
                    temp_01_value.text = "--"
                }

            })
    }

}
