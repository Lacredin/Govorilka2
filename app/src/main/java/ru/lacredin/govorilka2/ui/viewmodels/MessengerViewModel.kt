package ru.lacredin.govorilka2.ui.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import ru.lacredin.govorilka2.domain.models.MessageDataItem

class MessengerViewModel : ViewModel() {
    var messageItems = mutableStateListOf<MessageDataItem>()
        private set
}