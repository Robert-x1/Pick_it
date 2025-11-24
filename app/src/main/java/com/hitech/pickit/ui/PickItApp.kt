package com.hitech.pickit.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.hitech.pickit.R
import com.hitech.pickit.auth.presentation.SignInScreen
import com.hitech.pickit.auth.presentation.SignInViewModel
import com.hitech.pickit.movie.presentation.BOOK_list.BookmarkViewModel
import com.hitech.pickit.movie.presentation.BOOK_list.UnifiedFavListScreen
import com.hitech.pickit.movie.presentation.credit.CastScreen
import com.hitech.pickit.movie.presentation.credit.CrewScreen
import com.hitech.pickit.movie.presentation.credit.PersonScreen
import com.hitech.pickit.movie.presentation.credit.PersonViewModel
import com.hitech.pickit.movie.presentation.detail_screen.MovieDetailScreen
import com.hitech.pickit.movie.presentation.detail_screen.MovieDetailViewModel
import com.hitech.pickit.movie.presentation.detail_screen.TVShowDetailScreen
import com.hitech.pickit.movie.presentation.detail_screen.TVShowDetailViewModel
import com.hitech.pickit.movie.presentation.feed.MovieFeedScreen
import com.hitech.pickit.movie.presentation.feed.MovieFeedViewModel
import com.hitech.pickit.movie.presentation.feed.TVShowFeedScreen
import com.hitech.pickit.movie.presentation.feed.TVShowFeedViewModel
import com.hitech.pickit.movie.presentation.image.ImagesScreen
import com.hitech.pickit.movie.presentation.noDataEmptyScreen.EmptyScreen
import com.hitech.pickit.movie.presentation.onBoardingScreen.MainOnboardingFlow
import com.hitech.pickit.movie.presentation.paging.main.AiringTodayTVShowScreen
import com.hitech.pickit.movie.presentation.paging.main.DiscoverMovieScreen
import com.hitech.pickit.movie.presentation.paging.main.DiscoverTVShowScreen
import com.hitech.pickit.movie.presentation.paging.main.NowPlayingMovieScreen
import com.hitech.pickit.movie.presentation.paging.main.OnTheAirTVShowScreen
import com.hitech.pickit.movie.presentation.paging.main.PopularMovieScreen
import com.hitech.pickit.movie.presentation.paging.main.PopularTVShowScreen
import com.hitech.pickit.movie.presentation.paging.main.SimilarMovieScreen
import com.hitech.pickit.movie.presentation.paging.main.SimilarTVShowScreen
import com.hitech.pickit.movie.presentation.paging.main.TopRatedMovieScreen
import com.hitech.pickit.movie.presentation.paging.main.TopRatedTVShowScreen
import com.hitech.pickit.movie.presentation.paging.main.TrendingMovieScreen
import com.hitech.pickit.movie.presentation.paging.main.TrendingTVShowScreen
import com.hitech.pickit.movie.presentation.paging.main.UpcomingMovieScreen
import com.hitech.pickit.movie.presentation.paging.main.movie.DiscoverMoviesViewModel
import com.hitech.pickit.movie.presentation.paging.main.movie.NowPlayingMoviesViewModel
import com.hitech.pickit.movie.presentation.paging.main.movie.PopularMoviesViewModel
import com.hitech.pickit.movie.presentation.paging.main.movie.SimilarMoviesViewModel
import com.hitech.pickit.movie.presentation.paging.main.movie.TopRatedMoviesViewModel
import com.hitech.pickit.movie.presentation.paging.main.movie.TrendingMoviesViewModel
import com.hitech.pickit.movie.presentation.paging.main.movie.UpcomingMoviesViewModel
import com.hitech.pickit.movie.presentation.paging.main.tvshow.AiringTodayTvSeriesViewModel
import com.hitech.pickit.movie.presentation.paging.main.tvshow.DiscoverTvSeriesViewModel
import com.hitech.pickit.movie.presentation.paging.main.tvshow.OnTheAirTvSeriesViewModel
import com.hitech.pickit.movie.presentation.paging.main.tvshow.PopularTvSeriesViewModel
import com.hitech.pickit.movie.presentation.paging.main.tvshow.SimilarTvSeriesViewModel
import com.hitech.pickit.movie.presentation.paging.main.tvshow.TopRatedTvSeriesViewModel
import com.hitech.pickit.movie.presentation.paging.main.tvshow.TrendingTvSeriesViewModel
import com.hitech.pickit.movie.presentation.paging.search.SearchMoviesScreen
import com.hitech.pickit.movie.presentation.paging.search.SearchMoviesViewModel
import com.hitech.pickit.movie.presentation.paging.search.SearchTVSeriesScreen
import com.hitech.pickit.movie.presentation.paging.search.SearchTVSeriesViewModel
import com.hitech.pickit.movie.presentation.profile.ProfileScreen
import com.hitech.pickit.movie.utili.Constants.ONBOARDING_ROUTE
import com.hitech.pickit.movie.utili.MainDestinations
import com.hitech.pickit.movie.utili.MainDestinations.HOME_ROUTE
import com.hitech.pickit.movie.utili.MainDestinations.SIGNIN_ROUTE

