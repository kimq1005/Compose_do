package com.llama.compose_do.ui.stable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ContactRowScreen(contact: Contact, modifier: Modifier = Modifier) {
    var resultNumber by remember { mutableIntStateOf(0) }

    Column(modifier) {
        ResultTextLayout(number = resultNumber)
        ContactDetailLayout(contact)
        Button(onClick = {
            resultNumber += 1
        }) {

        }
    }
}

@Composable
fun ResultTextLayout(number: Int) {
    Text(text = number.toString())
}

@Composable
//@Stable
fun ContactDetailLayout(contact: Contact) {
    Column {
        Text(text = contact.name)

        Spacer(modifier = Modifier.height(7.dp))

        Text(text = contact.number)
    }
}

//@Immutable
data class Contact(
    val name: String,
    val number: String
)