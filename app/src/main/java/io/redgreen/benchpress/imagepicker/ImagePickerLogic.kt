package io.redgreen.benchpress.imagepicker

import com.spotify.mobius.Next
import com.spotify.mobius.Next.next
import com.spotify.mobius.Update

object ImagePickerLogic : Update<ImageModel,ImageEvent,ImageEffect> {
    override fun update(model: ImageModel, event: ImageEvent): Next<ImageModel, ImageEffect> {
        return when(event) {
            is ImagePickEvent -> next(model, setOf(ImagePickEffect))
            is SetImageEvent -> next(model.updateUri(event.uri))
            else -> TODO()
        }
    }
}