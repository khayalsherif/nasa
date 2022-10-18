package az.khayalsharifli.presentation.tools

import androidx.navigation.NavDirections
import androidx.navigation.Navigator

sealed interface NavigationCommand {
    data class To(val direction: NavDirections, val extras: Navigator.Extras? = null) :
        NavigationCommand

    data class BackTo(val destinationId: Int) : NavigationCommand
    object Back : NavigationCommand
}