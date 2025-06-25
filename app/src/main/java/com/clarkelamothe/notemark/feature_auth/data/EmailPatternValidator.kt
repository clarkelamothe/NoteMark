package com.clarkelamothe.notemark.feature_auth.data

import android.util.Patterns
import com.clarkelamothe.notemark.feature_auth.domain.PatternValidator

object EmailPatternValidator : PatternValidator {
    override fun matches(value: String) = Patterns.EMAIL_ADDRESS.matcher(value).matches()
}
