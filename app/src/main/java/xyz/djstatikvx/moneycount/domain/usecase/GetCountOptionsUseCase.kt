package xyz.djstatikvx.moneycount.domain.usecase

import xyz.djstatikvx.moneycount.data.model.toDomain
import xyz.djstatikvx.moneycount.data.repository.CountOptionRepository
import xyz.djstatikvx.moneycount.domain.model.CountOption
import javax.inject.Inject

class GetCountOptionsUseCase @Inject constructor(
    private val countOptionRepository: CountOptionRepository
) {
    suspend operator fun invoke(): List<CountOption> {
        return countOptionRepository.getSelectedOptions()
            .map { it.toDomain() }
    }
}