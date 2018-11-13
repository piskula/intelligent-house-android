package sk.momosi.intelligenthouse

import android.app.Application
import com.google.firebase.database.FirebaseDatabase

class IntelligentHouse: Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }
}
