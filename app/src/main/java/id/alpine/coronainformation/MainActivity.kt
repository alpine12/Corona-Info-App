package id.alpine.coronainformation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById(R.id.bottomNavigationView) as BottomNavigationView

        val nav_controler = findNavController(R.id.nav_host_fragment)

        val bottomNavConfig = AppBarConfiguration(
            setOf(
                R.id.menu_kasus, R.id.menu_informasi, R.id.menu_bantuan, R.id.menu_about
            )
        )
//       setupActionBarWithNavController(nav_controler, bottomNavConfig)
        bottomNav.setupWithNavController(nav_controler)
    }
}
