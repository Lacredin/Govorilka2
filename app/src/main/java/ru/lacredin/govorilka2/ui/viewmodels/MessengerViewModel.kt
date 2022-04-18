package ru.lacredin.govorilka2.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableSource
import io.reactivex.rxjava3.subjects.BehaviorSubject
import ru.lacredin.govorilka2.domain.models.MessageDataItem

class MessengerViewModel : ViewModel() {
    var messageItems = mutableStateListOf<MessageDataItem>()
        private set

    var textState by mutableStateOf("")
    var mainState = MainState(null)
    val observer = BehaviorSubject.create<MviObject>()
    val updateObserver = observer.subscribe {
        observerView.onNext(it.state)
        if (it.action == null) Observable.amb(listOf())
        else it.action.getObserver()
    }
    val observerView = BehaviorSubject.create<State>()

    fun submit(text: String) {
        observer.onNext(
            MviObject(
                mainState,
                object : Action {
                    override fun getObserver(): ObservableSource<MviObject> {
                        return observer.map {
                            mainState.state = text
                            MviObject(mainState, null)
                        }
                    }
                }
            )
        )
    }

    fun subscribe() {
        observerView.subscribe {
            textState = it.toString()
        }
    }


}

sealed interface State

data class MainState(
    var state: String? = null
) : State

interface Action {
    fun getObserver(): ObservableSource<MviObject>
}

data class MviObject(
    val state: State,
    val action: Action?
)