package com.flutter.stripe

import android.content.Context
import android.view.View
import com.facebook.react.uimanager.ThemedReactContext
import com.reactnativestripesdk.GooglePayButtonManager
import com.reactnativestripesdk.GooglePayButtonView
import com.reactnativestripesdk.StripeSdkModule
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.platform.PlatformView


class StripeSdkGooglePayButtonPlatformView(
        context: Context,
        channel: MethodChannel,
        id: Int,
        creationParams: Map<String?, Any?>?,
        private val googlePayButtonManager: GooglePayButtonManager,
        sdkAccessor: () -> StripeSdkModule
) : PlatformView {

    private val payButton: GooglePayButtonView = googlePayButtonManager.createViewInstance(ThemedReactContext(context, channel, sdkAccessor))

    init {
        if (creationParams?.containsKey("buttonType") == true) {
            googlePayButtonManager.buttonType(payButton, creationParams["buttonType"] as String)
        }
    }

    override fun getView(): View {
        return payButton
    }

    override fun dispose() {

    }

    override fun onFlutterViewAttached(flutterView: View) {
        googlePayButtonManager.onAfterUpdateTransaction(payButton)
    }
}