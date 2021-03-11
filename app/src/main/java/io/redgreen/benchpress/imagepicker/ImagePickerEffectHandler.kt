package io.redgreen.benchpress.imagepicker

import com.spotify.mobius.rx2.RxMobius
import io.reactivex.ObservableTransformer

object ImagePickerEffectHandler {

    fun create (widgetCallback: WidgetCallback): ObservableTransformer<ImageEffect, ImageEvent> {
        return RxMobius.subtypeEffectHandler<ImageEffect, ImageEvent>()
                .addConsumer(
                    ImagePickEffect::class.java,
                        {widgetCallback.openGallery()}
                )
                .build()
    }

}