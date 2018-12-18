package com.enpit.tatsuya.uni_ca_user

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_confirmation.*
import org.jetbrains.anko.startActivity

class Confirmation : AppCompatActivity() {

    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val userID     = pref.getString("USER_ID", "")
        val password   = pref.getString("PASSWORD", "")
        val mailAdress = pref.getString("MAIL_ADRESS", "")
        val birthday   = pref.getString("BIRTHDAY", "")
        val gender     = pref.getString("GENDER", "")

        textView_UseID.text    = userID
        textView_Password.text = password
        textView_Email.text    = mailAdress
        textView_Birthday.text = birthday
        textView_Gender.text   = gender

        //データベースの読み込み
        /*
        ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.value
                textView.text = value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("onCancelled", "error:", error.toException())
            }
        })
        */


        //送信ボタンを押したときの処理
        button_Send.setOnClickListener { view ->
            //データベースの書き込み処理
            val key1 = "UserID"
            val key2 = "Password"
            val key3 = "EmailAdress"
            val key4 = "Birthday"
            val key5 = "Gender"

            val database = FirebaseDatabase.getInstance()
            val ref1 = database.getReference(key1)
            val ref2 = database.getReference(key2)
            val ref3 = database.getReference(key3)
            val ref4 = database.getReference(key4)
            val ref5 = database.getReference(key5)

            ref1.setValue(userID)
            ref2.setValue(password)
            ref3.setValue(mailAdress)
            ref4.setValue(birthday)
            ref5.setValue(gender)

            val mAuth = FirebaseAuth.getInstance()

            //新規ユーザー登録処理
            mAuth.createUserWithEmailAndPassword(userID as String, password as String)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        // 成功処理
                    } else {
                        // 失敗処理
                    }
                }
            //ログイン認証
            mAuth.signInWithEmailAndPassword(userID, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        // 成功処理
                    } else {
                        // 失敗処理
                    }
                }

            startActivity<ShopInfo>()
        }
        //戻るボタンを押したときの処理
        button_Back.setOnClickListener { view ->
            startActivity<Input>()
        }
    }
}
