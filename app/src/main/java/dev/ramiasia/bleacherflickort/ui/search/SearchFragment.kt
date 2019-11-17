package dev.ramiasia.bleacherflickort.ui.search

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.ramiasia.bleacherflickort.R
import dev.ramiasia.bleacherflickort.model.entity.SearchImage


class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var cardRecyclerView: RecyclerView
    private lateinit var imageListAdapter: SearchImagesRecyclerViewAdapter
    private lateinit var imageViewModel: SearchViewModel
    private var currentlySearchedTerm = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.search_fragment, container, false)
        cardRecyclerView = view.findViewById(R.id.searchResultsRecyclerView)
        cardRecyclerView.layoutManager = GridLayoutManager(context, 3)
        imageListAdapter = SearchImagesRecyclerViewAdapter(context)
        cardRecyclerView.adapter = imageListAdapter
        imageViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        imageViewModel.images.observe(this, Observer<List<SearchImage>> {
            imageListAdapter.images = it
        })
        cardRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    imageViewModel.getImages(currentlySearchedTerm)
                }
            }
        })

        val editText: EditText = view.findViewById(R.id.searchEditText)
        editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                if (editText.text.isNotEmpty()) {
                    currentlySearchedTerm = editText.text.toString()
                    imageViewModel.getImages(currentlySearchedTerm)
                    val handler = Handler()
                    handler.postDelayed({
                        cardRecyclerView.scrollToPosition(0)
                    }, 1000)
                }
            }
            false
        }

        return view
    }

}
