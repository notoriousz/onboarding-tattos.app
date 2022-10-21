package com.api.v1.onboarding.validation

import com.api.v1.onboarding.service.ArtistService
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class EmailAvailableValidator(var artistService: ArtistService) : ConstraintValidator<EmailAvailable, String>{

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if(value.isNullOrEmpty()) {
            return false
        }

        return artistService.emailAvailable(value)
    }

}
