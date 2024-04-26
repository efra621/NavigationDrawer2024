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
import app.gratum.navigationdrawer.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar) //Toolbar personalizada

        binding.navView.setNavigationItemSelectedListener(this) //NavView(Menu de items)

        //Permite al usuario cambiar entre 2 estados activado o desactivado
        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerId,
            binding.toolbar,
            R.string.open_nav,
            R.string.close
        )

        binding.drawerId.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null){
            replaceFragment(FirstFragment())
            binding.navView.setCheckedItem(R.id.nav_first_menu)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_first_menu->replaceFragment(FirstFragment())
            R.id.nav_second_menu ->replaceFragment(SecondFragment())
            R.id.nav_third_menu ->replaceFragment(ThirdFragment())

            else -> Toast.makeText(this, "Hola", Toast.LENGTH_SHORT).show()
        }
        binding.drawerId.closeDrawer(GravityCompat.START)
        return true
    }


    private fun replaceFragment(fragment: Fragment){
        val tranaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        tranaction.replace(R.id.fragment_container,fragment)
        tranaction.commit()
    }
}