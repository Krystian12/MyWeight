package krystian.myweight.database

import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.annotation.provider.ContentUri
import com.raizlabs.android.dbflow.annotation.provider.TableEndpoint
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.structure.BaseModel
import com.raizlabs.android.dbflow.structure.provider.BaseProviderModel
import com.raizlabs.android.dbflow.structure.provider.BaseSyncableProviderModel
import krystian.myweight.ui.weight.Weight
import krystian.myweight.ui.weight.WeightFactory
import krystian.myweight.ui.weight.WeightManager
import java.util.*

/**
 * Created by Krystian on 2017-09-20.
 */

@Table(name = WeightItem.TABLE_NAME, database = AppDatabase::class)
class WeightItem : BaseSyncableProviderModel {

    constructor() : super()

    constructor(cursor: Cursor) : super() {
        loadEntries(cursor)
    }

    companion object {
        private const val TAG = "WeightItem"
        private const val URL_ALL = WeightContentProvider.URL_ALL

        const val TABLE_NAME: String = "WeightItems"
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
    var id: Long = -1

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

    override fun getInsertUri(): Uri = getUti()

    override fun getDeleteUri(): Uri = getUti()

    override fun getQueryUri(): Uri = getUti()

    override fun getUpdateUri(): Uri = getUti()

    private fun getUti(): Uri {
        return Uri.parse("$URL_ALL$id")
    }

    override fun save(): Boolean {
        weightToDisplayInKilograms = WeightFactory.getWeight(Weight.Unit.KILOGRAM, weightOfGram)
                .getWeightValueWithUnitShort(FlowManager.getContext())

        weightToDisplayInFunt = WeightFactory.getWeight(Weight.Unit.FUNT, weightOfGram)
                .getWeightValueWithUnitShort(FlowManager.getContext())

        weightToDisplayInStone = WeightFactory.getWeight(Weight.Unit.STONE, weightOfGram)
                .getWeightValueWithUnitShort(FlowManager.getContext())

        Log.d(TAG, "save")
        return super.save()
    }

    fun loadEntries(cursor: Cursor): WeightItem {
        val weightItem = WeightItem()

        id = cursor.getLong(AppDatabase.WeightProvider.KEY_INT_ID)
        timeAdd = Date(cursor.getLong(AppDatabase.WeightProvider.KEY_INT_TIME_ADD))
        timeChange = Date(cursor.getLong(AppDatabase.WeightProvider.KEY_INT_TIME_CHANGE))
        timeMeasurement = Date(cursor.getLong(AppDatabase.WeightProvider.KEY_INT_TIME_MEASUREMENT))

        weightToDisplayInKilograms = cursor.getString(AppDatabase.WeightProvider.KEY_INT_WEIGHT_TO_DISPLAY_IN_KILOGRAMS)
        weightToDisplayInFunt = cursor.getString(AppDatabase.WeightProvider.KEY_INT_WEIGHT_TO_DISPLAY_IN_FUNT)
        weightToDisplayInStone = cursor.getString(AppDatabase.WeightProvider.KEY_INT_WEIGHT_TO_DISPLAY_IN_STONE)

        Log.d(TAG, "loadEntrie: " + toString())

        return weightItem

    }

    override fun toString(): String =
            "WeightItem(id=$id, " +
                    "timeAdd=$timeAdd, " +
                    "timeChange=$timeChange, " +
                    "timeMeasurement=$timeMeasurement, " +
                    "weightOfGram=$weightOfGram," +
                    " weightToDisplayInKilograms='$weightToDisplayInKilograms'," +
                    " weightToDisplayInFunt='$weightToDisplayInFunt', " +
                    "weightToDisplayInStone='$weightToDisplayInStone', " +
                    "weight=$weight)"
}
