package com.example.tatsuya.uni_ca_user

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.tatsuya.uni_ca_user.R.id.button_MapSearch
import com.example.tatsuya.uni_ca_user.R.id.button_ShopSearch

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.CameraPosition
import kotlinx.android.synthetic.main.activity_maps.*
import org.jetbrains.anko.startActivity


class Maps : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //FragmentManagerからMapFragmentを取得し、コールバックを設定する
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        
        //店舗検索への画面遷移の処理
        button_ShopSearch.setOnClickListener { view ->
            startActivity<ShopSearch>()
        }
        //店舗情報への画面遷移の処理
        button_ShopInfo.setOnClickListener { view ->
            startActivity<ShopInfo>()
        }
        //各種設定への画面遷移の処理
        button_Settings.setOnClickListener { view ->
            startActivity<Settings>()
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    //初期化されて利用できたときに呼ばれる
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap //インスタンスを得られるので保持しておく

        //基本的に緯度経度で場所を指定する　羽田空港と関空の位置
        val hanedaAirport = LatLng(35.5554, 139.7544)
        val kankuAirport = LatLng(34.4320024, 135.2303939)
        val latLngOrigin = LatLng(10.3181466, 123.9029382) // Ayala
        val latLngDestination = LatLng(10.311795,123.915864) // SM City
        val sydney = LatLng(-34.0, 151.0)

        //地図へのマーカーの設定方法
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.addMarker(MarkerOptions().position(LatLng(0.0, 0.0)).title("Maker"))
        mMap.addMarker(MarkerOptions().position(hanedaAirport).title("羽田空港"))

        //地図の移動　画面上に表示される場所を指定する
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hanedaAirport))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        //マップのズーム絶対値指定　1: 世界 5: 大陸 10:都市 15:街路 20:建物 ぐらいのサイズ
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15F))

        //1段階(レベル)のズーム
        mMap.moveCamera(CameraUpdateFactory.zoomIn())

        //複数段階のズーム
        mMap.moveCamera(CameraUpdateFactory.zoomBy(2F))

        //地図を移動して特定段階のズーム
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hanedaAirport, 15F))

        //指定ピクセルだけスクロール
        mMap.moveCamera(CameraUpdateFactory.scrollBy(100F, 100F))

        //カメラの回転、東西南北の向き、見下ろす角度などの指定
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder().let{
            //位置の指定必須　ないとエラーになる
            it.target(hanedaAirport)
            //東西南北の指定　北を0として右回りで360度
            it.bearing(90F)
            //カメラの見下ろす角度の指定　真下を0として90度
            it.tilt(10F)
            //ズームを指定しないと元にサイズに戻されるので事実上指定必須
            it.zoom(15F)
            it.build()
        }))

        mMap.setMyLocationEnabled(true)
        mMap.setTrafficEnabled(true)
        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE) //地図の表示
        //mMap.setOnMapClickListener()

        //出発地から目的地までのルートをプロットする
        mMap.addMarker(MarkerOptions().position(latLngOrigin).title("Ayala"))
        mMap.addMarker(MarkerOptions().position(latLngDestination).title("SM City"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngOrigin, 14.5f))

    }
}