@Composable
fun PickItApp(
    viewModel: MainViewModel = hiltViewModel()
) {
    val appState = rememberPickItAppState()
    val isLoading by viewModel.isLoading.collectAsState()
    val startDestination by viewModel.startDestination.collectAsState()
    Scaffold(
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                TMDbBottomBar(
                    tabs = appState.bottomBarTabs,
                    currentRoute = appState.currentRoute ?: HomeSections.MOVIE_SECTION.route,
                    navigateToRoute = { route -> appState.navigateToBottomBarRoute(route) },
                )
            }
        },
    ) { innerPaddingModifier ->
        val newPadding =
            PaddingValues(
                start = innerPaddingModifier.calculateStartPadding(LocalLayoutDirection.current),
                end = innerPaddingModifier.calculateEndPadding(LocalLayoutDirection.current),
                bottom = innerPaddingModifier.calculateBottomPadding(),
                top = 0.dp
            )
        NavHost(
            navController = appState.navController,
            startDestination = startDestination,
            modifier = Modifier.padding(newPadding),
        ) {
            onboardingScreen(appState.navController, viewModel)
            navigationScreens(appState.navController)

            movieDetailGraph(appState.navController)

            tvShowDetailGraph(appState.navController)

            searchScreens(appState.navController)

            personScreen(appState.navController)

            moviePagingScreens(appState.navController)
            tvShowPagingScreens(appState.navController)

            signInScreen(navController = appState.navController)
        }
    }
}

