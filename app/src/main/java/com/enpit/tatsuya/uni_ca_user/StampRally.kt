package com.enpit.tatsuya.uni_ca_user

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.View
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlinx.android.synthetic.main.activity_stamp_rally.*
import org.jetbrains.anko.startActivity
import android.widget.TextView
import com.enpit.tatsuya.uni_ca_user.R.id.*


class StampRally : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stamp_rally)

        var stampNum = 0

        val result : IntentResult

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

        //QRコードのスキャン
        button_QRcodeReader.setOnClickListener { view ->
            IntentIntegrator(this).initiateScan()
        }

        /*
        stamp1.setVisibility(View.INVISIBLE)
        stamp2.setVisibility(View.INVISIBLE)
        stamp3.setVisibility(View.INVISIBLE)
        */

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        if(stampNum == 1){
                stamp1.setVisibility(View.VISIBLE)
                stamp2.setVisibility(View.INVISIBLE)
                stamp3.setVisibility(View.INVISIBLE)
            }
            if(stampNum == 2){
                stamp1.setVisibility(View.VISIBLE)
                stamp2.setVisibility(View.VISIBLE)
                stamp3.setVisibility(View.INVISIBLE)
            }
            if(stampNum == 3){
                stamp1.setVisibility(View.VISIBLE)
                stamp2.setVisibility(View.VISIBLE)
                stamp3.setVisibility(View.VISIBLE)
            }
            if(stampNum == 4){
                stampNum = 0
                stamp1.setVisibility(View.INVISIBLE)
                stamp2.setVisibility(View.INVISIBLE)
                stamp3.setVisibility(View.INVISIBLE)
            }
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        var stampNum = pref.getInt("STAMP_NUM", 1)

        // null の場合
        if (intentResult == null) {
            super.onActivityResult(requestCode, resultCode, data)
            return
        }
        // カメラで読み取った情報をラベルに表示
        val textViewScannedData = findViewById(R.id.textScaned) as TextView
        textViewScannedData.text = intentResult.contents

        stampNum += 1
        if(stampNum == 1){
            stamp1.setVisibility(View.VISIBLE)
            stamp2.setVisibility(View.INVISIBLE)
            stamp3.setVisibility(View.INVISIBLE)
        }
        if(stampNum == 2){
            stamp1.setVisibility(View.VISIBLE)
            stamp2.setVisibility(View.VISIBLE)
            stamp3.setVisibility(View.INVISIBLE)
        }
        if(stampNum == 3){
            stamp1.setVisibility(View.VISIBLE)
            stamp2.setVisibility(View.VISIBLE)
            stamp3.setVisibility(View.VISIBLE)
        }
        if(stampNum == 4){
            stampNum = 0
            stamp1.setVisibility(View.INVISIBLE)
            stamp2.setVisibility(View.INVISIBLE)
            stamp3.setVisibility(View.INVISIBLE)
        }

        val editor = pref.edit()
        editor
            .putInt("STAMP_NUM", stampNum)
            .apply()
    }
}
