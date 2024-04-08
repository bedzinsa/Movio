package com.arunasbedzinskas.movio.ui.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.arunasbedzinskas.movio.models.data.Genre
import com.arunasbedzinskas.movio.models.data.KeyFact
import com.arunasbedzinskas.movio.models.state.UiState
import com.arunasbedzinskas.movio.models.ui.KeyFactUI
import com.arunasbedzinskas.movio.models.ui.MovieDetailsUI
import com.arunasbedzinskas.movio.ui.R
import com.arunasbedzinskas.movio.ui.components.compose.MovioAppTheme
import com.arunasbedzinskas.movio.ui.components.compose.Rating
import com.arunasbedzinskas.movio.ui.ext.popBackStack
import com.arunasbedzinskas.movio.ui.ext.toPx
import com.arunasbedzinskas.movio.ui.viewmodels.FavoriteViewModel
import com.arunasbedzinskas.movio.ui.viewmodels.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.ceil

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()

    private val movieDetailsProvider: MovieDetailsProvider
        get() = movieDetailsViewModel.movieDetailsProvider

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            MovioAppTheme { MovieDetails() }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieDetailsViewModel.initWithMovieId(args.movieId)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MovieDetails() {
        val movieDetailsState by movieDetailsViewModel.movieData.collectAsState()

        Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
            Surface(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                Column(Modifier.padding(horizontal = 24.dp)) {
                    TopAppBar(
                        title = {},
                        actions = { TopBarActions((movieDetailsState as? UiState.NormalState)?.data) }
                    )

                    val movieDetailsUI = (movieDetailsState as? UiState.NormalState)?.data
                    movieDetailsUI?.let {
                        MovieDetailsContent(movieDetailsUI)
                        MovieKeyFacts(movieDetailsUI.keyFacts)
                    }
                }
            }
        }
    }

    @Composable
    fun TopBarActions(movieDetailsUI: MovieDetailsUI?) {
        movieDetailsUI?.let {
            IconToggleButton(
                checked = movieDetailsUI.isFavorite,
                colors = IconButtonDefaults.iconToggleButtonColors(
                    containerColor = Color.Transparent,
                    checkedContentColor = Color.Unspecified
                ),
                onCheckedChange = { isChecked ->
                    favoriteViewModel.onFavoriteChanged(movieDetailsUI.id, isChecked)
                }
            ) {
                Icon(
                    painter = painterResource(
                        if (movieDetailsUI.isFavorite)
                            R.drawable.ic_favorite_checked
                        else
                            R.drawable.ic_favorite_unchecked
                    ),
                    contentDescription = stringResource(R.string.cd_details_favorite)
                )
            }
        }
        Box(
            modifier = Modifier
                .background(colorResource(R.color.dark_grey), CircleShape)
                .size(30.dp)
                .clickable { popBackStack() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_close),
                contentDescription = stringResource(R.string.cd_details_close)
            )
        }
    }

    @Composable
    fun ColumnScope.MovieDetailsContent(movieDetailsUI: MovieDetailsUI) {
        AsyncImage(
            model = with(ImageRequest.Builder(requireContext())) {
                data(movieDetailsUI.logo)
                transformations(RoundedCornersTransformation(14.dp.toPx()))
                build()
            },
            contentDescription = stringResource(R.string.cd_details_logo),
            modifier = Modifier
                .width(200.dp)
                .wrapContentHeight()
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Rating(
            rating = movieDetailsUI.rating,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "${movieDetailsUI.releaseDate} · ${movieDetailsUI.duration}",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.labelMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = movieDetailsUI.title,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.displaySmall
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = movieDetailsUI.releaseYear,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.headlineLarge,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            movieDetailsUI.genres.forEach { Genre(it) }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Label(text = stringResource(R.string.details_content_title_overview))
        Text(
            text = movieDetailsUI.overview,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(24.dp))
    }

    @Composable
    fun Label(text: String) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
    }

    @Composable
    fun Genre(genre: Genre) {
        Box(
            modifier = Modifier
                .padding(4.dp)
                .background(
                    colorResource(R.color.dark_grey),
                    RoundedCornerShape(12.dp)
                )
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = movieDetailsProvider.getGenre(genre),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

    @Composable
    fun MovieKeyFacts(keyFacts: List<KeyFactUI>) {
        Label(text = stringResource(R.string.details_content_title_key_facts))

        val amountOfRows = ceil(keyFacts.size.toDouble() / 2).toInt()
        for (row in 0 until amountOfRows) {
            Row(Modifier.fillMaxWidth()) {

                val column1Index = row + row
                KeyFactContent(
                    keyFactUI = keyFacts[column1Index],
                    modifier = Modifier.padding(end = 4.dp, bottom = 8.dp)
                )

                val column2Index = column1Index + 1
                if (column2Index < keyFacts.size) {
                    KeyFactContent(
                        keyFactUI = keyFacts[column2Index],
                        modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
    }

    @Composable
    fun RowScope.KeyFactContent(
        keyFactUI: KeyFactUI,
        modifier: Modifier = Modifier
    ) {
        Column(
            Modifier
                .weight(1f)
                .then(modifier)
                .background(colorResource(R.color.dark_grey), RoundedCornerShape(12.dp))
                .padding(8.dp)
        ) {
            Text(
                text = movieDetailsProvider.getKeyFactTitle(keyFactUI.keyFact),
                style = MaterialTheme.typography.bodyMedium
            )
            val value = if (keyFactUI.keyFact == KeyFact.OriginalLanguage)
                movieDetailsProvider.getLanguage(keyFactUI.value)
            else
                keyFactUI.value
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
