package krystian.myweight.unit

import android.database.Cursor
import krystian.myweight.database.WeightItem

/**
 * Created by Krystian on 2017-09-23.
 */
object WeightManager {
    enum class Unit {
        KILOGRAM, FUNT, STONE
    }

    fun addWeight(weightItem: WeightItem) {

    }

    fun loadEntries(cursor: Cursor): WeightItem {
        return WeightItem()
    }
}