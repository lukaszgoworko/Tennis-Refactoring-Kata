class TennisGame1(private val player1Name: String, private val player2Name: String) : TennisGame {

    private var m_score1: Int = 0
    private var m_score2: Int = 0

    override fun wonPoint(playerName: String) {
        if (playerName === "player1")
            m_score1 += 1
        else
            m_score2 += 1
    }

    override fun getScore(): String {
        var score = ""
        if (isSameScore) {
            score = convertIfSameScore
        } else if (isAdvantageOrWinScore) {
            score = calculateOnAdvantageOrWin
        } else {
            score = calculateStandardScore(score)
        }
        return score
    }

    private fun calculateStandardScore(score: String): String {
        var score1 = score
        var tempScore: Int
        for (i in 1..2) {
            if (i == 1)
                tempScore = m_score1
            else {
                score1 += "-"
                tempScore = m_score2
            }
            when (tempScore) {
                0 -> score1 += "Love"
                1 -> score1 += "Fifteen"
                2 -> score1 += "Thirty"
                3 -> score1 += "Forty"
            }
        }
        return score1
    }

    private val calculateOnAdvantageOrWin: String
        get() {
            val minusResult = m_score1 - m_score2

            return if (minusResult == 1)
                "Advantage player1"
            else if (minusResult == -1)
                "Advantage player2"
            else if (minusResult >= 2)
                "Win for player1"
            else
                "Win for player2"
        }

    private val isAdvantageOrWinScore get() = m_score1 >= 4 || m_score2 >= 4

    private val convertIfSameScore
        get() = when (m_score1) {
            0    -> "Love-All"
            1    -> "Fifteen-All"
            2    -> "Thirty-All"
            else -> "Deuce"
        }

    private val isSameScore get() = m_score1 == m_score2
}
