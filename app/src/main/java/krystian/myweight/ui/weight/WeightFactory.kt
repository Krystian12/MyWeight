package krystian.myweight.ui.weight

import android.content.Context

/**
 * Created by Krystian on 2015-12-30.
 */
object WeightFactory {

    fun getWeight(unit: Weight.Unit): Weight {
        return when (unit) {
            Weight.Unit.KILOGRAM -> WeightKilogram()
            Weight.Unit.FUNT -> WeightFunt()
            Weight.Unit.STONE -> WeightStone()
        }
    }

    fun getWeightShort(context: Context, unitShort: String): Weight {
        return when {
            Weight.getUnitShort(context, Weight.Unit.KILOGRAM).equals(unitShort) -> WeightKilogram()
            Weight.getUnitShort(context, Weight.Unit.FUNT).equals(unitShort) -> WeightFunt()
            Weight.getUnitShort(context, Weight.Unit.STONE).equals(unitShort) -> WeightStone()
            else -> throw IllegalArgumentException("I do not recognize " + unitShort)
        }
    }

    fun getWightValue(valueStatus: Int): Weight {
        return when (valueStatus) {
            Weight.Unit.KILOGRAM.valueStatus -> WeightKilogram()
            Weight.Unit.FUNT.valueStatus -> WeightFunt()
            Weight.Unit.STONE.valueStatus -> WeightStone()
            else -> throw IllegalArgumentException("do not recognize valueStatus " + valueStatus)
        }
    }

    fun getUnitValue(valueStatus: Int): Weight.Unit {
        return when (valueStatus) {
            Weight.Unit.KILOGRAM.valueStatus -> Weight.Unit.KILOGRAM
            Weight.Unit.FUNT.valueStatus -> Weight.Unit.FUNT
            Weight.Unit.STONE.valueStatus -> Weight.Unit.STONE
            else -> throw IllegalArgumentException("do not recognize valueStatus " + valueStatus)
        }
    }
}
