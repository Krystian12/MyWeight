package krystian.myweight.database

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import java.util.*

/**
 * Created by Krystian on 2017-09-20.
 */

@Table(name = WeightItem.TABLE_NAME, database = AppDatabase::class)
class WeightItem : BaseModel() {
    companion object {
        const val TABLE_NAME: String = "WeightItem"
        const val KEY_ID: String = "id"
        const val TIME_ADD: String = "timeAdd"
        const val TIME_CHANGE: String = "timeChange"
        const val TIME_MEASUREMENT: String = "timeMeasurement"
        const val WEIGHT_OF_MILLIGRAM: String = "weightToDisplayInKilograms"
        const val WEIGHT_TO_DISPLAY_IN_KILOGRAMS: String = "weightToDisplayInKilograms"
        const val WEIGHT_TO_DISPLAY_IN_FUNT: String = "weightToDisplayInFunt"
        const val WEIGHT_TO_DISPLAY_IN_STONE: String = "weightToDisplayInStone"
    }

    @PrimaryKey(autoincrement = true)
    @Column(name = KEY_ID)
    var id: Long = 0

    @Column(name = TIME_ADD)
    var timeAdd: Calendar = Calendar.getInstance()

    @Column(name = TIME_CHANGE)
    var timeChange: Calendar = Calendar.getInstance()

    @Column(name = TIME_MEASUREMENT)
    var timeMeasurement: Calendar = Calendar.getInstance()

    @Column(name = WEIGHT_OF_MILLIGRAM)
    var weightOfMilligram: Int = 0

    @Column(name = WEIGHT_TO_DISPLAY_IN_KILOGRAMS)
    var weightToDisplayInKilograms: String = ""

    @Column(name = WEIGHT_TO_DISPLAY_IN_FUNT)
    var weightToDisplayInFunt: String = ""

    @Column(name = WEIGHT_TO_DISPLAY_IN_STONE)
    var weightToDisplayInStone: String = ""

}
