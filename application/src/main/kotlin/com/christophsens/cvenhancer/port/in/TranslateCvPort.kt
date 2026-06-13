package com.christophsens.cvenhancer.application.port.`in`

import com.christophsens.cvenhancer.model.valueobject.LocaleValueObject

/**
 * Input port for translating a CV.
 */
interface TranslateCvPort {
    fun translate(cvContent: String, locale: LocaleValueObject): String
}