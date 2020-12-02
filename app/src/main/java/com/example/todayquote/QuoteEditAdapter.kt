package com.example.todayquote

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class QuoteEditAdapter(private val dataList: List<Quote>):
    RecyclerView.Adapter<QuoteEditAdapter.QuoteItemViewHolder>(){
    class QuoteItemViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        lateinit var quote: Quote
        val quoteTextEdit = view.findViewById<TextView>(R.id.quote_text_edit)
        val quoteFromEdit = view.findViewById<TextView>(R.id.quote_from_edit)
        val quoteDeleteBtn = view.findViewById<Button>(R.id.quote_delete_btn)
        val quoteModifyBtn = view.findViewById<Button>(R.id.quote_modify_btn)

        init { //view 생성 이후 어디든 위치 가능, 주 생성자 호출 이후 바로 실행되는 코드 블록
            var pref = view.context.getSharedPreferences("quotes", Context.MODE_PRIVATE)
            quoteDeleteBtn.setOnClickListener { //삭제
                quoteTextEdit.setText("")
                quoteFromEdit.setText("")
                quote.text = ""
                quote.from = ""
                val pos = this.adapterPosition //반드시 adapterPosition을 사용해야함
                Quote.removeQuoteFromPreference(pref, pos)
                Toast.makeText(view.context, "삭제되었습니다.", Toast.LENGTH_LONG).show()
            }
            quoteModifyBtn.setOnClickListener { //수정
                quote.text = quoteTextEdit.text.toString()
                quote.from = quoteFromEdit.text.toString()
                val pos = this.adapterPosition
                Quote.saveToPreference(pref, pos, quote.text, quote.from)
            }
        }
        
        fun bind(q:Quote){
            quote = q
            quoteTextEdit.setText(quote.text)
            quoteFromEdit.setText(quote.from)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)

        return QuoteItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteItemViewHolder, position: Int) {
        // 뷰, 교체할 데이터
        val data = dataList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.quote_edit_list_item
    }
}