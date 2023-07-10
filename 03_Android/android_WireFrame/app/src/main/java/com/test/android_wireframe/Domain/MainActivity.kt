package com.test.android_wireframe.Domain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.test.android_wireframe.Domain.login.CreateAccountFragment
import com.test.android_wireframe.Domain.login.LoginFragment
import com.test.android_wireframe.Domain.login.VerificationFragment
import com.test.android_wireframe.Domain.main.MainFragment
import com.test.android_wireframe.Domain.main.category.CategoryFragment
import com.test.android_wireframe.Domain.main.category.checkout.CartFragment
import com.test.android_wireframe.Domain.main.category.checkout.CheckoutFragment
import com.test.android_wireframe.Domain.main.category.checkout.DeliveryFragment
import com.test.android_wireframe.Domain.main.category.checkout.OrderConfirmedFragment
import com.test.android_wireframe.Domain.main.deals.DealsFragment
import com.test.android_wireframe.Domain.splash.SplashFragment
import com.test.android_wireframe.R
import com.test.android_wireframe.databinding.ActivityMainBinding
import javax.security.auth.login.LoginException

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // Fragment 분기
    companion object {
        // splash
        val SPLASH = "SplashFragment"

        // login
        val CREATE_ACCOUNT = "CreateAccountFragment"
        val LOGIN = "LoginFragment"
        val VERIFICATION = "VerificationFragment"

        // main
        val MAIN = "MainFragment"

        // deals
        val DEALS = "DealsFragment"

        // category
        val CATEGORY = "CategoryFragment"

        // checkout
        val CART = "CartFragment"
        val CHECKOUT = "CheckoutFragment"
        val DELIVERY = "DeliveryFragment"
        val ORDER_CONFIRMED = "OrderConfirmedFragment"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            replaceFragment(SPLASH, false, false)
        }
    }

    fun replaceFragment(name: String, addToBackStack: Boolean, animate: Boolean) {
        val fragmentTrasaction = supportFragmentManager.beginTransaction()
        var newFragment = when (name) {

            // splash
            SPLASH -> {
                SplashFragment()
            }

            // login
            CREATE_ACCOUNT -> {
                CreateAccountFragment()
            }

            LOGIN -> {
                LoginFragment()
            }

            VERIFICATION -> {
                VerificationFragment()
            }

            // main
            MAIN -> {
                MainFragment()
            }

            // deals
            DEALS -> {
                DealsFragment()
            }

            // category
            CATEGORY -> {
                CategoryFragment()
            }

            // checkout
            CART -> {
                CartFragment()
            }

            CHECKOUT -> {
                CheckoutFragment()
            }

            DELIVERY -> {
                DeliveryFragment()
            }

            ORDER_CONFIRMED -> {
                OrderConfirmedFragment()
            }

            else -> {
                Fragment()
            }
        }

        run {
            fragmentTrasaction.replace(R.id.fcv_main, newFragment)
            if (animate) fragmentTrasaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            if (addToBackStack) fragmentTrasaction.addToBackStack(name)
            fragmentTrasaction.commit()
        }
    }

    fun removeFragment(name: String) {
        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}