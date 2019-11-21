package dev.ramiasia.bleacherflickort.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maximeroussy.invitrode.WordGenerator
import dev.ramiasia.bleacherflickort.R
import dev.ramiasia.bleacherflickort.data.entity.SearchImage


class SearchFragment : Fragment(), SearchedTermsRecyclerViewAdapter.OnSearchTermPressedListener {

    private lateinit var cardRecyclerView: RecyclerView
    private lateinit var imageListAdapter: SearchImagesRecyclerViewAdapter

    private lateinit var searchTermsRecyclerView: RecyclerView
    private lateinit var searchTermsListAdapter: SearchedTermsRecyclerViewAdapter
    private lateinit var editText: EditText

    private lateinit var imageViewModel: SearchViewModel
    private var currentlySearchedTerm = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.search_fragment, container, false)

        //Image card RecyclerView setup
        cardRecyclerView = view.findViewById(R.id.searchResultsRecyclerView)
        cardRecyclerView.layoutManager = GridLayoutManager(context, 3)
        imageListAdapter = SearchImagesRecyclerViewAdapter(context)
        cardRecyclerView.adapter = imageListAdapter
        cardRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    imageViewModel.getImages(currentlySearchedTerm, false)
                }
            }
        })

        //ImageViewModel setup
        imageViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        imageViewModel.images.observe(this, Observer<List<SearchImage>> {
            imageListAdapter.images = it
        })

        //Searched terms RecyclerView setup
        searchTermsRecyclerView = view.findViewById(R.id.searchedTermsRecyclerView)
        searchTermsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        searchTermsListAdapter = SearchedTermsRecyclerViewAdapter(context, this)
        searchTermsRecyclerView.adapter = searchTermsListAdapter



        imageViewModel.searchedTerms.observe(this, Observer {
            println(it.size)
            searchTermsListAdapter.terms = it
        })


        editText = view.findViewById(R.id.searchEditText)
        editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                if (editText.text.isNotEmpty()) {
                    currentlySearchedTerm = editText.text.toString()
                    imageViewModel.getImages(currentlySearchedTerm, false)
                    searchTermsListAdapter.terms = ArrayList()
                }
            }
            false
        }

        editText.doOnTextChanged { text, _, _, _ ->
            println("Text changed to ${text.toString().trim()}")
            imageViewModel.searchTermsLike(if (text?.length!! > 0) text.toString().trim() else "")
        }

        return view
    }

    override fun onSearchTermPressed(searchTerm: String) {
        editText.setText(searchTerm)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentlySearchedTerm = WordGenerator().newWord(5)
        imageViewModel.getImages(currentlySearchedTerm, true)
    }

}
