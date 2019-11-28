package dev.ramiasia.flickrfindr.ui.search

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
import dev.ramiasia.flickrfindr.R
import dev.ramiasia.flickrfindr.data.entity.SearchImage
import dev.ramiasia.flickrfindr.viewmodel.SearchViewModel

/**
 * Fragment allowing users to search for and see images.
 */
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

        //Set a grid layout for the images displayed.
        //TODO: Span different numbers of columns depending on screen size/resolution
        cardRecyclerView.layoutManager = GridLayoutManager(context, 3)

        //Set the RecyclerViewAdapter for our images
        imageListAdapter = SearchImagesRecyclerViewAdapter(context)
        cardRecyclerView.adapter = imageListAdapter

        /*
         * Listen for scrolling events. When the user reaches the bottom (RecyclerView can't scroll
         * any more), then load the next set of images.
         */
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

        //Observe the LiveData containing the images.
        imageViewModel.images.observe(this, Observer<List<SearchImage>> {
            imageListAdapter.images = it
        })

        //Searched terms history RecyclerView setup
        searchTermsRecyclerView = view.findViewById(R.id.searchedTermsRecyclerView)
        searchTermsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        searchTermsListAdapter = SearchedTermsRecyclerViewAdapter(context, this)
        searchTermsRecyclerView.adapter = searchTermsListAdapter


        //Observe the LiveData updating the list of searched terms similar to the user's input
        imageViewModel.searchedTerms.observe(this, Observer {
            println(it.size)
            searchTermsListAdapter.terms = it
        })


        //Instantiate EditText for entering the user's desired search term.
        editText = view.findViewById(R.id.searchEditText)

        //Set the listener for entering a value to be searched:
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

        //As the user types in the search bar, update the list of similarly searched terms
        editText.doOnTextChanged { text, _, _, _ ->
            imageViewModel.searchTermsLike(if (text?.length!! > 0) text.toString().trim() else "")
        }

        //Return the view inflated in the beginning of the method.
        return view
    }

    /**
     * Handles the event of a term selected from the list of previously searched terms.
     */
    override fun onSearchTermPressed(searchTerm: String) {
        //Set the text in the search bar accordingly
        editText.setText(searchTerm)

        //Move the cursor accordingly to the end of the term
        editText.setSelection(searchTerm.length)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Upon initial loading of the fragment, load a set of images to show the user.
        //The default images load as a result of searching "Landscape"
        currentlySearchedTerm = getString(R.string.landscape)
        imageViewModel.getImages(currentlySearchedTerm, true)
    }

}
