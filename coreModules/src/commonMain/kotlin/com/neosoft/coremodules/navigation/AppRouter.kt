package com.neosoft.coremodules.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

class AppRouter(private val navController: NavHostController) {

    fun go(route: Route) {
        navController.navigate(route.path)
    }

    fun <T: Any> go(route: Route.WithArgs<T>, obj: T) {
        val encoded = NavigationSerializer.encode(obj, route.serializer)
        navController.navigate("${route.routeName}/$encoded")
    }

    fun <T : Any> getArgs(
        backStackEntry: NavBackStackEntry,
        route: Route.WithArgs<T>
    ): T? {
        val routeString = backStackEntry.destination.route ?: ""
        val postEncoded = routeString
            .substringAfter("args=", "1")
        println("backStackEntry")
        println(postEncoded)
        return NavigationSerializer.decode(postEncoded, route.serializer)
    }




    fun pop() = navController.popBackStack()
}
