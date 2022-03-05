package com.losandroides.learn.presentation.provisional

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.losandroides.learn.R
import com.losandroides.learn.databinding.ActivityProvisionalMainBinding
import com.losandroides.learn.framework.navigateTo
import dagger.hilt.android.AndroidEntryPoint

@Deprecated("This has to be replaced by Jetpack Compose MainActivity")
@AndroidEntryPoint
class ProvisionalMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProvisionalMainBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProvisionalMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigateToMainList()
    }

    private fun navigateToMainList() {
        navigateTo(
            R.id.flContainer,
            ProvisionalMainFragment.newInstance()
        )
    }
}
