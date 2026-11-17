# LYGANE Smart TV — Launcher v2.0

Launcher moderno para teléfonos Android y Android TV / Google TV. Kotlin + Jetpack Compose.

## Diseño
- **Fondo ambiental**: el resplandor cambia al color del icono de la app enfocada (estilo Google TV / Apple TV).
- **Hero dinámico**: en TV muestra la app enfocada con botones *Abrir* y *Favorito*; en móvil, tu última app usada.
- **Filas horizontales**: Recientes, Favoritos y Todas las aplicaciones, con zoom animado, borde blanco y halo de color al enfocar.
- **TV**: barra superior con pestañas, buscador desplegable, reloj y fecha. El foco inicial cae en la primera app y las pestañas cambian al mover el foco.
- **Móvil**: saludo según la hora y barra de navegación flotante tipo píldora.
- Pantalla completa de borde a borde (edge-to-edge), sin parpadeo blanco al abrir.

## Abrir apps y volver al launcher
- Cada app se abre por su componente exacto (Leanback en TV, Launcher en móvil) y, si falla, con el intent por defecto del paquete.
- **Atrás** dentro de la app abierta devuelve a LYGANE.
- **Inicio** devuelve a LYGANE **si es el launcher predeterminado**. Ajustes (engranaje) → *Usar como pantalla de inicio*. En Android TV, si el televisor no permite cambiarlo, aparece el selector de Android la primera vez que pulsas Inicio: elige LYGANE y *Siempre*.
- La actividad es `singleTask`: al pulsar Inicio con el launcher abierto vuelve a Inicio y al primer elemento.
- Con LYGANE como launcher, Atrás no cierra la pantalla; con él abierto como app normal, Atrás sale como siempre.
- La lista se actualiza sola al instalar, actualizar o desinstalar apps.

## Integración con Samsung DeX
- Detecta el modo Samsung DeX mediante la configuración del sistema Samsung y reacciona al cambio de configuración sin reiniciar la actividad.
- Si LYGANE está abierta al entrar en DeX, o se inicia desde el escritorio DeX, muestra un selector para permanecer en LYGANE o volver al escritorio Samsung.
- Las aplicaciones abiertas desde LYGANE se solicitan desde la actividad actual y, normalmente, continúan en la misma pantalla/tarea de DeX.
- La app no sustituye la pantalla de inicio interna de Samsung DeX ni puede iniciarse por sí sola desde segundo plano al conectar HDMI: esas decisiones pertenecen al sistema Samsung. Para probarla, abre LYGANE desde DeX.
- La actividad es redimensionable y conserva el proceso durante el cambio de pantalla mediante el metadato Samsung.

## Favoritos
- Táctil: mantén pulsada una app.
- Mando: mantén pulsado **OK** (o la tecla **Menú**), o usa el botón *Favorito* del hero.
- Se conservan los favoritos de la versión anterior.

## Compilar
- **GitHub Actions** (sin PC): sube la carpeta a un repositorio → *Actions → Build Android APK → Run workflow* → descarga `LYGANE-SmartTV-debug-apk`.
- **Android Studio**: abre la carpeta, espera la sincronización y *Build → Build APK(s)*.
- **Terminal**: `gradle :app:assembleDebug` (JDK 17, Android SDK 35).

## Notas
- Android limita la visibilidad de paquetes: solo aparecen apps con actividad `LAUNCHER` o `LEANBACK_LAUNCHER`.
- Algunos fabricantes de TV no permiten sustituir su launcher; en ese caso LYGANE funciona como app normal.
