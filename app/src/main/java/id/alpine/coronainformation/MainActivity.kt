package id.alpine.coronainformation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pixplicity.easyprefs.library.Prefs
import id.alpine.coronainformation.workmanager.UpdateData
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNav()

        val firstOpen = Prefs.getBoolean("open", true)
        if (firstOpen) {
            workManager()
            Prefs.putBoolean("open", false)
        }
    }

    private fun workManager() {
        val constrain = Constraints.Builder().apply {
            setRequiresDeviceIdle(false)
            setRequiredNetworkType(NetworkType.CONNECTED)
            setRequiresCharging(false)
        }.build()

        val request = PeriodicWorkRequest.Builder(
            UpdateData::class.java,
            60,
            TimeUnit.MINUTES
        ).setConstraints(constrain).build()
        WorkManager.getInstance(this).enqueue(request)
    }


    private fun setupBottomNav() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navGraphIds = listOf(
            R.navigation.nasional,
            R.navigation.daerah,
            R.navigation.bantuan,
            R.navigation.tentang
        )
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_fragment,
            intent = intent
        )
        // Whenever the selected controller changes, setup the action bar.
        controller.observe(this, Observer { navController ->
//            setupActionBarWithNavController(navController)
        })
        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

}
