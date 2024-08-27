package xyz.djstatikvx.moneycount.domain.model

import io.kotlintest.shouldBe
import org.junit.Test

class CountOptionTest {

    @Test
    fun `toEntity() should return a correct CountOptionEntity`() {
        // Given
        val countOption = CountOption(
            multiplier = CountOptionValue.TEN
        )

        // When
        val countOptionEntity = countOption.toEntity()

        // Then
        countOptionEntity.value shouldBe countOption.multiplier
        countOptionEntity.selected shouldBe countOption.selected
    }

}