package dev.ramiasia.bleacherflickort.ui.search

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.ramiasia.bleacherflickort.R
import dev.ramiasia.bleacherflickort.data.entity.SearchImage
import dev.ramiasia.bleacherflickort.ui.PictureDetailsActivity
import dev.ramiasia.bleacherflickort.utils.ImageUtils


class SearchImagesRecyclerViewAdapter(private val context: Context?) :
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
            holder.titleTextView.text =
                if (image.title.length < 40)
                    image.title
                else
                    image.title.substring(0, 37) + "..."

            holder.itemView.setOnClickListener {
                val intent = Intent(PictureDetailsActivity.ACTION_DETAILS)
                intent.putExtra(PictureDetailsActivity.EXTRA_IMAGE, image)
                context?.startActivity(intent)
            }

            ImageUtils.loadImage(
                image, holder.imageView, R.drawable.ic_launcher_foreground, R
                    .drawable.ic_launcher_background
            )
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