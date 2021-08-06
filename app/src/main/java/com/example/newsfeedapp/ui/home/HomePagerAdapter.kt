package com.example.newsfeedapp.ui.home

import android.content.Context
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.newsfeedapp.R
import com.example.newsfeedapp.ui.technology.TechnologyFragment
import com.example.newsfeedapp.ui.life.LifeFragment
import com.example.newsfeedapp.ui.newsfeed.NewsFeedFragment

class HomePagerAdapter(
    private val ctx: Context,
    fm: FragmentManager
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var currentFragment: Fragment? = null

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        if (currentFragment != `object`) {
            currentFragment = `object` as Fragment
        }
        super.setPrimaryItem(container, position, `object`)
    }

    override fun getItem(position: Int): Fragment {
        return when {
            position == 0 -> NewsFeedFragment()
            position % 2 == 0 -> TechnologyFragment()
            else -> LifeFragment()
        }
    }

    override fun getCount() = 5

    override fun getPageTitle(position: Int): String {
        return when {
            position == 0 -> ctx.getString(R.string.newsfeed)
            position % 2 == 0 -> ctx.getString(R.string.technology)
            else -> ctx.getString(R.string.life)
        }
    }


}
