package com.example.todayquote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlin.random.Random

class QuoteMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quote_main_activity)
        var quoteText = findViewById<TextView>(R.id.Quote)
        var quoteFrom = findViewById<TextView>(R.id.Person)

        var quotes = mutableListOf<Quote>()
        quotes.add(Quote(1, "명언 내용 1", "출처 1"))
        quotes.add(Quote(2, "명언 내용 2", "출처 2"))
        quotes.add(Quote(3, "명언 내용 3", "출처 3"))

        var randomIndex = Random.nextInt(0, 3)
        var randomQuote = quotes[randomIndex]

        quoteText.text = randomQuote.sentence
        quoteFrom.text = randomQuote.from
    }
}