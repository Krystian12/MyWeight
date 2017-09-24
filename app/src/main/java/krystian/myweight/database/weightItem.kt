package krystian.myweight.database

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import krystian.myweight.ui.weight.Weight
import krystian.myweight.ui.weight.WeightManager
import java.util.*

/**
 * Created by Krystian on 2017-09-20.
 */

@Table(name = WeightItem.TABLE_NAME, database = AppDatabase::class)
class WeightItem : BaseModel() {
    companion object {
        const val TABLE_NAME: String = "WeightItem"
        const val _ID: String = "_id"
        const val TIME_ADD: String = "TIME_ADD"
        const val TIME_CHANGE: String = "TIME_CHANGE"
        const val TIME_MEASUREMENT: String = "TIME_MEASUREMENT"
        const val WEIGHT_OF_GRAM: String = "WEIGHT_OF_GRAM"
        const val WEIGHT_TO_DISPLAY_IN_KILOGRAMS: String = "WEIGHT_TO_DISPLAY_IN_KILOGRAMS"
        const val WEIGHT_TO_DISPLAY_IN_FUNT: String = "WEIGHT_TO_DISPLAY_IN_FUNT"
        const val WEIGHT_TO_DISPLAY_IN_STONE: String = "WEIGHT_TO_DISPLAY_IN_STONE"
    }

    @PrimaryKey(autoincrement = true)
    @Column(name = _ID)
    var id: Long = 0

    @Column(name = TIME_ADD)
    var timeAdd: Date = Calendar.getInstance().time

    @Column(name = TIME_CHANGE)
    var timeChange: Date = Calendar.getInstance().time

    @Column(name = TIME_MEASUREMENT)
    var timeMeasurement: Date = Calendar.getInstance().time

    @Column(name = WEIGHT_OF_GRAM)
    var weightOfGram: Long = 0

    @Column(name = WEIGHT_TO_DISPLAY_IN_KILOGRAMS)
    var weightToDisplayInKilograms: String = ""

    @Column(name = WEIGHT_TO_DISPLAY_IN_FUNT)
    var weightToDisplayInFunt: String = ""

    @Column(name = WEIGHT_TO_DISPLAY_IN_STONE)
    var weightToDisplayInStone: String = ""

    private var weight: Weight = WeightManager.getDefaultWeight()

    fun getWeightWithUnit(): String {
        return when (weight.getUnit()) {
            Weight.Unit.KILOGRAM -> weightToDisplayInKilograms
            Weight.Unit.FUNT -> weightToDisplayInFunt
            Weight.Unit.STONE -> weightToDisplayInStone
        }
    }

    fun setWeight(weight: Weight) {
        this.weight = weight;
        weightOfGram = weight.grams
    }

    fun getWeight(): Weight {
        return weight
    }
}
