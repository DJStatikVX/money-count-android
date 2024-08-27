package xyz.djstatikvx.moneycount.data.model

import io.kotlintest.shouldBe
import org.junit.Test
import xyz.djstatikvx.moneycount.domain.model.CountOptionValue

class CountOptionEntityTest {

    @Test
    fun `toDomain should return a correct CountOption`() {
        // Given
        val countOptionEntity = CountOptionEntity(
            value = CountOptionValue.TEN,
            selected = true
        )

        // When
        val countOption = countOptionEntity.toDomain()

        // Then
        countOption.multiplier shouldBe countOptionEntity.value
        countOption.selected shouldBe countOptionEntity.selected
        countOption.amount shouldBe 0
    }

}