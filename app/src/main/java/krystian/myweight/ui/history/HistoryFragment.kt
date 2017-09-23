package krystian.myweight.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import krystian.myweight.ui.FragmentWeight
import krystian.weightmanagement.R

/**
 * Created by Krystian on 2015-12-26.
 */
class HistoryFragment : FragmentWeight() {

    private var listView: ListView? = null
    private var historyCursorAdapter: HistoryCursorAdapter? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.tabs_weight_history_fragment, container, false)
        listView = view.findViewById(R.id.history_list) as ListView
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListView()
    }

    private fun setupListView() {
        historyCursorAdapter = HistoryCursorAdapter(activity, null!!, true)

        listView!!.adapter = historyCursorAdapter
    }


    override fun getTitle(): String {
        return getString(R.string.tabs_weight_history_title)
    }

}
