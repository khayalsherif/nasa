@file:Suppress("DEPRECATION")

package az.khayalsharifli.presentation.tools

import android.content.Context
import android.location.Geocoder
import android.location.Location
import az.khayalsharifli.presentation.R
import java.util.*

object Util {
    fun getAddressFromLocation(location: Location, context: Context): String {
        val geocoder = Geocoder(context, Locale.getDefault())
        val address =
            geocoder.getFromLocation(location.latitude, location.longitude, 1) ?: emptyList()
        val fullAddress = StringBuilder()
        if (address.isNotEmpty()) {
            fullAddress.append(address[0]?.countryName ?: "")
        } else {
            fullAddress.append(context.getString(R.string.location_dont_found))
        }
        return fullAddress.toString()
    }
}