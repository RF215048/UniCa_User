package com.enpit.tatsuya.uni_ca_user

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_shop_info.*
import org.jetbrains.anko.browse
import org.jetbrains.anko.startActivity
import kotlin.concurrent.timer

class ShopInfo : AppCompatActivity() {

    private val resources = listOf(
        R.drawable.b20190126_1,
        R.drawable.b20190126_2,
        R.drawable.b20190126_3
    )


    private var position = 0
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_info)

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        //val shopImage     = pref.getString("SHOP_IMAGE","")
        val shopName = pref.getString("SHOP_NAME", "")
        val shopNum = pref.getInt("SHOP_NUM", 0)

        button_Banner.setBackgroundResource(resources[0])

        timer(period = 5000) {
            handler.post {
                movePosition(1)
            }
        }
        button_Banner.setOnClickListener { view ->
            if(position == 0){ browse("https://www.google.co.jp/") }
            if(position == 1){ browse("https://www.google.co.jp/") }
            if(position == 2){ browse("https://www.google.co.jp/") }
        }

        //追加した店名
        val database = FirebaseDatabase.getInstance().getReference("shopname")
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){
                val shopname = snapshot.value.toString()
                textView_name.text = shopname
            }
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        //マップ検索への画面遷移の処理
        button_MapSearch.setOnClickListener { view ->
            startActivity<Maps>()
        }
        //店舗検索への画面遷移の処理
        button_ShopSearch.setOnClickListener { view ->
            startActivity<ShopSearch>()
        }
        //各種設定への画面遷移の処理
        button_Settings.setOnClickListener { view ->
            startActivity<Settings>()
        }

        //チケット1をタップしたら店の画像と店舗名を記憶する
        button_ticket1.setOnClickListener { view ->
            val editor = pref.edit()
            editor
                //.putString("SHOP_IMAGE", button_ticket1.text.toString())
                .putString("SHOP_NAME", textView_name1.text.toString())
                .putInt("SHOP_NUM", 1)
                .apply()
            startActivity<ServiceTicket>()
        }
        //チケット2をタップしたら店の画像と店舗名を記憶する
        button_ticket2.setOnClickListener { view ->
            val editor = pref.edit()
            editor
                //.putString("SHOP_IMAGE", button_ticket2.text.toString())
                .putString("SHOP_NAME", textView_name2.text.toString())
                .putInt("SHOP_NUM", 2)
                .apply()
            startActivity<ServiceTicket>()
        }
        //チケット3をタップしたら店の画像と店舗名を記憶する
        button_ticket.setOnClickListener { view ->
            val editor = pref.edit()
            editor
                //.putString("SHOP_IMAGE", button_ticket2.text.toString())
                .putString("SHOP_NAME", textView_name.text.toString())
                .putInt("SHOP_NUM", 3)
                .apply()
            startActivity<ServiceTicket>()
        }

        button_stampRally.setOnClickListener { view ->
            startActivity<StampRally>()
        }
    }

    private fun movePosition(move: Int) {
        position += move
        if (position >= resources.size) {
            position = 0
        } else if (position < 0) {
            position = resources.size - 1
        }
        button_Banner.setBackgroundResource(resources[position])
    }
}

