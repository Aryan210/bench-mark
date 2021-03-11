package io.redgreen.benchpress.imagepicker

class ImagePickerRenderer(
        val view: ImagePickerView
) {
    fun render(model: ImageModel) {
        if (model.imageUri != null)
          view.showImage(model.imageUri)
    }
}
