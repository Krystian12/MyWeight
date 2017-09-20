package krystian.myweight.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView

import krystian.myweight.FragmentWeight
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

    private val animFadein: Animation? = null
    private val animFadeout: Animation? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.tabs_weight_info_fragment, container, false)
        viewHolderLastEntry.buttonAdd = view.findViewById(R.id.info_button_add) as TextView
        viewHolderLastEntry.weight = view.findViewById(R.id.info_last_entry_weight) as TextView
        viewHolderLastEntry.date = view.findViewById(R.id.info_last_entry_date) as TextView

        return view
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        viewHolderLastEntry.buttonAdd!!.setOnClickListener(onClickListenerButtonAdd)
        setupWeight()

        super.onViewCreated(view, savedInstanceState)
    }

    fun setupWeight() {
        setLastWeight()
    }

    fun setLastWeight() {
        //Todo
        //        EntrieWeight entrieWeight = WeightManager.getInstance().getLastWeight();
        //
        //        if(entrieWeight == null) {
        //            viewHolderLastEntry.weight.setText("---");
        //            viewHolderLastEntry.date.setText("---");
        //        }else {
        //            viewHolderLastEntry.weight.setText(entrieWeight.getWeight().getWeightValuetWithUnitShort(getActivity()));
        //            viewHolderLastEntry.date.setText(DateFormater.getDateTimeDefault(getActivity(), entrieWeight.getDateWeight()));
        //        }
    }

    override fun getTitle(): String {
        return getString(R.string.tabs_weight_info_title)
    }

    private val onClickListenerButtonAdd: View.OnClickListener
        get() = View.OnClickListener { showDialogAddWeight() }

    private fun showDialogAddWeight() {
        //Todo
    }
}
