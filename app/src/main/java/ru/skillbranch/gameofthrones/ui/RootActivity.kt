package ru.skillbranch.gameofthrones.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import ru.skillbranch.gameofthrones.R
import ru.skillbranch.gameofthrones.databinding.ActivityRootBinding
import ru.skillbranch.gameofthrones.ui.splash.SplashFragmentDirections

class RootActivity : AppCompatActivity() {
    private lateinit var viewModel: RootViewModel
    lateinit var navController: NavController
    private lateinit var binding: ActivityRootBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        binding = ActivityRootBinding.inflate(layoutInflater)
        val rootView = binding.root
        setContentView(rootView)

        initViewModel()
        savedInstanceState ?: prepareDate()
        navController = Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        )
    }

    private fun prepareDate() {
        viewModel.syncDataIfNeed().observe(this, Observer {
            when (it) {
                LoadResult.Loading -> navController.navigate(R.id.nav_splash)
                LoadResult.Success -> {
                    val action = SplashFragmentDirections.actionNavSplashToNavHouses()
                    navController.navigate(action)
                }
                is LoadResult.Error -> {
                    Snackbar.make(
                        binding.rootContainer,
                        it.message,
                        Snackbar.LENGTH_INDEFINITE
                    ).show()
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(RootViewModel::class.java)
    }
}