package krystian.myweight.ui.weight

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Created by Krystian on 2016-01-03.
 */
internal open class WeightFunt : WeightKilogram, Parcelable {
    constructor() : super()

    constructor(grams: Long) : super(grams)

    private val conversionGramsToFunt = 0.0022046228

    override fun getValue(): Double {
        return grams * conversionGramsToFunt
    }

    override fun setValue(value: Double) {
        grams = (Math.round(value / conversionGramsToFunt))
    }

    override fun getWeightValueFormat(): String {
        return String.format(Locale.ENGLISH, "%.1f", getValue())
    }

    override fun getUnit(): Unit {
        return Unit.FUNT
    }

    constructor(source: Parcel) : this(
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {}

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<WeightFunt> = object : Parcelable.Creator<WeightFunt> {
            override fun createFromParcel(source: Parcel): WeightFunt = WeightFunt(source)
            override fun newArray(size: Int): Array<WeightFunt?> = arrayOfNulls(size)
        }
    }
}
