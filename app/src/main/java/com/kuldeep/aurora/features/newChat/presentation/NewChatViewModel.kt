package com.kuldeep.aurora.features.newChat.presentation

import com.kuldeep.aurora.core.ui.BaseViewModel
import com.kuldeep.aurora.features.newChat.domain.usecases.GetContactsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NewChatViewModel @Inject constructor(
    private val getContactsUseCase: GetContactsUseCase
) : BaseViewModel() {


    private val _uiState = MutableStateFlow(NewChatUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: NewChatUiEvent) {
        when (event) {
            NewChatUiEvent.GetContacts -> getContacts()
            is NewChatUiEvent.OnContactSelected -> {

                _uiState.update {
                    it.copy(onContactSelected = event.contact)
                }
            }

            is NewChatUiEvent.RequestContactPermission -> {
                _uiState.update {
                    it.copy(
                        requestContactPermission = event.granted
                    )
                }
            }

            is NewChatUiEvent.ContactPermissionResult -> {
                _uiState.update {
                    it.copy(
                        contactPermissionGranted = event.granted
                    )
                }
            }

            is NewChatUiEvent.SearchQueryChanged -> {
                _uiState.update {
                    it.copy(searchQuery = event.query)
                }
                filterContacts(event.query)
            }

            is NewChatUiEvent.SearchResultsChanged -> {
                _uiState.update {
                    it.copy(searchResults = event.results)
                }
            }
        }
    }

    private fun filterContacts(query: String) {

        launchWithDefaultDispatcher {
            val filteredContacts = uiState.value.contacts.filter {
                it.name.contains(query, ignoreCase = true)
            }
            _uiState.update {
                it.copy(searchResults = filteredContacts)
            }
        }

    }

    private fun getContacts() {

        if (!uiState.value.contactPermissionGranted)
            return

        launchWithIoDispatcher {
            _uiState.update {
                it.copy(contacts = getContactsUseCase())
            }
        }
    }
}