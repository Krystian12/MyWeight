package krystian.myweight.ui.info

import android.database.ContentObserver
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import krystian.myweight.R
import krystian.myweight.database.AppDatabase
import krystian.myweight.ui.FragmentWeight
import krystian.myweight.ui.dialogs.DialogChangeWeight
import krystian.myweight.ui.weight.WeightManager
import krystian.myweight.unit.DateFormater


/**
 * Created by Krystian on 2015-12-26.
 */
class InfoFragment : FragmentWeight() {

    internal inner class ViewHolderLastEntry {
        var buttonAdd: TextView? = null
        var weight: TextView? = null
        var date: TextView? = null
    }

    private val viewHolderLastEntry = ViewHolderLastEntry()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.tabs_weight_info_fragment, container, false)
        viewHolderLastEntry.buttonAdd = view.findViewById(R.id.info_button_add) as TextView
        viewHolderLastEntry.weight = view.findViewById(R.id.info_last_entry_weight) as TextView
        viewHolderLastEntry.date = view.findViewById(R.id.info_last_entry_date) as TextView

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewHolderLastEntry.buttonAdd!!.setOnClickListener(onClickListenerButtonAdd)
        setupWeight()

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        context.contentResolver.registerContentObserver(
                AppDatabase.WeightProvider.CONTENT_URI_ALL_ORDER_BY_DATE_WEIGHT,
                true, contentObserver
        )
    }

    override fun onStop() {
        super.onStop()
        context.contentResolver.unregisterContentObserver(contentObserver)
    }

    var contentObserver = object : ContentObserver(Handler()) {
        override fun onChange(selfChange: Boolean) {
            setLastWeight()
        }

        override fun onChange(selfChange: Boolean, uri: Uri?) {
            setLastWeight()
        }
    }

    fun setupWeight() {
        setLastWeight()
    }

    private fun setLastWeight() {
        val weightItem = WeightManager.getLastWeight(context)

        if (weightItem == null) {
            viewHolderLastEntry.weight!!.text = "---"
            viewHolderLastEntry.date!!.text = "---"
        } else {
            viewHolderLastEntry.weight!!.text = weightItem.getWeightWithUnit()
            viewHolderLastEntry.date!!.text = DateFormater.getDateTimeDefault(getActivity(), weightItem!!.timeMeasurement)
        }
    }

    override fun getTitle(): String = getString(R.string.tabs_weight_info_title)

    private val onClickListenerButtonAdd: View.OnClickListener
        get() = View.OnClickListener { showDialogAddWeight() }

    private fun showDialogAddWeight() {
        val dialogChangeWeight = DialogChangeWeight.newInstance()
        dialogChangeWeight.show(fragmentManager, "dialogChangeWeight")
    }

}
