package sk.momosi.intelligenthouse

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
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
    }

}
