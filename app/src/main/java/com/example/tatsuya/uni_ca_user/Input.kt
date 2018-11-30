package com.example.tatsuya.uni_ca_user

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_input.*
import org.jetbrains.anko.startActivity
import java.lang.IllegalArgumentException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class Input : AppCompatActivity() {
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)
        realm = Realm.getDefaultInstance()
        var currentId = ""

        //次へボタンを押したときの処理（各記入要項を満たせば次の画面へ）
        button_Next.setOnClickListener {
            realm.executeTransaction {
                val maxId = realm.where<PersonalInfo>().max("id")
                val nextId = (maxId?.toLong() ?: 0L) + 1
                val personalInfo = realm.createObject<PersonalInfo>(nextId)

                personalInfo.userID = editText_UserID.text.toString()
                personalInfo.password = editText_Password.text.toString()
                personalInfo.eMail = editText_Email.text.toString()

                editText_Birthday.text.toString().toDate("yyyy/MM/dd")?.let {
                    personalInfo.birthday = it
                }

                personalInfo.gender = currentId
            }
        }
        //性別を選択したときの処理
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            currentId = findViewById<RadioButton>(checkedId).text as String
        }

        button_Next.setOnClickListener { view ->
            startActivity<Confirmation>()
        }
        //戻るボタンを押したときの処理
        button_Back.setOnClickListener { view ->
            startActivity<Accept>()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }


    fun String.toDate(pattern: String = "yyyy/MM/dd HH:mm"): Date? {
        val sdFormat = try {
            SimpleDateFormat(pattern)
        } catch (e: IllegalArgumentException) {
            null
        }
        val date = sdFormat?.let {
            try {
                it.parse(this)
            } catch (e: ParseException) {
                null
            }
        }
        return date
    }

}
