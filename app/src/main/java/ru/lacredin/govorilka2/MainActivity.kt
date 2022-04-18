package ru.lacredin.govorilka2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import ru.lacredin.govorilka2.domain.models.MessageDataItem
import ru.lacredin.govorilka2.ui.compose.input.MessengerInput
import ru.lacredin.govorilka2.ui.theme.Govorilka2Theme
import ru.lacredin.govorilka2.ui.theme.Shapes
import ru.lacredin.govorilka2.ui.theme.subInfoMessage
import ru.lacredin.govorilka2.ui.viewmodels.MessengerViewModel
import java.util.*

class MainActivity : ComponentActivity() {

    val messengerViewModel by viewModels<MessengerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Govorilka2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Govorilka2Theme {
        Greeting("Android")
    }
}

@Composable
fun MessengerScreen(modifier: Modifier = Modifier, viewModel: MessengerViewModel) {
    ConstraintLayout(modifier = modifier) {
        val (listItems, textField) = createRefs()
        MessagesList(
            items = viewModel.messageItems,
            modifier = Modifier.constrainAs(listItems) {
                start.linkTo(parent.start)
                end.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(textField.bottom)
            }
        )
        MessengerInput(
            modifier = Modifier.constrainAs(textField) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }
        )
    }
}

@Composable
fun MessagesList(modifier: Modifier = Modifier, items: List<MessageDataItem> = listOf()) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(items) { message ->
            Box(Modifier.fillMaxSize()) {
                val (alignment, horizontalAlign) = if (message.isOwn) Alignment.CenterStart to Alignment.Start
                else Alignment.CenterEnd to Alignment.End
                MessageItem(
                    modifier = Modifier.align(alignment),
                    horizontalAlignment = horizontalAlign,
                    item = message
                )
            }
        }
    }
}

@Composable
fun MessageItem(
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    item: MessageDataItem
) {
    Column(
        horizontalAlignment = horizontalAlignment,
        modifier = modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
            .fillMaxWidth(0.55f)
    ) {
        Card(
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
            shape = Shapes.medium,
            elevation = 8.dp,
        ) {
            Text(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
                text = item.message
            )
        }
        Text(
            text = "${item.userName}   ${item.date}",
            style = subInfoMessage,
        )
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
fun MessagePreview() {
    MessageItem(
        modifier = Modifier,
        item = MessageDataItem(
            message = "Текст сообщения",
            date = Date(),
            userId = UUID.randomUUID().toString(),
            userName = "LOGIN",
            mutableList = mutableListOf(),
            isOwn = false
        )
    )
}

@Preview(showSystemUi = true)
@Composable
fun MessagesListPreview() {
    val list = listOf(
        MessageDataItem(
            message = "Текст сообщения 1 " +
                    "Текст сообщения 1 " +
                    "Текст сообщения 1 " +
                    "Текст сообщения 1 " +
                    "Текст сообщения 1 ",

            date = Date(),
            userId = UUID.randomUUID().toString(),
            userName = "Denis",
            mutableList = mutableListOf(),
            isOwn = true
        ),
        MessageDataItem(
            message = "Текст сообщения 2",
            date = Date(),
            userId = UUID.randomUUID().toString(),
            userName = "Nasty",
            mutableList = mutableListOf(),
            isOwn = false
        ),
        MessageDataItem(
            message = "Текст сообщения 3",
            date = Date(),
            userId = UUID.randomUUID().toString(),
            userName = "Denis",
            mutableList = mutableListOf(),
            isOwn = true
        ),
        MessageDataItem(
            message = "Текст сообщения 4",
            date = Date(),
            userId = UUID.randomUUID().toString(),
            userName = "LOGIN",
            mutableList = mutableListOf(),
            isOwn = false
        )
    )

    Govorilka2Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            ConstraintLayout(
                Modifier.fillMaxSize()
            ) {
                val (listItems, textField) = createRefs()
                MessagesList(
                    items = list,
                    modifier = Modifier.constrainAs(listItems) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(textField.top)
                    }
                )
                MessengerInput(
                    modifier = Modifier.constrainAs(textField) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                )
            }
        }
    }
}