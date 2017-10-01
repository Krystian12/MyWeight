package krystian.myweight.ui.weight

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import krystian.myweight.R


/**
 * Created by Krystian on 2015-12-30.
 */
abstract class Weight {

    var grams: Long = 0

    enum class Unit(val valueStatus: Int, val positionInArryaStringWeightUnit: Int) {
        KILOGRAM(0, 0),
        FUNT(1, 1),
        STONE(2, 2);
    }

    constructor()

    constructor(grams: Long) {
        this.grams = grams
    }

    abstract fun getValue(): Double
    abstract fun setValue(value: Double)
    abstract fun getWeightValueFormat(): String
    abstract fun setWeightValueFormat(weightFormat: String)
    abstract fun getWeightValueWithUnitShort(context: Context): String
    abstract fun getUnit(): Unit

    fun getUnitShort(context: Context): String {
        return Weight.getUnitShort(context, getUnit())
    }


    companion object {

        internal fun getUnitShort(context: Context, unit: Unit): String {
            val position = unit.positionInArryaStringWeightUnit
            return context.resources.getStringArray(R.array.weight_units_short)[position]
        }
    }

    override fun toString(): String {
        return "Weight(grams=$grams)"
    }
}
