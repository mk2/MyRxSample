package mk2.red.myrxsample.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import mk2.red.myrxsample.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launch_simple_rxjava.setOnClickListener {
            val intent = Intent(this, SimpleActivity::class.java)
            startActivity(intent)
        }
    }
}