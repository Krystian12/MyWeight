package krystian.myweight.ui.dialogs

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import krystian.myweight.R
import krystian.myweight.database.WeightItem
import krystian.myweight.ui.weight.Weight
import krystian.myweight.ui.weight.WeightFactory
import krystian.myweight.ui.weight.WeightManager
import krystian.myweight.unit.DateFormater
import java.util.*


/**
 * Created by Krystian on 2015-12-30.
 */
class DialogChangeWeight : DialogFragment() {


    private inner class ViewHolder {
        var title: TextView? = null
        var weightValue: EditText? = null
        var weightUnit: Spinner? = null
        var weightDate: TextView? = null
        var buttonDone: TextView? = null
        var buttonCancel: TextView? = null
    }

    private val viewHolder = ViewHolder()

    private var unitAdapter: ArrayAdapter<String>? = null
    private var datePickerDialog: DatePickerDialog? = null

    private var weightItem: WeightItem? = null

    private var keyboardStatus: Boolean = false

    companion object {
        private val KEY_WEIGHT_ITEM: String = "key_weight_item"

        fun newInstance(): DialogChangeWeight {
            return DialogChangeWeight()
        }

        fun newInstance(weightItem: WeightItem): DialogChangeWeight {
            var dialogChangeWeight = DialogChangeWeight()
            var bundle = Bundle()
            bundle.putParcelable(KEY_WEIGHT_ITEM, weightItem)

            dialogChangeWeight.arguments = bundle
            return dialogChangeWeight
        }
    }

    private var date: Date = Date()
        set(date) {
            field = date
            viewHolder.weightDate!!.text = DateFormater.getDateTimeDefault(activity, date)
        }

    private var unit: Weight.Unit = WeightManager.getDefaultUnit()
        set(unit) {
            field = unit
            viewHolder.weightUnit!!.setSelection(unit.positionInArryaStringWeightUnit)
        }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?):
            View = inflater!!.inflate(R.layout.dialog_change_weight, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewHolder.title = view!!.findViewById(R.id.dialog_inside_title) as TextView
        viewHolder.weightValue = view.findViewById(R.id.dialog_inside_edit_weight_value) as EditText
        viewHolder.weightUnit = view.findViewById(R.id.dialog_inside_spinner_weight_units) as Spinner
        viewHolder.weightDate = view.findViewById(R.id.dialog_inside_date) as TextView
        viewHolder.buttonDone = view.findViewById(R.id.dialog_inside_button_done) as TextView
        viewHolder.buttonCancel = view.findViewById(R.id.dialog_inside_button_cancel) as TextView

        if (arguments != null) {
            weightItem = arguments.getParcelable(KEY_WEIGHT_ITEM)
        }

        intButtons()
        intWeightUnit()
        intWeightDate()
        setWeightItem(weightItem)
    }

    private fun intButtons() {
        viewHolder.buttonDone!!.setOnClickListener { done() }
        viewHolder.buttonCancel!!.setOnClickListener { dismiss() }
    }

    private fun intWeightUnit() {
        val arrayStringUnitsWeight = resources.getStringArray(R.array.weight_units_short)
        unitAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_item, arrayStringUnitsWeight)
        unitAdapter!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        viewHolder.weightUnit!!.adapter = unitAdapter

        viewHolder.weightUnit!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val weight = WeightFactory.getWeightShort(activity, unitAdapter!!.getItem(position))
                unit = weight.getUnit()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    private fun intWeightDate() {
        datePickerDialog = DatePickerDialog(activity, onDateSetListener, 0, 0, 0)

        viewHolder.weightDate!!.setOnClickListener { datePickerDialog!!.show() }
        date = Calendar.getInstance().time
    }

    private val onDateSetListener: DatePickerDialog.OnDateSetListener
        get() = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
                val calendar = Calendar.getInstance()
                calendar.time = date
                calendar.set(year, monthOfYear, dayOfMonth)
                date = calendar.time
                datePickerDialog!!.hide()
            }
        }

    private fun setWeightItem(weightItem: WeightItem?) {
        if (weightItem != null) {
            setWeight(weightItem.getWeight())
            date = weightItem.timeMeasurement
        }
    }

    private fun setTitle(title: String) {
        viewHolder.title!!.text = title
    }

    private fun setWeight(weight: Weight) {
        viewHolder.weightValue!!.setText(weight.getWeightValueFormat())
    }

    private fun done() {
        val weight = WeightFactory.getWeight(getUnit())
        weight.setWeightValueFormat(viewHolder.weightValue!!.text.toString())
        if (weightItem == null) {
            weightItem = WeightItem()
        }
        weightItem!!.setWeight(weight)
        weightItem!!.timeChange = Calendar.getInstance().time
        weightItem!!.timeMeasurement = date

        weightItem!!.save()
        dismiss()
    }

    private fun getUnit(): Weight.Unit = unit
}
