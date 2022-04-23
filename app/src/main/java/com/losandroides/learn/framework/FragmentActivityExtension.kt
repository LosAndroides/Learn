package com.losandroides.learn.framework

import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.navigateTo(
    @IdRes containerResId: Int,
    fragment: Fragment
) {
    supportFragmentManager.beginTransaction()
        .replace(
            containerResId,
            fragment
        )
        .commit()
}

fun Fragment.toast(
    @StringRes messageResId: Int,
    duration: Int = Toast.LENGTH_SHORT
) {
    Toast.makeText(
        requireContext(),
        messageResId,
        duration
    ).show()
}
