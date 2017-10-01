package krystian.myweight.ui.weight

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Krystian on 2016-01-03.
 */
internal class WeightStone : WeightFunt, Parcelable {
    constructor() : super()

    constructor(grams: Long) : super(grams)

    constructor(source: Parcel) : this(
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {}

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<WeightStone> = object : Parcelable.Creator<WeightStone> {
            override fun createFromParcel(source: Parcel): WeightStone = WeightStone(source)
            override fun newArray(size: Int): Array<WeightStone?> = arrayOfNulls(size)
        }
    }
}
