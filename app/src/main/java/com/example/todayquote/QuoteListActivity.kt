package com.example.todayquote

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.quote_list_item.*

class QuoteListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote_list)

        val currentQuoteSize = intent.getIntExtra("quote_size", 0)

        Toast.makeText(this, "현재 ${currentQuoteSize}개의 명언이 저장되어있습니다.", Toast.LENGTH_LONG).show()

        val recyclerView = findViewById<RecyclerView>(R.id.quote_list)
        recyclerView.setHasFixedSize(false);
        recyclerView.layoutManager = LinearLayoutManager(this)

        var pref = this.getSharedPreferences("quotes", Context.MODE_PRIVATE)
        val quotes = Quote.getQuotesFromPreference(pref)
        val adapter = QuoteAdapter(quotes)
        recyclerView.adapter = adapter

    }
}