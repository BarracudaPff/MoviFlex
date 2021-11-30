package com.samsung.moviflex

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun FragmentManager.findFragmentByIdKt(@IdRes id: Int): Fragment {
    return findFragmentById(id) ?: throw IllegalArgumentException("Can't find view with given id")
}