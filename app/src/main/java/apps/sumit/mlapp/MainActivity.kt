package apps.sumit.mlapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp)
                    .background(Color.White)
                    .verticalScroll(rememberScrollState(), true)
            ) {
                Column {
                    Row {
                        var painter: Painter = painterResource(id = R.drawable.image_labeler_logo)
                        var contentDescription: String = "Image Helper"
                        var title: String = contentDescription
                        var index: Int = 6
                        ImageCard(
                            painter = painter,
                            contentDescription = contentDescription,
                            title = contentDescription,
                            index = index,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        ImageCard(
                            painter = painter,
                            contentDescription = contentDescription,
                            title = contentDescription,
                            index = index,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Row {
                        var painter: Painter = painterResource(id = R.drawable.image_labeler_logo)
                        var contentDescription: String = "Image Helper"
                        var title: String = contentDescription
                        var index: Int = 6
                        ImageCard(
                            painter = painter,
                            contentDescription = contentDescription,
                            title = contentDescription,
                            index = index,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        ImageCard(
                            painter = painter,
                            contentDescription = contentDescription,
                            title = contentDescription,
                            index = index,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Row {
                        var painter: Painter = painterResource(id = R.drawable.image_labeler_logo)
                        var contentDescription: String = "Image Helper"
                        var title: String = contentDescription
                        var index: Int = 6
                        ImageCard(
                            painter = painter,
                            contentDescription = contentDescription,
                            title = contentDescription,
                            index = index,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        ImageCard(
                            painter = painter,
                            contentDescription = contentDescription,
                            title = contentDescription,
                            index = index,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Row {
                        var painter: Painter = painterResource(id = R.drawable.image_labeler_logo)
                        var contentDescription: String = "Image Helper"
                        var title: String = contentDescription
                        var index: Int = 6
                        ImageCard(
                            painter = painter,
                            contentDescription = contentDescription,
                            title = contentDescription,
                            index = index,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        ImageCard(
                            painter = painter,
                            contentDescription = contentDescription,
                            title = contentDescription,
                            index = index,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    index: Int,
    modifier: Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(25.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Box(
            modifier = Modifier.height(200.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            )
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Green,
                                fontSize = 25.sp,
                                shadow = Shadow(Color(0xFF0AA0FE))
                            )
                        ) {
                            append(title[0])
                        }
                        append(title.substring(1, index - 1) + " ")
                        withStyle(
                            style = SpanStyle(
                                color = Color.Green,
                                fontSize = 25.sp,
                                shadow = Shadow(Color(0xFF0AA0FE))
                            )
                        ) {
                            append(title[index])
                        }
                        append(title.substring(index + 1))
                    },
                    style = TextStyle(
                        color = Color.White,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 16.sp
                    )
                )
            }
        }
    }
}