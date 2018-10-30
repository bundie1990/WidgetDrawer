package tk.zwander.widgetdrawer.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.provider.Settings
import android.util.TypedValue
import android.view.WindowManager


val Context.canDrawOverlays
    get() = Build.VERSION.SDK_INT < Build.VERSION_CODES.M
            || Settings.canDrawOverlays(this)

fun Context.dpAsPx(dpVal: Int) =
    dpAsPx(dpVal.toFloat())

fun Context.dpAsPx(dpVal: Float) =
    Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, resources.displayMetrics))

fun Context.pxAsDp(pxVal: Int) =
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pxVal.toFloat(), resources.displayMetrics)

fun Context.screenSize(): Point {
    val display = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
    return Point().apply { display.getRealSize(this) }
}

fun Context.statusBarHeight(): Int {
    val id = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android")
    return Resources.getSystem().getDimensionPixelSize(id)
}

fun Context.actionBarHeight(): Int {
    val value = TypedValue().apply { theme.resolveAttribute(android.R.attr.actionBarSize, this, true) }
    return TypedValue.complexToDimensionPixelSize(value.data, resources.displayMetrics)
}