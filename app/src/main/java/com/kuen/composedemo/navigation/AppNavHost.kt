package com.kuen.composedemo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.kuen.composedemo.home.AboutRoute
import com.kuen.composedemo.home.HomeRoute
import com.kuen.composedemo.samples.SamplesHubScreen
import com.kuen.composedemo.samples.design.DesignSystemSampleScreen
import com.kuen.composedemo.samples.lazy.LazyListSampleScreen
import com.kuen.composedemo.samples.modifier.ModifierLabScreen
import com.kuen.composedemo.samples.animation.AnimationLabScreen
import com.kuen.composedemo.samples.controls.ControlsLabScreen
import com.kuen.composedemo.samples.focus.FocusInsetsLabScreen
import com.kuen.composedemo.samples.layout.CustomLayoutLabScreen
import com.kuen.composedemo.samples.stability.StabilityLabScreen
import com.kuen.composedemo.samples.profile.ProfileNavSampleScreen
import com.kuen.composedemo.samples.sideeffect.SideEffectSampleScreen
import com.kuen.composedemo.samples.state.StateSampleScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) {
            HomeRoute(
                onOpenAbout = { navController.navigate(Routes.ABOUT) },
                onOpenSamples = { navController.navigate(Routes.SAMPLES_HUB) },
            )
        }
        composable(Routes.ABOUT) {
            AboutRoute(onBack = { navController.popBackStack() })
        }
        composable(Routes.SAMPLES_HUB) {
            SamplesHubScreen(
                onBack = { navController.popBackStack() },
                onNavigate = { route -> navController.navigate(route) },
            )
        }
        composable(Routes.DESIGN_SAMPLE) {
            DesignSystemSampleScreen(onBack = { navController.popBackStack() })
        }
        composable(Routes.STATE_SAMPLE) {
            StateSampleScreen(onBack = { navController.popBackStack() })
        }
        composable(
            route = Routes.PROFILE_WITH_ARGS,
            arguments = listOf(
                navArgument(Routes.ARG_USER_ID) { type = NavType.StringType },
            ),
        ) { entry ->
            val userId = entry.arguments?.getString(Routes.ARG_USER_ID).orEmpty()
            ProfileNavSampleScreen(
                userId = userId,
                onBack = { navController.popBackStack() },
            )
        }
        composable(Routes.LAZY_SAMPLE) {
            LazyListSampleScreen(onBack = { navController.popBackStack() })
        }
        composable(Routes.SIDE_EFFECT_SAMPLE) {
            SideEffectSampleScreen(onBack = { navController.popBackStack() })
        }
        composable(Routes.MODIFIER_SAMPLE) {
            ModifierLabScreen(onBack = { navController.popBackStack() })
        }
        composable(Routes.STABILITY_SAMPLE) {
            StabilityLabScreen(onBack = { navController.popBackStack() })
        }
        composable(Routes.CONTROLS_SAMPLE) {
            ControlsLabScreen(onBack = { navController.popBackStack() })
        }
        composable(Routes.CUSTOM_LAYOUT_SAMPLE) {
            CustomLayoutLabScreen(onBack = { navController.popBackStack() })
        }
        composable(Routes.ANIMATION_SAMPLE) {
            AnimationLabScreen(onBack = { navController.popBackStack() })
        }
        composable(Routes.FOCUS_SAMPLE) {
            FocusInsetsLabScreen(onBack = { navController.popBackStack() })
        }
    }
}
