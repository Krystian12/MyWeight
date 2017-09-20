package krystian.myweight

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import krystian.myweight.chart.ChartFragment
import krystian.myweight.history.HistoryFragment
import krystian.myweight.info.InfoFragment


/**
 * Created by Krystian on 2015-12-26.
 */
class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val tabsFragments = arrayOf(InfoFragment(), HistoryFragment(), ChartFragment())
    private val tabTitles = arrayOf("Tab1", "Tab2", "Tab3")

    override fun getItem(position: Int): FragmentWeight {
        return tabsFragments[position]
    }

    override fun getCount(): Int {
        return tabsFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabTitles[position]
    }
}
