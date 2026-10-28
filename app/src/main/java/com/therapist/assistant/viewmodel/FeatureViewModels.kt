package com.therapist.assistant.viewmodel

import com.therapist.assistant.domain.ClientFilters
import com.therapist.assistant.domain.FinanceAnalytics
import com.therapist.assistant.domain.SmartReminder

/**
 * لایه‌های منطقی جدا — فعلاً از AppointmentViewModel به‌عنوان هاب استفاده می‌شود.
 * این کلاس‌ها مسیر مهاجرت تدریجی به چند ViewModel را باز می‌کنند.
 */
class ClientsLogic {
    val filter: ClientFilters = ClientFilters()
}

class FinanceLogic {
    val analytics: FinanceAnalytics = FinanceAnalytics()
}

class ReminderLogic {
    val smart: SmartReminder = SmartReminder()
}

/** نشانگر نقش منشی برای UI */
object SecretaryGate {
    fun isSecretary(roleName: String?): Boolean = roleName == "SECRETARY"
}
