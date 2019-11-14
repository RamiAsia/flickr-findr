package dev.ramiasia.bleacherflickort.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.ramiasia.bleacherflickort.R
import dev.ramiasia.bleacherflickort.model.entity.SearchImage

class SearchImagesRecyclerViewAdapter(context: Context?) :
    RecyclerView.Adapter<SearchImagesRecyclerViewAdapter.ImageViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    var images: List<SearchImage> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val card = inflater.inflate(R.layout.image_card, parent, false)
        return ImageViewHolder(card)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        if (images.isNotEmpty()) {
            val image = images[position]
            holder.imageView.setImageResource(R.drawable.ic_launcher_background)
            holder.titleTextView.text = image.title
        }
    }

    override fun getItemCount(): Int {
        if (images.isNullOrEmpty()) {
            return 0
        }
        return images.size
    }

    class ImageViewHolder(card: View) : RecyclerView.ViewHolder(card) {
        val titleTextView: TextView = card.findViewById(R.id.imageTitle)
        val imageView: ImageView = card.findViewById(R.id.flickrImage)
    }
}