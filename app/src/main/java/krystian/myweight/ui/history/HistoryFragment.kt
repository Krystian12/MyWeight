package krystian.myweight.ui.history

import android.database.Cursor
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import krystian.myweight.R
import krystian.myweight.database.AppDatabase
import krystian.myweight.ui.FragmentWeight
import krystian.myweight.ui.dialogs.DialogChangeWeight

/**
 * Created by Krystian on 2015-12-26.
 */
class HistoryFragment : FragmentWeight(), LoaderManager.LoaderCallbacks<Cursor> {

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
        historyCursorAdapter = HistoryCursorAdapter(activity, null, true)
        listView!!.adapter = historyCursorAdapter

        listView!!.setOnItemClickListener { parent, view, position, id ->
            showDialogChangeWeight(position)
        }

        loaderManager.initLoader(0, null, this)
    }

    private fun showDialogChangeWeight(position: Int) {
        val weightItem = historyCursorAdapter!!.getWeightItem(position)
        val dialogChangeWeight = DialogChangeWeight.newInstance(weightItem)
        dialogChangeWeight.show(fragmentManager, "dialogChangeWeight")
    }

    override fun getTitle(): String = getString(R.string.tabs_weight_history_title)

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(activity, AppDatabase.WeightProvider.CONTENT_URI_ALL_ORDER_BY_DATE_WEIGHT,
                AppDatabase.WeightProvider.COLUMNS_ALL,
                null, null, null)
    }

    override fun onLoadFinished(loader: Loader<Cursor>?, data: Cursor?) {
        historyCursorAdapter!!.swapCursor(data)
    }

    override fun onLoaderReset(loader: Loader<Cursor>?) {
        historyCursorAdapter!!.swapCursor(null)
    }

}
