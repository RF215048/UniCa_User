package com.example.tatsuya.uni_ca_user

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class PersonalInfo : RealmObject() {
    @PrimaryKey
    var id       : Long    = 0

    var userID   : String  = ""      //ユーザーID
    var password : String  = ""      //パスワード
    var eMail    : String  = ""      //Eメール
    var birthday : Date    = Date()  //生年月日
    var gender   : String  = ""      //性別
}