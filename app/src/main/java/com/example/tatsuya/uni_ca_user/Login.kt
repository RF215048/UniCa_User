package com.example.tatsuya.uni_ca_user

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //新規登録のボタンを押したときの処理
        button_new.setOnClickListener { view ->
            startActivity<Accept>()
        }

        //ログインボタンを押したときの処理（IDとパスワードが一致しなければ赤文字でIDかパスワードが間違っていますと表示）
        button_Login.setOnClickListener { view ->
            //店舗情報のアクティビティを表示
            startActivity<ShopInfo>()
        }
    }
}
