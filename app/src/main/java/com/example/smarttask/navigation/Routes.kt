package com.example.smarttask.navigation

sealed class Routes(val route: String) {
    object Splash : Routes("splash")
    object Login : Routes("login")
    object Register : Routes("register")
    object Home : Routes("home")
    object AddTask : Routes("add_task")
    object TaskDetails : Routes("task_details/{taskId}") {
        fun createRoute(taskId: Int) = "task_details/$taskId"
    }
    object EditTask : Routes("edit_task/{taskId}") {
        fun createRoute(taskId: Int) = "edit_task/$taskId"
    }
    object Notifications : Routes("notifications")
    object Dashboard : Routes("dashboard")
    object Profile : Routes("profile")
}