package xyz.williamreed.mapbox_sigsegv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment(), OnMapReadyCallback {

    private lateinit var parent: ButtonClickHandler
    private lateinit var mapView: MapView
    private lateinit var mapboxMap: MapboxMap
    private var symbolManager: SymbolManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        parent = activity as ButtonClickHandler
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        mapView = view.findViewById(R.id.map)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        return view
    }

    override fun onMapReady(mapboxMap: MapboxMap) {
        this.mapboxMap = mapboxMap

        mapboxMap.setStyle(Style.MAPBOX_STREETS) { style ->
            mapboxMap.moveCamera(CameraUpdateFactory.newLatLng(LatLng(40.7831, -73.9712)))
            mapboxMap.moveCamera(CameraUpdateFactory.zoomTo(10.0))


            // create symbol manager and bus symbol if needed.
            if (symbolManager == null) {
                symbolManager = SymbolManager(mapView, mapboxMap, style)
            }

            symbolManager?.create(
                SymbolOptions().withLatLng(LatLng(40.7831, -73.9712))
            )

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.button.setOnClickListener {
            parent.handleButtonClick()
        }
    }

    interface ButtonClickHandler {
        fun handleButtonClick()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        symbolManager?.onDestroy()
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}
