package com.example.medancapilpelaporan.utils.routing

import com.example.medancapilpelaporan.ui.history2.HistoryActivity

class Routes {
    private var classRoute: Class<*>? = null

    companion object {
        @Volatile
        private var INSTANCE: Routes? = null

        fun getInstance(): Routes = INSTANCE ?: synchronized(this) {
            val instance = Routes()
            INSTANCE = instance
            instance
        }
    }

    fun setDestination(destination: String) {
        when(destination) {


            "lapor_history" -> classRoute = HistoryActivity::class.java
            else -> {
                classRoute = null
                println("No route available!")
            }
        }
    }

    fun getRoute(): Class<*>? {
        return classRoute
    }
}