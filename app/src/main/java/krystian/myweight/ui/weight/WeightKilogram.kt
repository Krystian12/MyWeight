package krystian.myweight.ui.weight

import android.content.Context
import java.util.*

/**
 * Created by Krystian on 2015-12-30.
 */

internal open class WeightKilogram : Weight {

    constructor() : super()

    constructor(grams: Long) : super(grams)

    override fun setValue(value: Double) {
        grams = Math.round(value * 1000)
    }

    override fun getValue(): Double {
        return grams / 1000.0
    }

    override fun setWeightValueFormat(weightFormat: String) {
        setValue(java.lang.Double.parseDouble(weightFormat))
    }

    override fun getWeightValueFormat(): String {
        return String.format(Locale.ENGLISH, "%.2f", getValue())
    }

    override fun getWeightValueWithUnitShort(context: Context): String {
        return getWeightValueFormat() + " " + getUnitShort(context)
    }

    override fun getUnit(): Unit {
        return Unit.KILOGRAM
    }
}
