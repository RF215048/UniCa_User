package com.enpit.tatsuya.uni_ca_user

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_input.*
import org.jetbrains.anko.startActivity

class Input : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val userID     = pref.getString("USER_ID","")
        val password   = pref.getString("PASSWORD","")
        val mailAdress = pref.getString("MAIL_ADRESS","")
        val birthday   = pref.getString("BIRTHDAY","")
        val gender     = pref.getString("GENDER","")

        //次へボタンを押したときの処理（各記入要項を満たせば次の画面へ）
        button_Next.setOnClickListener { View ->
            startActivity<Confirmation>()
            saveData()
        }
        //戻るボタンを押したときの処理
        button_Back.setOnClickListener { view ->
            startActivity<Accept>()
        }
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            genderText.text = findViewById<RadioButton>(checkedId).text
        }
    }
    private fun saveData() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        editor
            .putString("USER_ID", editText_UserID.text.toString())
            .putString("PASSWORD", editText_Password.text.toString())
            .putString("MAIL_ADRESS", editText_Email.text.toString())
            .putString("BIRTHDAY", editText_Birthday.text.toString())
            .putString("GENDER", genderText.text.toString())
            .apply()
    }
}