@Composable
fun TMDbBottomBar(
    tabs: Array<HomeSections>,
    currentRoute: String,
    navigateToRoute: (String) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = Color.White,
        tonalElevation = 0.dp,
        modifier = Modifier.navigationBarsPadding()
    ) {
        tabs.forEach { section ->
            val selected = section.route == currentRoute

            NavigationBarItem(
                selected = selected,
                onClick = { navigateToRoute(section.route) },

                icon = {
                    Icon(
                        imageVector = if (selected) section.selectedIcon else section.unselectedIcon,
                        contentDescription = stringResource(id = section.title)
                    )
                },

                label = {
                    Text(
                        text = stringResource(id = section.title),
                        style = MaterialTheme.typography.labelMedium
                    )
                },

                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,

                    selectedIconColor = MaterialTheme.colorScheme.onSurface,
                    selectedTextColor = MaterialTheme.colorScheme.onSurface,

                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}

private fun NavGraphBuilder.navigationScreens(navController: NavController) {
    navigation(
        route = HOME_ROUTE,
        startDestination = HomeSections.MOVIE_SECTION.route,
    ) {
        composable(route = HomeSections.MOVIE_SECTION.route) {
            val viewModel: MovieFeedViewModel = hiltViewModel()
            MovieFeedScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = HomeSections.TV_SHOW_SECTION.route) {
            val viewModel: TVShowFeedViewModel = hiltViewModel()
            TVShowFeedScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(
            route = HomeSections.BOOKMARK_SECTION.route
        ) {
            val viewModel: BookmarkViewModel = hiltViewModel()
            val state by viewModel.uiState.collectAsState()

            LaunchedEffect(Unit) { viewModel.loadBookmarks() }

            var selectedTab by rememberSaveable { mutableIntStateOf(0) }
            val tabs = listOf("Movies", "TV Shows")

            Column {
                TabRow(
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(top = 16.dp),
                    selectedTabIndex = selectedTab,
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.primary
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(title) }
                        )
                    }
                }

                when (selectedTab) {
                    0 -> {
                        if (state.movies.isNotEmpty()) {
                            UnifiedFavListScreen(
                                items = state.movies,
                                isLoading = state.isLoading,
                                onItemClick = { id ->
                                    navController.navigate("${MainDestinations.TMDB_MOVIE_DETAIL_ROUTE}/$id")
                                }
                            )
                        } else {
                            EmptyScreen(message = "No Favorite Movies")
                        }
                    }

                    1 -> {
                        if (state.tvShows.isNotEmpty()) {
                            UnifiedFavListScreen(
                                items = state.tvShows,
                                isLoading = state.isLoading,
                                onItemClick = { id ->
                                    navController.navigate("${MainDestinations.TMDB_TV_SHOW_DETAIL_ROUTE}/$id")
                                }
                            )
                        } else {
                            EmptyScreen(

                                message = "No Favorite TV Shows",
                            )
                        }
                    }
                }
            }
        }


        composable(route = HomeSections.SETTING_SECTION.route) {
            ProfileScreen(
                onSignActionClick = {
                    navController.navigate(SIGNIN_ROUTE) {
                        popUpTo(HOME_ROUTE) { inclusive = true }
                    }
                }
            )
        }
    }
}

private fun NavGraphBuilder.movieDetailGraph(navController: NavHostController) {
    val graphRoute = "movie_details_graph"

    navigation(
        startDestination = "${MainDestinations.TMDB_MOVIE_DETAIL_ROUTE}/{${MainDestinations.TMDB_ID_KEY}}",
        route = graphRoute
    ) {

        composable(
            route = "${MainDestinations.TMDB_MOVIE_DETAIL_ROUTE}/{${MainDestinations.TMDB_ID_KEY}}",
            arguments = listOf(navArgument(MainDestinations.TMDB_ID_KEY) { type = NavType.IntType })
        ) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(graphRoute)
            }
            val viewModel: MovieDetailViewModel = hiltViewModel(parentEntry)

            MovieDetailScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(
            route = "${MainDestinations.TMDB_IMAGES_ROUTE}/{index}",
            arguments = listOf(navArgument("index") { type = NavType.IntType })
        ) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(graphRoute)
            }
            val viewModel: MovieDetailViewModel = hiltViewModel(parentEntry)
            val index = backStackEntry.arguments?.getInt("index") ?: 0

            ImagesScreen(
                viewModel = viewModel,
                initialPage = index,
                onUpPress = { navController.navigateUp() }
            )
        }

        composable(route = MainDestinations.TMDB_CAST_ROUTE) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(graphRoute)
            }
            val viewModel: MovieDetailViewModel = hiltViewModel(parentEntry)

            CastScreen(
                viewModel = viewModel,
                onUpPress = { navController.navigateUp() }
            )
        }

        composable(route = MainDestinations.TMDB_CREW_ROUTE) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(graphRoute)
            }
            val viewModel: MovieDetailViewModel = hiltViewModel(parentEntry)

            CrewScreen(
                viewModel = viewModel,
                onUpPress = { navController.navigateUp() }
            )
        }
    }
}

