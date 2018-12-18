package com.enpit.tatsuya.uni_ca_user

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_service_ticket.*
import org.jetbrains.anko.startActivity

class ServiceTicket : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_ticket)

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        //val shopImage     = pref.getString("SHOP_IMAGE","")
        val shopName = pref.getString("SHOP_NAME", "")
        val serviceName = pref.getString("SERVICE_NAME", "")
        val shopNum = pref.getInt("SHOP_NUM", 0)

        shop_Name.text = shopName

        if (shopNum == 0) {
            imageView_shopImage.setImageResource(R.drawable.ic_launcher_foreground)
        } else if (shopNum == 1) {
            imageView_shopImage.setImageResource(R.drawable.ramen_ikkyu01)
        } else if (shopNum == 2) {
            imageView_shopImage.setImageResource(R.drawable.ramen_sankyuu01)
        }

        //マップ検索への画面遷移の処理
        button_MapSearch.setOnClickListener { view ->
            startActivity<Maps>()
        }
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

        //QRコードに店舗名とサービス名を登録しておく
        qrcode_button.setOnClickListener { view ->
            val editor = pref.edit()
            editor
                //.putString("SHOP_IMAGE", shop_Name.text.toString())
                .putString("SHOP_NAME", qrcode_button.text.toString())
                .apply()
            startActivity<QrcodeSending>()
        }
        qrcode_button2.setOnClickListener { view ->
            val editor = pref.edit()
            editor
                //.putString("SHOP_IMAGE", shop_Name.text.toString())
                .putString("SHOP_NAME", qrcode_button2.text.toString())
                .apply()
            startActivity<QrcodeSending>()
        }
        qrcode_button3.setOnClickListener { view ->
            val editor = pref.edit()
            editor
                //.putString("SHOP_IMAGE", shop_Name.text.toString())
                .putString("SHOP_NAME", qrcode_button3.text.toString())
                .apply()
            startActivity<QrcodeSending>()
        }
    }
}
