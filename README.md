# Proyecto final Multimedia

Este proyecto es una aplicación de Android que ofrece funcionalidades relacionadas con la gestión de productos y la autenticación de usuarios. Descripcion general de todo el proyecto: 

### Gestión de productos:

El proyecto incluye fragmentos para crear, leer, actualizar y eliminar productos (CRUD). Cada fragmento (Crear, Leer, Actualizar, Eliminar) está diseñado para realizar una operación específica en la base de datos de productos.
Se utiliza Retrofit, una biblioteca de Android para realizar solicitudes HTTP de manera sencilla, junto con Gson para la conversión de JSON a objetos Java. Esto sugiere que la aplicación se comunica con un servidor remoto para realizar operaciones CRUD en la base de datos de productos.

### Interfaz de usuario (UI):

La interfaz de usuario parece estar diseñada con Material Design, utilizando componentes proporcionados por la biblioteca de Material Design de Android.
Se utilizan diferentes tipos de vistas, como EditText, ListView, Button, TextView, etc., para interactuar con los productos y realizar operaciones CRUD.

### Autenticación de usuarios:

El proyecto incluye un fragmento para cerrar sesión, lo que hace que la aplicación admita la autenticación de usuarios.
Se utiliza Firebase Authentication para gestionar el proceso de inicio y cierre de sesión de los usuarios. También se utiliza Google Sign-In para autenticación con Google.


En resumen, este proyecto es una aplicación de Android que ofrece funcionalidades de gestión de productos y autenticación de usuarios utilizando tecnologías modernas como Retrofit para la comunicación con el servidor, Firebase para la autenticación y la base de datos en tiempo real, y bibliotecas de AndroidX para el desarrollo de la interfaz de usuario.


# Partes explicadas:

