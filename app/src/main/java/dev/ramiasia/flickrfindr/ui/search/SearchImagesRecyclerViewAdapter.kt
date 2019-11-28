package dev.ramiasia.flickrfindr.ui.search

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.ramiasia.flickrfindr.R
import dev.ramiasia.flickrfindr.data.entity.SearchImage
import dev.ramiasia.flickrfindr.ui.PictureDetailsActivity
import dev.ramiasia.flickrfindr.utils.ImageUtils

/**
 * [RecyclerView.Adapter] class handling the corresponding [RecyclerView]'s rendering of searched
 * [SearchImage] objects.
 */
class SearchImagesRecyclerViewAdapter(private val context: Context?) :
    RecyclerView.Adapter<SearchImagesRecyclerViewAdapter.ImageViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    //When the images have been set, notify the adapter that the data has changed
    var images: List<SearchImage> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    /**
     * Whenever an item is to be retrieved, generate a [ImageViewHolder] with a specified
     * layout.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val item = inflater.inflate(R.layout.image_card, parent, false)
        return ImageViewHolder(item)
    }

    /**
     * On binding the [ImageViewHolder] to an image, set the layout of the holder to the properties
     * of the [SearchImage]
     */
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        if (images.isNotEmpty()) {
            val image = images[position]
            //Limit displayed title to 40 characters. Format the titles of longer titles.
            holder.titleTextView.text =
                if (image.title.length < 40)
                    image.title
                else
                    image.title.substring(0, 37) + "..."

            //Upon clicking on an image, direct the user to a page showing details about the image.
            holder.itemView.setOnClickListener {
                val intent = Intent(PictureDetailsActivity.ACTION_DETAILS)
                intent.putExtra(PictureDetailsActivity.EXTRA_IMAGE, image)
                context?.startActivity(intent)
            }

            //Load the image into the holder.
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

    /**
     * [RecyclerView.ViewHolder] containing the appropriate visual elements used for the
     * [RecyclerView.Adapter].
     */
    class ImageViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val titleTextView: TextView = item.findViewById(R.id.imageTitle)
        val imageView: ImageView = item.findViewById(R.id.flickrImage)
    }
}