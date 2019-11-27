package dev.ramiasia.bleacherflickort.network

import dev.ramiasia.bleacherflickort.data.entity.ImageQuery
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface used by Retrofit for REST methods.
 */
interface ImageDataInterface {

    /**
     * Queries Flickr API for list of images based on a given search term.
     */
    @GET("rest/")
    suspend fun getImageList(
        @Query("api_key") apiKey: String,
        @Query("per_page") itemCount: Int,
        @Query("text") term: String,
        @Query("method") method: String = "flickr.photos.search",
        @Query("privacy_filter") privacy: Int = 1,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") callback: Int = 1,
        @Query("page") page: Int
    ): ImageQuery
}
