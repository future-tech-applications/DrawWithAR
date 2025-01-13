package com.example.drawwithar.util

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.example.drawwithar.feature.savedrawingpage.folderName

object MediaStoreUtil {

    // get saved images list from storage
    fun fetchSavedImages(context: Context): List<Uri> {
        Log.d("MediaStoreUtil", "Fetching saved images...")
        val imagesList = mutableListOf<Uri>()
        val projection = arrayOf(MediaStore.Images.Media._ID)
        val selection = "${MediaStore.Images.Media.RELATIVE_PATH} LIKE ?"
        val selectionArgs = arrayOf("Pictures/$folderName/")

        val queryUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        Log.d("MediaStoreUtil", "Query URI: $queryUri")

        Log.d("MediaStoreUtil", "Querying with path: ${selectionArgs[0]}")

        context.contentResolver.query(
            queryUri, projection, selection, selectionArgs, null
        )?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val uri = Uri.withAppendedPath(queryUri, id.toString())
                imagesList.add(uri)
            }
        }
        Log.d("MediaStoreUtil", "Fetched ${imagesList.size} images")
        return imagesList
    }

}