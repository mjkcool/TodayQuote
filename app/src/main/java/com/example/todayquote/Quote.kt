package com.example.todayquote

/*
코틀린의 '데이터 클래스'.
- 데이터(정적 속성)을 위주로 하는 클래스: 메서드 잘 사용X
ex) data class Person(var name:String, var age:Int)
*/
data class Quote(
    var idx:Int,
    var sentence:String,
    val from:String
)