package krystian.myweight.ui.history

import android.content.Context
import android.database.Cursor
import android.support.v4.widget.CursorAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import krystian.weightmanagement.R

/**
 * Created by Krystian on 2016-01-12.
 */
class HistoryCursorAdapter(private val context: Context, c: Cursor, autoRequery: Boolean) : CursorAdapter(context, c, autoRequery) {


    private inner class ViewHolder {
        var dateDay: TextView? = null
        var dateTime: TextView? = null
        var weight: TextView? = null
        var trend: TextView? = null
    }

    override fun newView(context: Context, cursor: Cursor, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(R.layout.tabs_weight_history_fragment_row, parent, false)
        val viewHolder = ViewHolder()
        viewHolder.dateDay = view.findViewById(R.id.history_row_date) as TextView
        viewHolder.dateTime = view.findViewById(R.id.history_row_time) as TextView
        viewHolder.weight = view.findViewById(R.id.history_row_weight) as TextView
        viewHolder.trend = view.findViewById(R.id.history_row_trend) as TextView
        view.tag = viewHolder

        return view
    }

    override fun bindView(view: View, context: Context, cursor: Cursor) {
        val viewHolder = view.tag as ViewHolder

        //ToDo
        //        EntrieWeight entrieWeight = getEntrieWeight(cursor);
        //
        //        setDate(viewHolder, entrieWeight);
        //        setWeight(viewHolder, entrieWeight);
    }

    //    private EntrieWeight getEntrieWeight(Cursor cursor) {
    //        return WeightManager.loadEntrie(cursor);
    //    }

    //    private void setDate(ViewHolder viewHolder, EntrieWeight entrieWeight) {
    //        viewHolder.dateDay.setText(DateFormater.getDateTimeDefault(context, entrieWeight.getDateWeight()));
    //    }
    //
    //    private void setWeight(ViewHolder viewHolder, EntrieWeight entrieWeight) {
    //        viewHolder.weight.setText(entrieWeight.getWeight().getWeightValuetWithUnitShort(context));
    //    }

}
