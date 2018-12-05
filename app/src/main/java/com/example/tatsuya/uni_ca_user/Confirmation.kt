package com.example.tatsuya.uni_ca_user

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_confirmation.*
import org.jetbrains.anko.startActivity

class Confirmation : AppCompatActivity() {

    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val userID = pref.getString("USER_ID", "")
        val password = pref.getString("PASSWORD", "")
        val mailAdress = pref.getString("MAIL_ADRESS", "")
        val birthday = pref.getString("BIRTHDAY", "")
        val gender = pref.getString("GENDER", "")

        textView_UseID.text = userID
        textView_Password.text = password
        textView_Email.text = mailAdress
        textView_Birthday.text = birthday
        textView_Gender.text = gender

        //送信ボタンを押したときの処理
        button_Send.setOnClickListener { view ->
            startActivity<ShopInfo>()
        }
        //戻るボタンを押したときの処理
        button_Back.setOnClickListener { view ->
            startActivity<Input>()
        }
    }
}
