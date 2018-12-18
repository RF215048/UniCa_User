package com.enpit.tatsuya.uni_ca_user

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.AndroidRuntimeException
import com.google.zxing.WriterException
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.activity_qrcode_sending.*
import org.jetbrains.anko.startActivity


class QrcodeSending : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode_sending)

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val shopName     = pref.getString("SHOP_NAME","")
        val serviceName     = pref.getString("SERVICE_NAME","")

        shop_Name.text = shopName
        servise_Name.text = serviceName

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

        val data = shopName.toString() //QRコード化する文字列
        val size = 500 //QRコード画像の大きさを指定(pixel)

        try {
            val barcodeEncoder = BarcodeEncoder()
            //QRコードをBitmapで作成
            val bitmap = barcodeEncoder.encodeBitmap(data, BarcodeFormat.QR_CODE, size, size)
            //作成したQRコードを画面上に配置
            imageQRcode.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            throw AndroidRuntimeException("Barcode Error.", e)
        }
    }
}
