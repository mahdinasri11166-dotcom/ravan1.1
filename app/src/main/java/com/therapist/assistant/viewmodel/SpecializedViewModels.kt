package com.therapist.assistant.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.therapist.assistant.data.AppDatabase
import com.therapist.assistant.data.AppointmentRepository
import com.therapist.assistant.data.SettingsRepository
import com.therapist.assistant.data.UserRole
import com.therapist.assistant.domain.ClientFilters
import com.therapist.assistant.domain.AdvancedClientFilter
import com.therapist.assistant.domain.FinanceAnalytics
import com.therapist.assistant.domain.SmartReminder
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModelهای تخصصی واقعی — قابل استفاده جدا از AppointmentViewModel.
 * پایگاه‌داده و تنظیمات مشترک است.
 */
class ClientsViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    private val repo = AppointmentRepository(db.appointmentDao(), db.clientDao(), db.questionnaireDao())
    private val settings = SettingsRepository(application)

    val clients = repo.getAllClients().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val appointments = repo.getAllAppointments().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun filtered(query: String, filter: AdvancedClientFilter) =
        ClientFilters.apply(clients.value, appointments.value, filter, query)

    fun archiveClient(id: Long, archived: Boolean) = viewModelScope.launch {
        val c = repo.getClientById(id) ?: return@launch
        repo.updateClient(c.copy(isArchived = archived))
    }
}

class FinanceViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    private val repo = AppointmentRepository(db.appointmentDao(), db.clientDao(), db.questionnaireDao())
    val appointments = repo.getAllAppointments().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun unpaidTotal() = FinanceAnalytics.unpaidTotal(appointments.value)
    fun paidTotal() = FinanceAnalytics.paidTotal(appointments.value)
}

class SettingsFeatureViewModel(application: Application) : AndroidViewModel(application) {
    private val settings = SettingsRepository(application)
    val clinicName = settings.clinicName.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "")
    fun setClinicName(v: String) = viewModelScope.launch { settings.setClinicName(v) }
}

class SessionAccessViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    private val settings = SettingsRepository(application)
    private val therapistDao = db.therapistDao()
    val therapists = therapistDao.getActive().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val currentId = settings.currentTherapistId.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0L)

    fun isSecretary(): Boolean {
        val tid = currentId.value
        return therapists.value.find { it.id == tid }?.role == UserRole.SECRETARY
    }
}
