package org.kekus.rsachat.chats

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    chat: Chat,
    onBack: () -> Unit,
    onForward: (ChatMessage) -> Unit,
    onReply: (ChatMessage) -> Unit,
    onDelete: (ChatMessage) -> Unit,
    viewModel: ChatViewModel = remember { ChatViewModel(chat) }
) {
    val messages by viewModel.messages.collectAsState()
    var input by remember { mutableStateOf("") }
    var showAttach by remember { mutableStateOf(false) }
    var menuForMessage by remember { mutableStateOf<ChatMessage?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                title = { Text(chat.title) }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            LazyColumn(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                reverseLayout = true,
                verticalArrangement = Arrangement.Bottom
            ) {
                items(messages, key = { it.id }) { message ->
                    MessageBubble(
                        message = message,
                        onLongPress = { menuForMessage = message }
                    )
                }
            }
            Divider()
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { showAttach = true }) {
                    Icon(Icons.Default.AttachFile, contentDescription = "Attach")
                }
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = input,
                    onValueChange = { input = it },
                    maxLines = 4,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                    keyboardActions = KeyboardActions(onSend = {
                        if (input.isNotBlank()) {
                            viewModel.sendMessage(input)
                            input = ""
                        }
                    })
                )
                IconButton(onClick = {
                    if (input.isNotBlank()) {
                        viewModel.sendMessage(input)
                        input = ""
                    }
                }) {
                    Icon(Icons.Default.Send, contentDescription = "Send")
                }
            }
        }
    }

    DropdownMenu(
        expanded = showAttach,
        onDismissRequest = { showAttach = false }
    ) {
        DropdownMenuItem(text = { Text("Photo") }, onClick = { showAttach = false })
        DropdownMenuItem(text = { Text("File") }, onClick = { showAttach = false })
        DropdownMenuItem(text = { Text("Other") }, onClick = { showAttach = false })
    }

    menuForMessage?.let { msg ->
        DropdownMenu(
            expanded = true,
            onDismissRequest = { menuForMessage = null }
        ) {
            DropdownMenuItem(text = { Text("Reply") }, onClick = { onReply(msg); menuForMessage = null })
            DropdownMenuItem(text = { Text("Forward") }, onClick = { onForward(msg); menuForMessage = null })
            if (msg.isMine) {
                DropdownMenuItem(text = { Text("Delete") }, onClick = { onDelete(msg); menuForMessage = null })
            }
        }
    }
}

@Composable
private fun MessageBubble(message: ChatMessage, onLongPress: () -> Unit) {
    Column(
        horizontalAlignment = if (message.isMine) Alignment.End else Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = { onLongPress() })
            }
    ) {
        if (!message.isMine) {
            Text(message.authorName, fontSize = 12.sp, color = Color.Gray)
        }
        Surface(
            color = if (message.isMine) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
            tonalElevation = 2.dp,
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(8.dp),
                color = if (message.isMine) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
