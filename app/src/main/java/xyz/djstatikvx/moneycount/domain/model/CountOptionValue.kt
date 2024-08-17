package xyz.djstatikvx.moneycount.domain.model

import java.math.BigDecimal

enum class CountOptionValue(val value: BigDecimal) {
    TEN_THOUSAND(BigDecimal(10000)),
    FIVE_THOUSAND(BigDecimal(5000)),
    TWO_THOUSAND(BigDecimal(2000)),
    ONE_THOUSAND(BigDecimal(1000)),
    FIVE_HUNDRED(BigDecimal(500)),
    TWO_HUNDRED(BigDecimal(200)),
    ONE_HUNDRED(BigDecimal(100)),
    FIFTY(BigDecimal(50)),
    TWENTY(BigDecimal(20)),
    TEN(BigDecimal(10)),
    FIVE(BigDecimal(5)),
    TWO(BigDecimal(2)),
    ONE(BigDecimal(1)),

    ZERO_FIFTY(BigDecimal(0.50)),
    ZERO_TWENTY(BigDecimal(0.20)),
    ZERO_TEN(BigDecimal(0.10)),
    ZERO_ZERO_FIVE(BigDecimal(0.05)),
    ZERO_ZERO_TWO(BigDecimal(0.02)),
    ZERO_ZERO_ONE(BigDecimal(0.01))
}