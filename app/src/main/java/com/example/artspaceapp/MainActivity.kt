package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtGalleryScreen()
                }
            }
        }
    }
}

data class Artwork(val title: String, val artist: String, val imageResId: Int)

@Composable
fun ArtGalleryScreen() {
    val artworks = listOf(
        Artwork("Tom Campbell's Hill", "Bruno Accorsi", R.drawable.photo1),
        Artwork("Lake Louise", "Bruno Accorsi", R.drawable.photo2),
        Artwork("Lake Moraine", "Bruno Accorsi", R.drawable.photo3)
    )

    var currentIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.elevatedCardElevation(6.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = artworks[currentIndex].imageResId),
                    contentDescription = artworks[currentIndex].title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(12.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = artworks[currentIndex].title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Black
                    )
                    Text(
                        text = "by ${artworks[currentIndex].artist}",
                        fontSize = 18.sp,
                        color = Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { currentIndex = if (currentIndex > 0) currentIndex - 1 else artworks.size - 1 },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF021723)),
                shape = RoundedCornerShape(50)
            ) {
                Text("Previous", color = Color.White)
            }

            Button(
                onClick = { currentIndex = (currentIndex + 1) % artworks.size },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF021723)),
                shape = RoundedCornerShape(50)
            ) {
                Text("Next", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtGalleryPreview() {
    ArtSpaceAppTheme {
        ArtGalleryScreen()
    }
}