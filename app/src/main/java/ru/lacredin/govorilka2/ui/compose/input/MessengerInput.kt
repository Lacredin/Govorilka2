package ru.lacredin.govorilka2.ui.compose.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import ru.lacredin.govorilka2.R


@Preview(showSystemUi = true)
@Composable
fun MessengerInput(modifier: Modifier = Modifier) {
//    BoxWithConstraints {
        ConstraintLayout(
            modifier = modifier
                .fillMaxWidth()
        ) {
            val (button, text) = createRefs()
            InputText(
                "",
                onTextChange = {},
                modifier = Modifier.constrainAs(text) {
                    start.linkTo(parent.start, margin = 4.dp)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(button.start, margin = 8.dp)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                }
            )
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .constrainAs(button) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
                    .background(Color.Transparent)
//                    .padding(top = 4.dp, bottom = 4.dp, end = 4.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_send_24),
                    contentDescription = "Отправить",
                    tint = MaterialTheme.colors.primaryVariant,
                )
            }
        }
//    }
}

@Composable
fun InputText(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    BasicTextField(
        value = text,
        onValueChange = onTextChange,
//        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
//        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        modifier = modifier
    )
}