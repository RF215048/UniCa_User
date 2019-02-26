package com.enpit.tatsuya.uni_ca_user

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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
         else if (shopNum == 3) {
            imageView_shopImage.setImageResource(R.drawable.sample)
            val database_key1 = FirebaseDatabase.getInstance().getReference("pr")
            database_key1.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot){
                    val pr = snapshot.value.toString()
                    textExplain.text = pr
                }
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
            val database_key2 = FirebaseDatabase.getInstance().getReference("coupon1")
            database_key2.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot){
                    val coupon1 = snapshot.value.toString()
                    qrcode_button.text = coupon1
                }
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
            val database_key3 = FirebaseDatabase.getInstance().getReference("coupon2")
            database_key3.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot){
                    val coupon2 = snapshot.value.toString()
                    qrcode_button2.text = coupon2
                }
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
            val database_key4 = FirebaseDatabase.getInstance().getReference("coupon3")
            database_key4.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot){
                    val coupon3 = snapshot.value.toString()
                    qrcode_button3.text = coupon3
                }
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
            val database_key5 = FirebaseDatabase.getInstance().getReference("title")
            database_key5.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot){
                    val title = snapshot.value.toString()
                    text_Title.text = title
                }
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
            val database_key6 = FirebaseDatabase.getInstance().getReference("detail")
            database_key6.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot){
                    val detail = snapshot.value.toString()
                    text_detail.text = detail
                }
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
            val database_key7 = FirebaseDatabase.getInstance().getReference("recommended")
            database_key7.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot){
                    val recommended = snapshot.value.toString()
                    textRecommend.text = recommended
                }
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })
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
                .putString("SERVICE_NAME", qrcode_button.text.toString())
                .apply()
            startActivity<QrcodeSending>()
        }
        qrcode_button2.setOnClickListener { view ->
            val editor = pref.edit()
            editor
                //.putString("SHOP_IMAGE", shop_Name.text.toString())
                .putString("SERVICE_NAME", qrcode_button2.text.toString())
                .apply()
            startActivity<QrcodeSending>()
        }
        qrcode_button3.setOnClickListener { view ->
            val editor = pref.edit()
            editor
                //.putString("SHOP_IMAGE", shop_Name.text.toString())
                .putString("SERVICE_NAME", qrcode_button3.text.toString())
                .apply()
            startActivity<QrcodeSending>()
        }
    }
}
