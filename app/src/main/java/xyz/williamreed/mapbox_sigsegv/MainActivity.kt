package xyz.williamreed.mapbox_sigsegv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mapbox.mapboxsdk.Mapbox

class MainActivity : AppCompatActivity(), MainFragment.ButtonClickHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(applicationContext, "pk.eyJ1Ijoid2lsbGlhbXJlZWQiLCJhIjoiY2p0Z2EyN3BkMDFlMDRhcGttaHpjYTk5MiJ9.aAl7MRRocFIMVQBF4OaClg")
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            add(R.id.frag_container, MainFragment())
            commit()
        }
    }

    override fun handleButtonClick() {
        // Create new fragment and transaction
        val newFragment = OtherFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frag_container, newFragment)
            addToBackStack(null)
            commit()
        }
    }
}
