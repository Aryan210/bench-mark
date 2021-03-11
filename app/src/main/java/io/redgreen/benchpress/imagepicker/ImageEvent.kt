package io.redgreen.benchpress.imagepicker

import android.net.Uri

sealed class ImageEvent

object ImagePickEvent: ImageEvent()

data class SetImageEvent(
    val uri: Uri?
): ImageEvent()
