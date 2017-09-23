package krystian.myweight.database

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import java.util.*

/**
 * Created by Krystian on 2017-09-20.
 */

@Table(name = "WeightItem", database = AppDatabase::class)
class WeightItem : BaseModel() {

    @PrimaryKey(autoincrement = true)
    @Column(name = "id")
    var id: Long = 0

    @Column(name = "timeAdd")
    var timeAdd: Calendar = Calendar.getInstance()

    @Column(name = "timeChange")
    var timeChange: Calendar = Calendar.getInstance()

    @Column(name = "timeMeasurement")
    var timeMeasurement: Calendar = Calendar.getInstance()

    @Column(name = "weightOfMilligram")
    var weightOfMilligram: Int = 0

    @Column(name = "weightToDisplayInKilograms")
    var weightToDisplayInKilograms: String = ""

    @Column(name = "weightToDisplayInFunt")
    var weightToDisplayInFunt: String = ""

    @Column(name = "weightToDisplayInStone")
    var weightToDisplayInStone: String = ""

}
