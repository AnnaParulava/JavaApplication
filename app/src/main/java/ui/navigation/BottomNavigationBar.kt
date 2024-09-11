package ui.navigation


import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.javaapplication.util.Constants
import ui.theme.MainColor

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    NavigationBar(
        containerColor = MainColor
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val currentRoute = navBackStackEntry?.destination?.route

        Constants.getBottomNavItems().forEach { navItem ->
            NavigationBarItem(
                selected = currentRoute == navItem.route,

                onClick = {
                    if (currentRoute != navItem.route) {
                        navController.navigate(navItem.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.LightGray,
                    indicatorColor = Color.Transparent
                ),

                icon = {
                    Icon(
                        painter = navItem.icon,
                        contentDescription = navItem.label,
                    )
                },

                label = {
                    Text(text = navItem.label, color = Color.White)
                },

                alwaysShowLabel = false
            )
        }
    }
}
