sealed class Gallows(val view: String) {
    object Default : Gallows(
        """
             ____
             |  |
                |
                |
                |
             ___|___"""
    )

    object Had : Gallows(
        """
             ____
             |  |
             O  |
                |
                |
             ___|___"""
    )

    object Torso : Gallows(
        """
             ____
             |  |
             O  |
             |  |
               |
             ___|___"""
    )

    object LeftHand : Gallows(
        """
             ____
             |  |
             O  |
            /|  |
               |
             ___|___"""
    )

    object RightHand : Gallows(
        """
             ____
             |  |
             O  |
            /|\ |
               |
             ___|___"""
    )

    object LeftLeg : Gallows(
        """
             ____
             |  |
             O  |
            /|\ |
            /   |
             ___|___"""
    )

    object RightLeg : Gallows(
        """
             ____
             |  |
             O  |
            /|\ |
            / \ |
             ___|___"""
    )
}