package dev.ramiasia.bleacherflickort.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.ramiasia.bleacherflickort.R
import dev.ramiasia.bleacherflickort.data.entity.SearchTerm

class SearchedTermsRecyclerViewAdapter(
    context: Context?,
    val onSearchTermPressedListener: OnSearchTermPressedListener
) :
    RecyclerView.Adapter<SearchedTermsRecyclerViewAdapter.SearchedTermViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    var terms: List<SearchTerm> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedTermViewHolder {
        val item = inflater.inflate(R.layout.searched_term_item, parent, false)
        item.setOnClickListener {
            terms = ArrayList()
            onSearchTermPressedListener.onSearchTermPressed(item.findViewById<TextView>(R.id.searchedTerm).text.toString())
        }
        return SearchedTermViewHolder(item)
    }

    override fun getItemCount(): Int {
        return terms.size
    }

    override fun onBindViewHolder(holder: SearchedTermViewHolder, position: Int) {
        holder.termTextView.text = terms[position].term
    }

    class SearchedTermViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val termTextView: TextView = item.findViewById(R.id.searchedTerm)
    }

    interface OnSearchTermPressedListener {
        fun onSearchTermPressed(searchTerm: String)
    }
}