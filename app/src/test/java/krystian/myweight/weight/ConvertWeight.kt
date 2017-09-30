package krystian.myweight.weight

import krystian.myweight.ui.weight.Weight
import krystian.myweight.ui.weight.WeightFactory
import org.junit.Assert
import org.junit.Test

/**
 * Created by Krystian on 2017-09-26.
 */

class ConvertWeight {

    @Test
    fun checkConvertWeightValue() {
        Assert.assertEquals(1.0, WeightFactory.getWeight(Weight.Unit.KILOGRAM, 1000).getValue(), 0.0)
        Assert.assertEquals(2.0, WeightFactory.getWeight(Weight.Unit.KILOGRAM, 2000).getValue(), 0.0)

        Assert.assertEquals(2.2046228, WeightFactory.getWeight(Weight.Unit.FUNT, 1000).getValue(), 0.0)
        Assert.assertEquals(4.4092456, WeightFactory.getWeight(Weight.Unit.FUNT, 2000).getValue(), 0.0)

//        Assert.assertEquals(-1.0, WeightFactory.getWeight(Weight.Unit.STONE, 1000).getValue(), 0.0)
//        Assert.assertEquals(-1.0, WeightFactory.getWeight(Weight.Unit.STONE, 2000).getValue(), 0.0)
    }

    @Test
    fun checkConvertWeightValueFormat() {

        Assert.assertEquals("1.68", WeightFactory.getWeight(Weight.Unit.KILOGRAM, 1677).getWeightValueFormat())
        Assert.assertEquals("2.00", WeightFactory.getWeight(Weight.Unit.KILOGRAM, 1996).getWeightValueFormat())

        Assert.assertEquals("3.7", WeightFactory.getWeight(Weight.Unit.FUNT, 1677).getWeightValueFormat())
        Assert.assertEquals("4.4", WeightFactory.getWeight(Weight.Unit.FUNT, 2000).getWeightValueFormat())

//        Assert.assertEquals(-1.0, WeightFactory.getWeight(Weight.Unit.STONE, 1677).getWeightValueFormat(), 0.0)
//        Assert.assertEquals(-1.0, WeightFactory.getWeight(Weight.Unit.STONE, 2000).getWeightValueFormat(), 0.0)
    }

    @Test
    fun checkSetWeightValueFormat() {
        val kilogram = WeightFactory.getWeight(Weight.Unit.KILOGRAM)

        kilogram.setWeightValueFormat("1.68")
        Assert.assertEquals(1680, kilogram.grams)

        kilogram.setWeightValueFormat("2.00")
        Assert.assertEquals(2000, kilogram.grams)

        val funt = WeightFactory.getWeight(Weight.Unit.FUNT)
        funt.setWeightValueFormat("3.7")
        Assert.assertEquals(1678, funt.grams)

        funt.setWeightValueFormat("4.4")
        Assert.assertEquals(1996, funt.grams)
    }
}
