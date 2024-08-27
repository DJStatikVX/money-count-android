package xyz.djstatikvx.moneycount.domain.usecase

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import xyz.djstatikvx.moneycount.data.model.toDomain
import xyz.djstatikvx.moneycount.data.repository.CountOptionRepository

class GetCountOptionsUseCaseTest {

    @RelaxedMockK
    private lateinit var countOptionRepository: CountOptionRepository

    private lateinit var getCountOptionsUseCase: GetCountOptionsUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getCountOptionsUseCase = GetCountOptionsUseCase(countOptionRepository)
    }

    @Test
    fun `when there are no count options stored in Preferences then return default options`() =
        runBlocking {
            // Given
            val expectedList = CountOptionRepository.DEFAULT_OPTIONS
            coEvery { countOptionRepository.getSelectedOptions() } returns flowOf(expectedList)

            // When
            val response = getCountOptionsUseCase()

            // Then
            val actualList = response.first()
            assert(actualList.size == expectedList.size)
            assert(actualList.all { it.selected })
            for (index in actualList.indices) {
                assert(actualList[index] == expectedList[index].toDomain())
            }
        }

}