private fun NavGraphBuilder.tvShowDetailGraph(navController: NavHostController) {
    val graphRoute = "tv_show_details_graph"

    navigation(
        startDestination = "${MainDestinations.TMDB_TV_SHOW_DETAIL_ROUTE}/{${MainDestinations.TMDB_ID_KEY}}",
        route = graphRoute
    ) {
        composable(
            route = "${MainDestinations.TMDB_TV_SHOW_DETAIL_ROUTE}/{${MainDestinations.TMDB_ID_KEY}}",
            arguments = listOf(navArgument(MainDestinations.TMDB_ID_KEY) { type = NavType.IntType })
        ) { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry(graphRoute)
            }
            val viewModel: TVShowDetailViewModel = hiltViewModel(parentEntry)

            TVShowDetailScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}

private fun NavGraphBuilder.moviePagingScreens(navController: NavController) {

    composable(route = MainDestinations.TMDB_TRENDING_MOVIES_ROUTE) {
        val viewModel: TrendingMoviesViewModel = hiltViewModel()
        TrendingMovieScreen(navController = navController, viewModel = viewModel)
    }

    composable(route = MainDestinations.TMDB_POPULAR_MOVIES_ROUTE) {
        val viewModel: PopularMoviesViewModel = hiltViewModel()
        PopularMovieScreen(navController = navController, viewModel = viewModel)
    }

    composable(route = MainDestinations.TMDB_NOW_PLAYING_MOVIES_ROUTE) {
        val viewModel: NowPlayingMoviesViewModel = hiltViewModel()
        NowPlayingMovieScreen(navController = navController, viewModel = viewModel)
    }

    composable(route = MainDestinations.TMDB_UPCOMING_MOVIES_ROUTE) {
        val viewModel: UpcomingMoviesViewModel = hiltViewModel()
        UpcomingMovieScreen(navController = navController, viewModel = viewModel)
    }

    composable(route = MainDestinations.TMDB_TOP_RATED_MOVIES_ROUTE) {
        val viewModel: TopRatedMoviesViewModel = hiltViewModel()
        TopRatedMovieScreen(navController = navController, viewModel = viewModel)
    }

    composable(route = MainDestinations.TMDB_DISCOVER_MOVIES_ROUTE) {
        val viewModel: DiscoverMoviesViewModel = hiltViewModel()
        DiscoverMovieScreen(navController = navController, viewModel = viewModel)
    }

    composable(
        route = "${MainDestinations.TMDB_SIMILAR_MOVIES_ROUTE}/{${MainDestinations.TMDB_SIMILAR_ID}}",
        arguments = listOf(
            navArgument(MainDestinations.TMDB_SIMILAR_ID) { type = NavType.IntType },
        ),
    ) {
        val viewModel: SimilarMoviesViewModel = hiltViewModel()
        SimilarMovieScreen(navController = navController, viewModel = viewModel)
    }
}

private fun NavGraphBuilder.tvShowPagingScreens(navController: NavController) {

    composable(route = MainDestinations.TMDB_TRENDING_TV_SHOW_ROUTE) {
        val viewModel: TrendingTvSeriesViewModel = hiltViewModel()
        TrendingTVShowScreen(navController = navController, viewModel = viewModel)
    }

    composable(route = MainDestinations.TMDB_POPULAR_TV_SHOW_ROUTE) {
        val viewModel: PopularTvSeriesViewModel = hiltViewModel()
        PopularTVShowScreen(navController = navController, viewModel = viewModel)
    }

    composable(route = MainDestinations.TMDB_AIRING_TODAY_TV_SHOW_ROUTE) {
        val viewModel: AiringTodayTvSeriesViewModel = hiltViewModel()
        AiringTodayTVShowScreen(navController = navController, viewModel = viewModel)
    }

    composable(route = MainDestinations.TMDB_ON_THE_AIR_TV_SHOW_ROUTE) {
        val viewModel: OnTheAirTvSeriesViewModel = hiltViewModel()
        OnTheAirTVShowScreen(navController = navController, viewModel = viewModel)
    }

    composable(route = MainDestinations.TMDB_TOP_RATED_TV_SHOW_ROUTE) {
        val viewModel: TopRatedTvSeriesViewModel = hiltViewModel()
        TopRatedTVShowScreen(navController = navController, viewModel = viewModel)
    }

    composable(route = MainDestinations.TMDB_DISCOVER_TV_SHOW_ROUTE) {
        val viewModel: DiscoverTvSeriesViewModel = hiltViewModel()
        DiscoverTVShowScreen(navController = navController, viewModel = viewModel)
    }

    composable(
        route = "${MainDestinations.TMDB_SIMILAR_TV_SHOW_ROUTE}/{${MainDestinations.TMDB_SIMILAR_ID}}",
        arguments = listOf(
            navArgument(MainDestinations.TMDB_SIMILAR_ID) { type = NavType.IntType },
        ),
    ) {
        val viewModel: SimilarTvSeriesViewModel = hiltViewModel()
        SimilarTVShowScreen(navController = navController, viewModel = viewModel)
    }
}

private fun NavGraphBuilder.searchScreens(navController: NavController) {

    composable(route = MainDestinations.TMDB_SEARCH_MOVIE_ROUTE) {
        val viewModel: SearchMoviesViewModel = hiltViewModel()
        SearchMoviesScreen(
            navController = navController,
            viewModel = viewModel
        )
    }

    composable(route = MainDestinations.TMDB_SEARCH_TV_SHOW_ROUTE) {
        val viewModel: SearchTVSeriesViewModel = hiltViewModel()
        SearchTVSeriesScreen(
            navController = navController,
            viewModel = viewModel
        )
    }
}

private fun NavGraphBuilder.personScreen(navController: NavController) {
    composable(
        route = "${MainDestinations.TMDB_PERSON_ROUTE}/{${MainDestinations.TMDB_PERSON_KEY}}",
        arguments = listOf(
            navArgument(MainDestinations.TMDB_PERSON_KEY) { type = NavType.StringType },
        ),
    ) {
        val viewModel: PersonViewModel = hiltViewModel()

        PersonScreen(
            upPress = { navController.navigateUp() },
            viewModel = viewModel
        )
    }
}

private fun NavGraphBuilder.onboardingScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    composable(route = ONBOARDING_ROUTE) {
        MainOnboardingFlow(
            onFinish = {
                viewModel.saveOnBoardingState(completed = true)

                navController.navigate(HOME_ROUTE) {
                    popUpTo(ONBOARDING_ROUTE) { inclusive = true }
                }
            }
        )
    }
}

