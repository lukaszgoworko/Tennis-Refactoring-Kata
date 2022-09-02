class TennisGame1(private val player1Name: String, private val player2Name: String) : TennisGame {

    private var player1Points: Int = 0
    private var player2Points: Int = 0

    override fun wonPoint(playerName: String) {
        if (playerName === "player1")
            player1Points += 1
        else
            player2Points += 1
    }

    override fun getScore(): String =
        when {
            isSameScore           -> convertIfSameScore
            isAdvantageOrWinScore -> calculateOnAdvantageOrWin
            else                  -> calculateStandardScore
        }

    private val calculateStandardScore: String
        get() {
            var score = ""
            var tempScore: Int
            for (i in 1..2) {
                if (i == 1)
                    tempScore = player1Points
                else {
                    score += "-"
                    tempScore = player2Points
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
            val minusResult = player1Points - player2Points

            return if (minusResult == 1)
                "Advantage player1"
            else if (minusResult == -1)
                "Advantage player2"
            else if (minusResult >= 2)
                "Win for player1"
            else
                "Win for player2"
        }

    private val isAdvantageOrWinScore get() = player1Points >= 4 || player2Points >= 4

    private val convertIfSameScore
        get() = when (player1Points) {
            0    -> "Love-All"
            1    -> "Fifteen-All"
            2    -> "Thirty-All"
            else -> "Deuce"
        }

    private val isSameScore get() = player1Points == player2Points
}
