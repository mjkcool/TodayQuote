package com.example.todayquote

import android.content.SharedPreferences

/* [정리]
코틀린의 '데이터 클래스'.
- 데이터(정적 속성)을 위주로 하는 클래스: 메서드 잘 사용X
ex) data class Person(var name:String, var age:Int)
*/
data class Quote(var idx:Int, var text:String, val from:String){
    companion object { //클래스 메서드

        fun saveToPreference(
            pref: SharedPreferences,
            idx: Int, text: String, from: String = ""
        ): Quote {
            //SharedPreference 객체에 명언 데이터 저장
            var editor = pref.edit()
            //키, 값 저장
            editor.putString("$idx.text", text) //명언 문장
            editor.putString("$idx.from", from)
            editor.apply()

            return Quote(idx, text, from)
        }

        fun removeQuoteFromPreference(pref: SharedPreferences, idx: Int) {
            //해당 위치의 데이터 제거
            var editor = pref.edit()
            editor.remove("$idx.text")
            editor.remove("$idx.from")
            editor.apply()
        }

        fun getQuotesFromPreference(pref: SharedPreferences) : List<Quote>{
            val quotes = mutableListOf<Quote>()

            for (i in 0 until 20) { //i가 0부터 19까지
                val quoteText = pref.getString("$i.text", "")!! //null 불허타입으로 바꿈
                val quoteFrom = pref.getString("$i.from", "")!!
                if(quoteText.isNotBlank()){
                    quotes.add(Quote(i, quoteText, quoteFrom))
                }
            }
            return quotes
        }
    }
}