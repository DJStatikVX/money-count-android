package xyz.djstatikvx.moneycount.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import xyz.djstatikvx.moneycount.data.model.toDomain
import xyz.djstatikvx.moneycount.data.repository.CountOptionRepository
import xyz.djstatikvx.moneycount.domain.model.CountOption
import javax.inject.Inject

class GetCountOptionsUseCase @Inject constructor(
    private val countOptionRepository: CountOptionRepository
) {
    operator fun invoke(): Flow<List<CountOption>> {
        return countOptionRepository.getSelectedOptions()
            .map { flowList ->
                flowList
                    .map { item -> item.toDomain() }
            }
    }
}