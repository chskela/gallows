package views

enum class Gallows(val view: String) {
    Default(
        """
             ____
             |  |
                |
                |
                |
             ___|___"""
    ),

    Had(
        """
             ____
             |  |
             O  |
                |
                |
             ___|___"""
    ),

    Torso(
        """
             ____
             |  |
             O  |
             |  |
                |
             ___|___"""
    ),

    LeftHand(
        """
             ____
             |  |
             O  |
            /|  |
                |
             ___|___"""
    ),

    RightHand(
        """
             ____
             |  |
             O  |
            /|\ |
                |
             ___|___"""
    ),

    LeftLeg(
        """
             ____
             |  |
             O  |
            /|\ |
            /   |
             ___|___"""
    ),

    RightLeg(
        """
             ____
             |  |
             O  |
            /|\ |
            / \ |
             ___|___"""
    )
}