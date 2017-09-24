package krystian.myweight.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import krystian.myweight.ui.FragmentWeight
import krystian.myweight.ui.dialogs.DialogChangeWeight
import krystian.myweight.ui.weight.WeightManager
import krystian.myweight.unit.DateFormater
import krystian.weightmanagement.R


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

    fun setupWeight() {
        setLastWeight()
    }

    private fun setLastWeight() {
        val weightItem = WeightManager.getLastWeight(context)

        if (weightItem == null) {
            viewHolderLastEntry.weight!!.text = "---"
            viewHolderLastEntry.date!!.text = "---"
        } else {
            viewHolderLastEntry.weight!!.text = weightItem!!.getWeight().getWeightValueWithUnitShort(getActivity())
            viewHolderLastEntry.date!!.text = DateFormater.getDateTimeDefault(getActivity(), weightItem!!.timeMeasurement)
        }
    }

    override fun getTitle(): String = getString(R.string.tabs_weight_info_title)

    private val onClickListenerButtonAdd: View.OnClickListener
        get() = View.OnClickListener { showDialogAddWeight() }

    private fun showDialogAddWeight() {
        val dialogChangeWeight = DialogChangeWeight()
        dialogChangeWeight.show(fragmentManager, "dialogChangeWeight")
    }

}
