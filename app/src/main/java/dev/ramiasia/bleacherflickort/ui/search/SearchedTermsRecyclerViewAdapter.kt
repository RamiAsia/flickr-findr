package dev.ramiasia.bleacherflickort.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.ramiasia.bleacherflickort.R
import dev.ramiasia.bleacherflickort.data.entity.SearchTerm

/**
 * [RecyclerView.Adapter] handling the corresponding [RecyclerView]'s rendering of terms searched by the user.
 */
class SearchedTermsRecyclerViewAdapter(
    context: Context?,
    private val onSearchTermPressedListener: OnSearchTermPressedListener
) :
    RecyclerView.Adapter<SearchedTermsRecyclerViewAdapter.SearchedTermViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    //Whenever the terms are set, notify the adapter that the data set has changed:
    var terms: List<SearchTerm> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    /**
     * Whenever an item is to be retrieved, generate a [SearchedTermViewHolder] with a specified
     * layout and set the user interaction.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedTermViewHolder {
        //Inflate the layout to be used by the holder.
        val item = inflater.inflate(R.layout.searched_term_item, parent, false)
        item.setOnClickListener {
            //Clear the displayed terms when one term is selected.
            terms = ArrayList()
            //Notify the listener that a term has been selected.
            onSearchTermPressedListener.onSearchTermPressed(item.findViewById<TextView>(R.id.searchedTerm).text.toString())
        }
        return SearchedTermViewHolder(item)
    }

    override fun getItemCount(): Int {
        return terms.size
    }

    /**
     * When binding the [SearchedTermViewHolder] set its [TextView]'s text to be the [SearchTerm]'s
     * term.
     */
    override fun onBindViewHolder(holder: SearchedTermViewHolder, position: Int) {
        holder.termTextView.text = terms[position].term
    }

    /**
     * [RecyclerView.ViewHolder] containing the appropriate visual elements used for the
     * [RecyclerView.Adapter].
     */
    class SearchedTermViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val termTextView: TextView = item.findViewById(R.id.searchedTerm)
    }

    /**
     * An interface used for notifying a listener that a [SearchTerm] has been selected from the
     * [RecyclerView].
     */
    interface OnSearchTermPressedListener {
        fun onSearchTermPressed(searchTerm: String)
    }
}