package com.d121211082.movielists.ui.activities.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211082.movielists.data.models.Movie
import com.d121211082.movielists.ui.theme.MovieListsTheme

class DetailActivity : ComponentActivity() {

    private var selectedMovie: Movie? = null

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedMovie = intent.getParcelableExtra("MOVIE")
        setContent {
            MovieListsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        CenterAlignedTopAppBar(
                            modifier = Modifier.background(Color.Blue),  // Change background color here
                            title = {
                                Text(
                                    text = "Detail",
                                    fontWeight = FontWeight.SemiBold,
                                )

                            },
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(
                                        modifier = Modifier.height(512.dp),
                                        contentDescription = "",
                                        imageVector = Icons.Default.KeyboardArrowLeft
                                    )
                                }
                            }

                        )

                        DetailScreen()
                    }

                }
            }
        }
    }

    @Composable
    fun DetailScreen() {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        )
        {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data("https://image.tmdb.org/t/p/original" + selectedMovie?.poster_path)
                    .crossfade(true)
                    .build(),
                contentDescription = selectedMovie?.title,
                modifier = Modifier
                    .width(400.dp)
                    .height(450.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .align(Alignment.CenterHorizontally), // Aligns the image horizontally in the column
                contentScale = ContentScale.Crop
            )

            // Movie Details
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = selectedMovie?.title.toString(),
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = selectedMovie?.release_date.toString(),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Rating: ${selectedMovie?.vote_average.toString()}",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Description :",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,


            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = selectedMovie?.overview.toString(),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify
            )
        }
    }
}