package com.example.todayquote

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlin.random.Random

class QuoteMainActivity : AppCompatActivity() {
    //Activity는 생성자는 안드로이드만 접근할 수 있음.
    /* lateinit: 생성자에서 초기화를 해주지 못하나 null을 허용하지 않도록 한다.
                 반드시 이 객체에 접근하기 전 이 객체를 초기화하겠다는 선언이자 약속
     */
    private lateinit var pref: SharedPreferences //생성만 하였을 때 null 허용 X
    private lateinit var quotes: List<Quote> //때문에 사용전 반드시 초기화 필요


    fun initializeQuotes(){
        val initialized = pref.getBoolean("initialized", false)
        if(!initialized){ //초기화 여부 확인

            Quote.saveToPreference(pref, 0, "명언", "사람")

            val editor = pref.edit()
            editor.putBoolean("initialized", true)
            editor.apply()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) { //생성 시점에 호출되는 콜백 함수
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quote_main_activity)

        //[정리] 세미콜론 필요없음
        var quoteText = findViewById<TextView>(R.id.Quote)
        var quoteFrom = findViewById<TextView>(R.id.Person)


        //Preference 객체 가져오기 (파일 이름은 quotes)
        pref = this.getSharedPreferences("quotes", Context.MODE_PRIVATE)
        initializeQuotes()

        quotes = Quote.getQuotesFromPreference(pref)
        if(quotes.isNotEmpty()){
            val randomIdx = Random.nextInt(quotes.size)
            val randomQuote = quotes[randomIdx]
            quoteText.text = randomQuote.text
            quoteFrom.text = randomQuote.from
        }else{
            quoteText.text = "저장된 명언이 없습니다."
            quoteFrom.text = ""
        }

        //[정리] 코틀린은 인스턴스 생성시 new 식별자가 필요없음.
//        var quotes = mutableListOf<Quote>()
//        quotes.add(Quote(1, "명언 내용 1", "출처 1"))
//        quotes.add(Quote(2, "명언 내용 2", "출처 2"))
//        quotes.add(Quote(3, "명언 내용 3", "출처 3"))
//
//        var randomIndex = Random.nextInt(0, 3) //[정리] 0~2
//        var randomQuote = quotes.get(randomIndex)//quotes[randomIndex]
//
//        //[정리] getter, setter가 정의되어있으면 속성 이름만으로 접근 가능
//        //text 속성값에 접근(setText())
//        quoteText.text = randomQuote.sentence
//        quoteFrom.text = randomQuote.from

        /*
        var sp = getSharedPreferences("filename", Context.MODE_PRIVATE);

        //데이터를 저장
        val editor = sp.edit();

        //키, 값 쌍이 필요(자바의 맵, 파이썬의 딕셔너리), 덮어쓰기 역시 가능
        editor.putInt("k1", 1)
        editor.putString("k2", "hello")
        
        editor.apply() // 데이터 저장시키기. = editor.commit()
        
        데이터 삭제
        editor.remove("k1")
        editor.clear()

        var value1 = sp.getInt("k1", -1) //default값을 지정해야함
        var value2 = sp.getString("k2", null)
        */
    }
}