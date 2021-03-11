package io.redgreen.benchpress.imagepicker

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageModel(
        val imageUri: Uri?
): Parcelable {
    companion object {
        fun getInitialModel() = ImageModel(imageUri = null)
    }

    fun updateUri(uri: Uri?) : ImageModel = copy(imageUri = uri)
}
