
package com.youcef_bounaas.animevault

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.youcef_bounaas.animevault.ui.screen.anime.AnimeScreen
import com.youcef_bounaas.animevault.ui.theme.AnimeVaultTheme
import com.youcef_bounaas.animevault.ui.screen.trending_anime.TrendingAnimeScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimeVaultTheme {
                val navController = rememberNavController()

                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.dark(
                        android.graphics.Color.TRANSPARENT
                    )
                )

                SharedTransitionLayout {
                    NavHost(navController = navController, startDestination = TrendingAnimeRoute) {
                        composable<TrendingAnimeRoute> {
                            TrendingAnimeScreen(
                                animatedVisibilityScope = this,
                                onAnimeClick = { coverUrl, id ->
                                    navController.navigate(
                                        AnimeRoute(coverUrl = coverUrl, id = id)
                                    )
                                }
                            )
                        }

                        composable<AnimeRoute> {
                            val args = it.toRoute<AnimeRoute>()

                            AnimeScreen(
                                animatedVisibilityScope = this,
                                id = args.id.toInt(),
                                coverImage = args.coverUrl
                            )
                        }
                    }
                }

            }
        }
    }
}

@Serializable
data object TrendingAnimeRoute

@Serializable
data class AnimeRoute(val coverUrl: String, val id: String)

