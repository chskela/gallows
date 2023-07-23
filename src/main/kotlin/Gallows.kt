sealed class Gallows(val view: String) {
    object Default : Gallows(
        """
         ____
         |  |
            |
            |
            |
         ___|___   
    """.trimIndent()
    )

    object Had : Gallows(
        """
         ____
         |  |
         O  |
            |
            |
         ___|___   
    """.trimIndent()
    )

    object Torso : Gallows(
        """
         ____
         |  |
         O  |
         |  |
           |
         ___|___   
    """.trimIndent()
    )

    object LeftHand : Gallows(
        """
         ____
         |  |
         O  |
        /|  |
           |
         ___|___   
    """.trimIndent()
    )

    object RightHand : Gallows(
        """
         ____
         |  |
         O  |
        /|\ |
           |
         ___|___   
    """.trimIndent()
    )

    object LeftLeg : Gallows(
        """
         ____
         |  |
         O  |
        /|\ |
        /   |
         ___|___   
    """.trimIndent()
    )

    object RightLeg : Gallows(
        """
         ____
         |  |
         O  |
        /|\ |
        / \ |
         ___|___   
    """.trimIndent()
    )
}