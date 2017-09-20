package krystian.myweight

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem

import com.astuetz.PagerSlidingTabStrip

import krystian.weightmanagement.R


class TabsWeightActivity : AppCompatActivity() {

    private var pagerAdapter: PagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tabs_weight_activity)

        val myToolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(myToolbar)
        setupToolbar()

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        val viewPager = findViewById(R.id.viewpager) as ViewPager
        pagerAdapter = PagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter


        // Give the PagerSlidingTabStrip the ViewPager
        val tabsStrip = findViewById(R.id.tabs) as PagerSlidingTabStrip
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager)
    }

    private fun setupToolbar() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_tabs_weight, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return super.onOptionsItemSelected(item)
    }
}
