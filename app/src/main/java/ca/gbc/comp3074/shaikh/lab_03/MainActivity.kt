package ca.gbc.comp3074.shaikh.lab_03


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Data model for one message
data class Message(val author: String, val body: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Conversation(messages = sampleMessages())
                }
            }
        }
    }
}

// The same four messages repeated, like in the screenshot
fun sampleMessages(): List<Message> {
    val base = listOf(
        Message("Joe", "Hi!"),
        Message("Jim", "How are you?"),
        Message("Joe", "Test..1..2...3"),
        Message("Joe", "I hate coding!!!")
    )
    return List(5) { base }.flatten()
}

// Scrollable list of messages
@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn(modifier = Modifier.systemBarsPadding()) {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

// Custom composable for a single message row
@Composable
fun MessageCard(msg: Message) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        // Round avatar with a red border
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Profile picture of ${msg.author}",
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(1.5.dp, Color.Red, CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            // Author name
            Text(
                text = msg.author,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Message text inside a rounded "bubble" with a shadow
            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConversationPreview() {
    MaterialTheme {
        Conversation(messages = sampleMessages())
    }
}