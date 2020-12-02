package com.example.todayquote

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuoteEditAdapter(val dataList: List<Quote>):
    RecyclerView.Adapter<QuoteEditAdapter.QuoteItemViewHolder>(){
    class QuoteItemViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        lateinit var quote: Quote
        val quoteTextEdit = view.findViewById<TextView>(R.id.quote_text_edit)
        val quoteFromEdit = view.findViewById<TextView>(R.id.quote_from_edit)
        val deleteBtn = view.findViewById<Button>(R.id.quote_delete_btn)
        val modifyBtn = view.findViewById<Button>(R.id.quote_modify_btn)

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