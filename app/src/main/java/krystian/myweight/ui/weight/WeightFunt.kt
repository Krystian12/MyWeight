package krystian.myweight.ui.weight

/**
 * Created by Krystian on 2016-01-03.
 */
internal open class WeightFunt : WeightKilogram() {


    private val conversionGramsToFunt = 0.0022046

    override fun getValue(): Double {
        return grams * conversionGramsToFunt
    }

    override fun setValue(value: Double) {
        grams = (Math.round(value / conversionGramsToFunt))
    }

    override fun getWeightValueFormat(): String {
        return String.format("*.#", getValue())
    }

    override fun getUnit(): Unit {
        return Unit.FUNT
    }
}
