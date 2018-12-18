package com.enpit.tatsuya.uni_ca_user

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_accept.*
import org.jetbrains.anko.startActivity


class Accept : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accept)

        var currentId = radioGroup.checkedRadioButtonId

        //次へボタンを押したときの処理（同意すると画面移動）
        button_Next.setOnClickListener { view ->
            if (currentId == radioButton_Agree.id) {
                startActivity<Input>()
            }
        }
        //戻るボタンを押したときの処理
        button_Back.setOnClickListener { view ->
            startActivity<Login>()
        }
        //ラジオボタンの処理について
        radioGroup.setOnCheckedChangeListener{
            group, checkedId ->
            currentId = radioGroup.checkedRadioButtonId
        }
    }
}
