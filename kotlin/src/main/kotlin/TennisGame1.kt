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
        return if (isSameScore) {
            convertIfSameScore
        } else if (isAdvantageOrWinScore) {
            calculateOnAdvantageOrWin
        } else {
            calculateStandardScore()
        }
    }

    private fun calculateStandardScore(): String {
        var score = ""
        var tempScore: Int
        for (i in 1..2) {
            if (i == 1)
                tempScore = m_score1
            else {
                score += "-"
                tempScore = m_score2
            }
            when (tempScore) {
                0 -> score += "Love"
                1 -> score += "Fifteen"
                2 -> score += "Thirty"
                3 -> score += "Forty"
            }
        }
        return score
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