private fun NavGraphBuilder.signInScreen(
    navController: NavController,
) {
    composable(route = SIGNIN_ROUTE) {
        val viewModel = hiltViewModel<SignInViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        val context = LocalContext.current

        LaunchedEffect(key1 = state.isSignInSuccessful) {
            if (state.isSignInSuccessful) {
                navController.navigate(HOME_ROUTE) {
                    popUpTo(SIGNIN_ROUTE) { inclusive = true }
                }
            }
        }

        SignInScreen(
            state = state,
            onGoogleSignInClick = {
                viewModel.signInWithGoogle(context)
            },
            onGuestClick = {
                navController.navigate(HOME_ROUTE) {
                    popUpTo(SIGNIN_ROUTE) { inclusive = true }
                }
            }
        )
    }
}

enum class HomeSections(
    val route: String,
    @StringRes val title: Int,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
) {
    MOVIE_SECTION(
        route = "Movie",
        title = R.string.movie,
        unselectedIcon = Icons.Outlined.Movie,
        selectedIcon = Icons.Filled.Movie
    ),

    TV_SHOW_SECTION(
        route = "TVShow",
        title = R.string.tv_show,
        unselectedIcon = Icons.Outlined.Tv,
        selectedIcon = Icons.Filled.Tv
    ),

    BOOKMARK_SECTION(
        route = "Bookmark",
        title = R.string.favorite,
        unselectedIcon = Icons.Outlined.FavoriteBorder,
        selectedIcon = Icons.Filled.Favorite
    ),

    SETTING_SECTION(
        route = "Setting",
        title = R.string.setting,
        unselectedIcon = Icons.Outlined.Settings,
        selectedIcon = Icons.Filled.Settings
    ),
}