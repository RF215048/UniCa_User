package com.example.tatsuya.uni_ca_user

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
        realm = Realm.getDefaultInstance()

        val maxId = realm.where<PersonalInfo>().max("id")
        val nextId = (maxId?.toLong() ?: 0L) + 1
        val personalInfo = realm.createObject<PersonalInfo>(nextId)

        textView_UseID.text = personalInfo.userID
        textView_Password.text = personalInfo.password
        textView_Email.text = personalInfo.eMail
        textView_Birthday.text = personalInfo.birthday.toString()
        textView_Gender.text = personalInfo.gender

        //送信ボタンを押したときの処理
        button_Send.setOnClickListener { view ->
            startActivity<Settings>()
        }
        //戻るボタンを押したときの処理
        button_Back.setOnClickListener { view ->
            startActivity<Input>()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
