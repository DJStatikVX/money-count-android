package xyz.djstatikvx.moneycount.domain.usecase

import xyz.djstatikvx.moneycount.data.repository.CountOptionRepository
import xyz.djstatikvx.moneycount.domain.model.CountOption
import xyz.djstatikvx.moneycount.domain.model.toEntity
import javax.inject.Inject

class UpdateSelectedCountOptionsUseCase @Inject constructor(
    private val countOptionRepository: CountOptionRepository
) {
    suspend operator fun invoke(countOptions: List<CountOption>) {
        countOptionRepository.updateSelectedOptions(
            countOptions.map { it.toEntity() }
        )
    }
}