package com.enpit.tatsuya.uni_ca_user

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.activity_maps.*
import org.jetbrains.anko.startActivity
import android.widget.Toast



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

    //初期化されて利用できたときに呼ばれる
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap //インスタンスを得られるので保持しておく

        //宇部ラーメン位置情報
        var ramen1 = "中華そば一久 新川店"
        var ramen1_latlng = LatLng(33.95721, 131.242702)
        var ramen2 = "三久ラーメン 常盤店"
        var ramen2_latlng = LatLng(33.952062, 131.275688)
        var ramen3 = "味の三平"
        var ramen3_latlng = LatLng(33.956592, 131.243595)
        var ramen4 = "秀ちゃんラーメン"
        var ramen4_latlng = LatLng(33.954652, 131.243118)
        var ramen5 = "大阪屋"
        var ramen5_latlng = LatLng(33.949279, 131.24767)


        //地図へのマーカーの設定方法
        mMap.addMarker(MarkerOptions().position(ramen1_latlng).title(ramen1))
        mMap.addMarker(MarkerOptions().position(ramen2_latlng).title(ramen2))
        mMap.addMarker(MarkerOptions().position(ramen3_latlng).title(ramen3))
        mMap.addMarker(MarkerOptions().position(ramen4_latlng).title(ramen4))
        mMap.addMarker(MarkerOptions().position(ramen5_latlng).title(ramen5))


        //地図の移動　画面上に表示される場所を指定する
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ramen1_latlng))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ramen2_latlng))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ramen3_latlng))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ramen4_latlng))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ramen5_latlng))

        //マップのズーム絶対値指定　1: 世界 5: 大陸 10:都市 15:街路 20:建物 ぐらいのサイズ
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15F))

        //1段階(レベル)のズーム
        mMap.moveCamera(CameraUpdateFactory.zoomIn())

        //複数段階のズーム
        mMap.moveCamera(CameraUpdateFactory.zoomBy(2F))

        //地図を移動して特定段階のズーム
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ramen1_latlng, 15F))

        //指定ピクセルだけスクロール
        mMap.moveCamera(CameraUpdateFactory.scrollBy(100F, 100F))

        //カメラの回転、東西南北の向き、見下ろす角度などの指定
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.builder().let {
            //位置の指定必須　ないとエラーになる
            it.target(ramen1_latlng)
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

        /*
        //出発地から目的地までのルートをプロットする
        mMap.addMarker(MarkerOptions().position(latLngOrigin).title("Ayala"))
        mMap.addMarker(MarkerOptions().position(latLngDestination).title("SM City"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngOrigin, 14.5f))
        */
    }
}
