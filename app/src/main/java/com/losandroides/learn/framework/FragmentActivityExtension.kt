package com.losandroides.learn.framework

import androidx.annotation.IdRes
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
