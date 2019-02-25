import java.math.BigDecimal

/**
Assume you have access to a function biasedToss() which returns 0 or 1 with a probability that's not 50-50 (but also not 0-100 or 100-0). You do not know the bias of the coin.

Write a function to simulate an unbiased coin toss.
 */

const val BIAS_THREASHOLD = 0.3
const val N_TOSSES = 1000000
private fun biasedToss(): Int = if (Math.random() > BIAS_THREASHOLD) 1 else 0

/**
 In order to create an unbiased toss we need to find a combination of biased tosses that gives as an equal distribution of the results we want:
 If the bias is 60% in favor of ONES, and we make 2 tosses we have the following outcomes:
 |         | 0 (40%)  | 1 (60%) |
 | 0 (40%) | 0,0(16%) | 0,1(24%)|
 | 1 (60%) | 1,0(24%) | 1,1(36%)|

 So, results (1,0) and (0,1) have the same probability.
 In order to get an unbiased coin toss then we take 2 biased tosses at a time, and we discard the two tosses until we have one of the two results (0,1) or (1,0).
 For simplicity, the second toss is "inverted", so that we check for equality of the two tosses (0,0) or (1,1), and just return the result of the first toss.
 */
private fun unbiasedToss(): Int {
    var toss1 = 0
    var toss2 = 1

    while (toss1 != toss2) {
        toss1 = biasedToss()
        toss2 = 1 - biasedToss()
    }
    return toss1
}

private fun checkDistribution(toss: () -> Int) {
    var countOnes = 0
    (0..N_TOSSES).forEach {
        if (toss() == 1) {
            countOnes ++
        }
    }
    val oneDistribution = (countOnes.toBigDecimal().divide(N_TOSSES.toBigDecimal()))
    println("Distribution: 1 ($oneDistribution) - 0 (${BigDecimal.ONE - oneDistribution})")
}

fun main(args: Array<String>) {
    println("Biased toss")
    checkDistribution { biasedToss() }
    println("Unbiased toss")
    checkDistribution { unbiasedToss() }
}