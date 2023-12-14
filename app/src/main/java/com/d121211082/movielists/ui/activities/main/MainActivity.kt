package com.d121211082.movielists.ui.activities.main


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.d121211082.movielists.ui.activities.detail.DetailActivity
import com.d121211082.movielists.data.models.Movie
import com.d121211082.movielists.ui.activities.main.MainUiState
import com.d121211082.movielists.ui.activities.main.MainViewModel
import com.d121211082.movielists.ui.theme.MovieListsTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieListsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        CenterAlignedTopAppBar(
                            modifier = Modifier.background(Color.Blue),  // Change background color here
                            title = {
                                Text(
                                    text = "Movie Lists",
                                    fontWeight = FontWeight.SemiBold,
                                )
                            }
                        )
                        val mainViewModel: MainViewModel =
                            viewModel(factory = MainViewModel.Factory)
                        ListMoviesScreen(mainViewModel.mainUiState)
                    }

                }
            }
        }
    }

    @Composable
    private fun ListMoviesScreen(mainUiState: MainUiState, modifier: Modifier = Modifier) {
        when (mainUiState) {
            is MainUiState.Loading -> CenterText(text = "Loading...")
            is MainUiState.Error -> CenterText(text = "Something Error")
            is MainUiState.Success -> MovieList(mainUiState.movies)
        }
    }

    @Composable
    fun CenterText(text: String) {
        // Wrap the content with a Box to apply the centering modifiers
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Optional padding
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
            )
        }
    }

    @Composable
    fun MovieList(movies: List<Movie>, modifier: Modifier = Modifier) {

        LazyVerticalGrid(
            modifier = modifier,
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(movies) { movie ->
                MovieItem(movie = movie)
            }
        }
    }

    @Composable
    fun MovieItem(movie: Movie) {
        Box(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxHeight()
                .border(0.5.dp, Color.Gray)
                .clickable {
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("MOVIE", movie)
                    startActivity(intent)
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // Movie Poster
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data("https://image.tmdb.org/t/p/original" + movie.backdrop_path)
                        .crossfade(true)
                        .build(), contentDescription = movie.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )

                // Movie Details
               Column (modifier = Modifier.padding(8.dp).height(150.dp)){

                Text(
                    text = movie.title.toString(),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = movie.release_date.toString(),
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = movie.overview.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3
                )
                }


                // Other details like rating, genre, etc. can be added here
            }
        }
    }
}

