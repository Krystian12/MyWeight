package krystian.myweight.history

import android.database.Cursor
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

import krystian.myweight.FragmentWeight
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
        historyCursorAdapter = historyCurrsorAdapter
        listView!!.adapter = historyCursorAdapter
    }

    private val historyCurrsorAdapter: HistoryCursorAdapter
        get() = HistoryCursorAdapter(activity, null!!, true)

    override fun getTitle(): String {
        return getString(R.string.tabs_weight_history_title)
    }

}
