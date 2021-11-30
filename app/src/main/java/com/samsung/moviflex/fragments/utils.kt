package com.samsung.moviflex.fragments

import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun expand(view: View) {
    val animation = expandAction(view)
    view.startAnimation(animation)
}

private fun expandAction(view: View): Animation {
    view.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    val actualHeight = view.measuredHeight
    view.layoutParams.height = 0
    view.visibility = View.VISIBLE
    val animation: Animation = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            view.layoutParams.height =
                if (interpolatedTime == 1f) ViewGroup.LayoutParams.WRAP_CONTENT else (actualHeight * interpolatedTime).toInt()
            view.requestLayout()
        }
    }
    animation.duration = (actualHeight / view.context.resources.displayMetrics.density).toLong()
    view.startAnimation(animation)
    return animation
}

fun collapse(view: View) {
    val actualHeight = view.measuredHeight
    val animation: Animation = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
            if (interpolatedTime == 1f) {
                view.visibility = View.GONE
            } else {
                view.layoutParams.height = actualHeight - (actualHeight * interpolatedTime).toInt()
                view.requestLayout()
            }
        }
    }
    animation.duration = (actualHeight / view.context.resources.displayMetrics.density).toLong()
    view.startAnimation(animation)
}