## Activity 1 (Login)
![image](https://github.com/anavarroo/AndroidApp/assets/117681310/cbcd0e11-b03a-4e72-86c9-3b510582acbc)

### Lógica

Clase login que extiende AppCompatActivity. Aquí se maneja la lógica de inicio de sesión, incluyendo la autenticación con Google, así como el inicio de sesión local con nombre de usuario y contraseña.

### XML

Este archivo XML define la interfaz de usuario para la pantalla de inicio de sesión. Contiene elementos como campos de texto para el nombre de usuario y la contraseña, un botón de inicio de sesión, y una opción para iniciar sesión con Google.


## Activity 2 (Menu y fragments)


### Lógica

Actividad llamada ContenedorFragmentActivity, que extiende AppCompatActivity. Esta actividad se encarga de manejar la navegación a través de diferentes fragmentos utilizando un BottomNavigationView.

### XML

Define la disposición de la interfaz de usuario para la actividad ContenedorFragmentActivity. Utiliza un ConstraintLayout como contenedor principal y contiene un FragmentContainerView para alojar los fragmentos de navegación y un BottomNavigationView para la navegación entre las secciones de la aplicación.


## Fragement 1 (Home)
![Alt text](image-1.png)

### Lógica

Define el fragmento llamado HomeFragment, que extiende la clase Fragment. Este fragmento se utiliza para mostrar una lista de productos obtenidos de un servicio web utilizando Retrofit para realizar llamadas HTTP. El método getAll() se utiliza para obtener todos los productos del servidor y luego se muestra en un ListView utilizando un adaptador personalizado.

### XML

Define la disposición de la interfaz de usuario para el fragmento HomeFragment. Utiliza un RelativeLayout como contenedor principal y contiene un ListView para mostrar la lista de productos.


## Fragement 2 (Crear producto)
![image](https://github.com/anavarroo/AndroidApp/assets/117681310/fcd3254f-76f6-46d6-afaf-072c60b5fa3e)


### Lógica

Define el fragmento llamado CrearFragment, que extiende la clase Fragment. El fragmento se utiliza para permitir al usuario crear un nuevo producto mediante la introducción de su nombre, descripción e imagen. Utiliza Retrofit para realizar una solicitud HTTP POST al servidor para crear el producto.

### XML

Define la disposición de la interfaz de usuario para el fragmento CrearFragment. Utiliza un RelativeLayout como contenedor principal y contiene elementos de interfaz de usuario como EditText para ingresar el nombre, la descripción y la URL de la imagen del producto, así como un botón para iniciar la creación del producto.


## Fragement 3 (Modificar producto)
![image](https://github.com/anavarroo/AndroidApp/assets/117681310/677e821e-dbe4-4462-a1c2-16f2fdd1592d)


### Lógica

Define el fragmento llamado ModificarFragment, que extiende la clase Fragment. El fragmento se utiliza para permitir al usuario modificar un producto existente mediante la introducción de su ID y los nuevos detalles del producto. Utiliza Retrofit para realizar una solicitud HTTP PUT al servidor para actualizar el producto.

### XML

Define la disposición de la interfaz de usuario para el fragmento ModificarFragment. Utiliza un RelativeLayout como contenedor principal y contiene elementos de interfaz de usuario como EditText para ingresar el ID del producto y los nuevos detalles, así como un botón para iniciar la modificación del producto.


## Fragement 4 (Eliminar producto)
![image](https://github.com/anavarroo/AndroidApp/assets/117681310/700f0ba4-1fc3-4093-82a0-3ca0d4bbbeaf)


### Lógica

Define el fragmento llamado EliminarFragment, que extiende la clase Fragment. El fragmento se utiliza para permitir al usuario eliminar un producto existente mediante la introducción de su ID. Utiliza Retrofit para realizar una solicitud HTTP DELETE al servidor para eliminar el producto.


### XML

Define la disposición de la interfaz de usuario para el fragmento EliminarFragment. Utiliza un RelativeLayout como contenedor principal y contiene elementos de interfaz de usuario como EditText para ingresar el ID del producto y un botón para iniciar la eliminación del producto.


## Fragement 5 (Salir)
![image](https://github.com/anavarroo/AndroidApp/assets/117681310/71930415-4a47-42d1-b29c-53c3b5c8c9b7)


### Lógica

Define el fragmento llamado SalirFragment, que extiende la clase Fragment. El fragmento se utiliza para permitir al usuario cerrar sesión. Cuando el botón "Cerrar Sesión" se presiona, se cierra la sesión tanto en Firebase como en Google Sign-In, y luego se redirige a la actividad de inicio de sesión.

### XML

Define la disposición de la interfaz de usuario para el fragmento SalirFragment. Utiliza un FrameLayout como contenedor principal y contiene un botón "Cerrar Sesión" dentro de un LinearLayout. Este botón permite al usuario cerrar sesión.


## Versiones
- compileSdk = 34: Define la versión del SDK de Android que se utilizará para compilar la aplicación.
- minSdk = 24: Especifica la versión mínima del SDK de Android que la aplicación admite.
- targetSdk = 33: Define la versión del SDK de Android hacia la cual la aplicación está dirigida.
- sourceCompatibility = JavaVersion.VERSION_1_8 
-  targetCompatibility = JavaVersion.VERSION_1_8: Establecen la compatibilidad de la fuente y el destino del código Java en la versión 1.8.

## Dependencias
- implementation("com.squareup.picasso:picasso:2.8"): Biblioteca para cargar y mostrar imágenes en la aplicación.
- implementation("com.squareup.retrofit2:retrofit:2.9.0"): Biblioteca para realizar solicitudes HTTP de manera sencilla en Android.
- implementation("androidx.fragment:fragment-ktx:1.6.2"): Biblioteca de AndroidX para trabajar con fragmentos de manera simplificada.
- implementation("androidx.appcompat:appcompat:1.6.1"): Biblioteca de AndroidX para proporcionar compatibilidad con versiones anteriores de Android.
- implementation("com.google.android.material:material:1.11.0"): Biblioteca de Material Design para componentes de interfaz de usuario.
- implementation("androidx.constraintlayout:constraintlayout:2.1.4"): Biblioteca de AndroidX para crear diseños de interfaz de usuario mediante restricciones.
- implementation("androidx.navigation:navigation-fragment:2.7.6"): Biblioteca de AndroidX para la navegación en la aplicación.
- implementation ("de.hdodenhof:circleimageview:3.1.0"): Biblioteca para mostrar imágenes circulares en la aplicación.
- implementation("com.google.firebase:firebase-database:20.3.0"): Biblioteca de Firebase para la integración de la base de datos en tiempo real.
- testImplementation("junit:junit:4.13.2"): Biblioteca de pruebas unitarias JUnit.
- androidTestImplementation("androidx.test.ext:junit:1.1.5") 
 y androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1"): Bibliotecas para pruebas instrumentadas en Android.
- implementation ("com.squareup.retrofit2:converter-gson:2.9.0"): Conversor Gson para Retrofit, que permite la conversión de JSON a objetos Java.
- implementation ("androidx.recyclerview:recyclerview:1.3.2"): Biblioteca de AndroidX para trabajar con listas y reciclaje de vistas.
- implementation(platform("com.google.firebase:firebase-bom:32.7.1")): BOM (Bill of Materials) de Firebase, que permite gestionar fácilmente las versiones de las dependencias de Firebase.
- implementation("com.google.firebase:firebase-auth"), 
- implementation("com.google.firebase:firebase-auth:22.3.1") y  implementation("com.google.android.gms:play-services-auth:20.7.0"): Dependencias de Firebase y Google Play Services para la autenticación de usuarios.
