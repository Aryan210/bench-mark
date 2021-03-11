package io.redgreen.benchpress.imagepicker

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import com.spotify.mobius.Next
import io.reactivex.ObservableTransformer
import io.redgreen.benchpress.R
import io.redgreen.benchpress.architecture.android.BaseActivity
import kotlinx.android.synthetic.main.image_picker_activity.*


class ImagePickerActivity : BaseActivity<ImageModel, ImageEvent, ImageEffect>(), ImagePickerView,WidgetCallback {
  companion object {
    const val PICK_IMAGE = 100
    fun start(context: Context) {
      context.startActivity(Intent(context, ImagePickerActivity::class.java))
    }
  }

  override fun layoutResId() = R.layout.image_picker_activity

  private val renderer by lazy {
    ImagePickerRenderer(this)
  }

  override fun setup() {
    button.setOnClickListener { eventSource.notifyEvent(ImagePickEvent) }
  }

  override fun initialModel() = ImageModel.getInitialModel()

  override fun updateFunction(model: ImageModel, event: ImageEvent): Next<ImageModel, ImageEffect> {
    return ImagePickerLogic.update(model, event)
  }

  override fun render(model: ImageModel) {
    renderer.render(model)
  }

  override fun effectHandler(): ObservableTransformer<ImageEffect, ImageEvent> {
    return ImagePickerEffectHandler.create(this)
  }

  override fun showImage(uri: Uri) {
    image.setImageURI(uri)
  }

  override fun openGallery() {
    val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
    startActivityForResult(gallery, PICK_IMAGE)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
      eventSource.notifyEvent(SetImageEvent(data?.data))
    }
  }
}
