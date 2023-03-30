package com.ch96.tpcafenity.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ch96.tpcafenity.R
import com.ch96.tpcafenity.databinding.ActivityMainBinding
import com.ch96.tpcafenity.fragments.AccountFragment
import com.ch96.tpcafenity.fragments.CommunityFragment
import com.ch96.tpcafenity.fragments.HomeFragment
import com.ch96.tpcafenity.fragments.InterestsFragment

private const val TAG_HOME = "home_fragment"
private const val TAG_INTERESTS = "interests_fragment"
private const val TAG_COMMUNITY = "community_fragment"
private const val TAG_ACCOUNT = "account_fragment"

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragment(TAG_HOME, HomeFragment())

        binding.navigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.homeFragment -> setFragment(TAG_HOME, HomeFragment())
                R.id.interestsFragment -> setFragment(TAG_INTERESTS, InterestsFragment())
                R.id.communityFragment -> setFragment(TAG_COMMUNITY, CommunityFragment())
                R.id.accountFragment -> setFragment(TAG_ACCOUNT, AccountFragment())
            }
            true
        }

    }

    private fun setFragment(tag:String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        if(manager.findFragmentByTag(tag) == null) {
            fragTransaction.add(R.id.mainFrameLayout, fragment, tag)
        }

        val home = manager.findFragmentByTag(TAG_HOME)
        val intersts = manager.findFragmentByTag(TAG_INTERESTS)
        val community = manager.findFragmentByTag(TAG_COMMUNITY)
        val account = manager.findFragmentByTag(TAG_ACCOUNT)

        if (home != null) {
            fragTransaction.hide(home)
        }
        if (intersts != null) {
            fragTransaction.hide(intersts)
        }
        if (community != null) {
            fragTransaction.hide(community)
        }
        if (account != null) {
            fragTransaction.hide(account)
        }

        if (tag == TAG_HOME) {
            if (home != null) {
                fragTransaction.show(home)
            }
        } else if (tag == TAG_INTERESTS) {
            if (intersts != null) {
                fragTransaction.show(intersts)
            }
        } else if (tag == TAG_COMMUNITY) {
            if (community != null) {
                fragTransaction.show(community)
            }
        } else if (tag == TAG_ACCOUNT) {
            if (account != null) {
                fragTransaction.show(account)
            }
        }

        fragTransaction.commitAllowingStateLoss()
    }
}