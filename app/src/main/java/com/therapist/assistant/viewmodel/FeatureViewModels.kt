package com.therapist.assistant.viewmodel

import androidx.lifecycle.ViewModel
import com.therapist.assistant.domain.ClientFilters
import com.therapist.assistant.domain.FinanceAnalytics
import com.therapist.assistant.domain.SmartReminder

/**
 * لایه‌های منطقی جدا — فعلاً از AppointmentViewModel به‌عنوان هاب استفاده می‌شود.
 * این کلاس‌ها مسیر مهاجرت تدریجی به چند ViewModel را باز می‌کنند.
 */
class ClientsLogic {
    fun filter = ClientFilters
}

class FinanceLogic {
    fun analytics = FinanceAnalytics
}

class ReminderLogic {
    fun smart = SmartReminder
}

/** نشانگر نقش منشی برای UI */
object SecretaryGate {
    fun isSecretary(roleName: String?): Boolean = roleName == "SECRETARY"
}
