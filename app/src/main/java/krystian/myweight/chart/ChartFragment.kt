package krystian.myweight.chart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import krystian.myweight.FragmentWeight
import krystian.weightmanagement.R

/**
 * Created by Krystian on 2015-12-26.
 */
class ChartFragment : FragmentWeight() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.tabs_weight_chart_fragment, container, false)
    }

    override fun getTitle(): String {
        return getString(R.string.tabs_weight_chart_title)
    }
}
