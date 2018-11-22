package sk.momosi.intelligenthouse

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import sk.momosi.intelligenthouse.ui.listdays.SENSOR_ID
import sk.momosi.intelligenthouse.ui.listdays.DayListActivity

const val REQUEST_CODE_RECOVER_PLAY_SERVICES = 1001

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_temp_01.setOnClickListener {
            intent = Intent(this, DayListActivity::class.java)
                .putExtra(SENSOR_ID, "temp_room_1")
            startActivity(intent)
        }

        button_temp_02.setOnClickListener {
            intent = Intent(this, DayListActivity::class.java)
                .putExtra(SENSOR_ID, "temp_room_2")
            startActivity(intent)
        }

        setupDashboardSensors()
    }

    override fun onResume() {
        super.onResume()
        if (checkPlayServices()) {
            // Then we're good to go!
        } else {
            Toast.makeText(this, "Problem with Play Services", Toast.LENGTH_SHORT).show()
            TODO("handle play services")
        }
    }

    private fun setupDashboardSensors() {
        setTempSensor("temp_room_1", temp_01_value)
        setTempSensor("temp_room_2", temp_02_value)
        setLastHealthCheck()
        setTempSensorHealth("temp_room_1", temp_01_value)
        setTempSensorHealth("temp_room_2", temp_02_value)
    }

    private fun setTempSensorHealth(sensorId: String, txt: TextView) {
        FirebaseDatabase.getInstance()
            .getReference("activeErrorNotification")
            .child(sensorId)
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        txt.setTextColor(Color.RED)
                    } else {
                        txt.setTextColor(Color.BLACK)
                    }
                }

                override fun onCancelled(p0: DatabaseError) {}

            })
    }

    private fun setTempSensor(sensorId: String, txt: TextView) {
        FirebaseDatabase.getInstance()
            .getReference("data/$sensorId").orderByKey()
            .limitToLast(1)
            .addChildEventListener(object : ChildEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    txt.text = "--"
                }

                override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                    txt.text = "--"
                }

                override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                    Log.e("childChanged $sensorId", p0.children.last().value.toString());
                    txt.text = "${p0.children.last().value} \u00B0C"
                }

                override fun onChildRemoved(p0: DataSnapshot) {
                    txt.text = "--"
                }

                override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                    Log.e("Added $sensorId", p0.children.last().value.toString());
                    txt.text = "${p0.children.last().value} \u00B0C"
                }
            })
    }

    private fun setLastHealthCheck() {
        FirebaseDatabase.getInstance()
            .reference.child("lastDeathThresholdCheck")
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        last_check.text =
                                DateUtils.getRelativeTimeSpanString(dataSnapshot.getValue(Long::class.java) ?: 0)
                        return
                    }
                    last_check.text = "--"
                }

                override fun onCancelled(p0: DatabaseError) {
                    last_check.text = "--"
                }

            })
    }

    private fun checkPlayServices(): Boolean {
        val googleApi = GoogleApiAvailability.getInstance()
        val status = googleApi.isGooglePlayServicesAvailable(this)
        if (status != ConnectionResult.SUCCESS) {
            if (googleApi.isUserResolvableError(status)) {
                googleApi.getErrorDialog(this, status, REQUEST_CODE_RECOVER_PLAY_SERVICES).show()
            } else {
                Toast.makeText(
                    this, "This device is not supported.",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
            return false
        }
        return true

    }

}
