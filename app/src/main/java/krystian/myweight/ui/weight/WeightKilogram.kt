package krystian.myweight.ui.weight

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Created by Krystian on 2015-12-30.
 */

internal open class WeightKilogram : Weight, Parcelable {
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

    constructor(source: Parcel) : this(
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {}

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<WeightKilogram> = object : Parcelable.Creator<WeightKilogram> {
            override fun createFromParcel(source: Parcel): WeightKilogram = WeightKilogram(source)
            override fun newArray(size: Int): Array<WeightKilogram?> = arrayOfNulls(size)
        }
    }
}
