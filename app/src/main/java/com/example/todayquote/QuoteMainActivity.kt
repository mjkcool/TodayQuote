package com.example.todayquote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlin.random.Random

class QuoteMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quote_main_activity)

        //[정리] 세미콜론 필요없음
        var quoteText = findViewById<TextView>(R.id.Quote)
        var quoteFrom = findViewById<TextView>(R.id.Person)

        //[정리] 코틀린은 인스턴스 생성시 new 식별자가 필요없음.
        var quotes = mutableListOf<Quote>()
        quotes.add(Quote(1, "명언 내용 1", "출처 1"))
        quotes.add(Quote(2, "명언 내용 2", "출처 2"))
        quotes.add(Quote(3, "명언 내용 3", "출처 3"))

        var randomIndex = Random.nextInt(0, 3) //[정리] 0~2
        var randomQuote = quotes.get(randomIndex)//quotes[randomIndex]

        //[정리] getter, setter가 정의되어있으면 속성 이름만으로 접근 가능
        //text 속성값에 접근(setText())
        quoteText.text = randomQuote.sentence
        quoteFrom.text = randomQuote.from
    }
}