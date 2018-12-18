package com.enpit.tatsuya.uni_ca_user

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_shop_info.*
import org.jetbrains.anko.browse
import org.jetbrains.anko.startActivity
import kotlin.concurrent.timer

class ShopInfo : AppCompatActivity() {

    private val resources = listOf(
        R.drawable.ramen_ikkyu01_banner,
        R.drawable.ramen_oosakaya01_banner,
        R.drawable.ramen_sankyuu01_banner,
        R.drawable.ramen_sanpei01_banner,
        R.drawable.ramen_syuuchan01_banner
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
            if(position == 0){ browse("http://odcs.bodik.jp/352021/wp-content/uploads/sites/8/2016/08/ramen_uberamendon1.jpg") }
            if(position == 1){ browse("http://odcs.bodik.jp/352021/wp-content/uploads/sites/8/2016/08/ramen_oosakaya01.jpg") }
            if(position == 2){ browse("http://odcs.bodik.jp/352021/wp-content/uploads/sites/8/2016/08/ramen_sankyuu01.jpg") }
            if(position == 3){ browse("http://odcs.bodik.jp/352021/wp-content/uploads/sites/8/2016/08/ramen_sanpei01.jpg") }
            if(position == 4){ browse("http://odcs.bodik.jp/352021/wp-content/uploads/sites/8/2016/08/ramen_syuuchan01.jpg") }
        }

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
            //val pref = PreferenceManager.getDefaultSharedPreferences(this)
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
            //val pref = PreferenceManager.getDefaultSharedPreferences(this)
            val editor = pref.edit()
            editor
                //.putString("SHOP_IMAGE", button_ticket2.text.toString())
                .putString("SHOP_NAME", textView_name2.text.toString())
                .putInt("SHOP_NUM", 2)
                .apply()
            startActivity<ServiceTicket>()
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

