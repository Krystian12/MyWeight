package krystian.myweight.ui.weight

import java.util.*

/**
 * Created by Krystian on 2016-01-03.
 */
internal open class WeightFunt : WeightKilogram {

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
}
