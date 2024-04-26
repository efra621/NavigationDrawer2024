package app.gratum.navigationdrawer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    //Elementos q se usaran
    private lateinit var drawerLayout: DrawerLayout //Vista
    private lateinit var toolbar: Toolbar
    private lateinit var navigationView: NavigationView //Menu de item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_id)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar) //Toolbar personalizada

        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        //Permite al usuario cambiar entre 2 estados activado o desactivado
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open_nav,
            R.string.close
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null){
            replaceFragment(FirstFragment())
            navigationView.setCheckedItem(R.id.nav_first_menu)
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_first_menu->replaceFragment(FirstFragment())
            R.id.nav_second_menu ->replaceFragment(SecondFragment())
            R.id.nav_third_menu ->replaceFragment(ThirdFragment())

            else -> Toast.makeText(this, "Hola", Toast.LENGTH_SHORT).show()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    private fun replaceFragment(fragment: Fragment){
        val tranaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        tranaction.replace(R.id.fragment_container,fragment)
        tranaction.commit()
    }


}