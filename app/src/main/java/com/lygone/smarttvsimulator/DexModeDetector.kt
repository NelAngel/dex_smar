package com.lygone.smarttvsimulator

import android.content.Context
import android.content.res.Configuration

/**
 * Detecta Samsung DeX usando el indicador documentado por Samsung en Configuration.
 * La reflexión evita depender de campos Samsung que no forman parte del SDK Android público.
 */
object DexModeDetector {
    fun isSamsungDex(context: Context): Boolean {
        val config = context.resources.configuration
        return try {
            val configClass = config.javaClass
            val enabledValue = configClass.getField("SEM_DESKTOP_MODE_ENABLED").getInt(configClass)
            val currentValue = configClass.getField("semDesktopModeEnabled").getInt(config)
            currentValue == enabledValue
        } catch (_: ReflectiveOperationException) {
            // Fallback conservador para dispositivos que exponen el modo escritorio estándar.
            config.uiMode and Configuration.UI_MODE_TYPE_MASK == Configuration.UI_MODE_TYPE_DESK
        } catch (_: RuntimeException) {
            false
        }
    }
}
