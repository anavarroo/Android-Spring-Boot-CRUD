package com.vedruna.finalmultimedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;



/**
 * Esta clase representa la actividad principal que contiene fragmentos navegables.
 */
public class ContenedorFragmentActivity extends AppCompatActivity {



    /**
     * Se llama cuando se crea la actividad.
     *
     * @param savedInstanceState La instancia previamente guardada del estado de esta actividad, si está disponible.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contenedorfragmentactivity);


        // Obtener la referencia al BottomNavigationView y seleccionar la opción de inicio
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        // Obtener el NavController del NavHostFragment para gestionar la navegación entre fragmentos
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();

        // Configurar un listener para el BottomNavigationView para navegar entre fragmentos
        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.navigation_home){
                navController.navigate(R.id.homeFragment);
            } else if (item.getItemId() == R.id.navigation_crear){
                navController.navigate(R.id.crearFragment);
            } else if (item.getItemId() == R.id.navigation_modificar) {
                navController.navigate(R.id.modificarFragment);
            } else if (item.getItemId() == R.id.navigation_eliminar){
                navController.navigate(R.id.eliminarFragment);
            } else if (item.getItemId() == R.id.navigation_salir){
                navController.navigate(R.id.salirFragment);
            }


            return true;



        });

        // Después de que el usuario ha iniciado sesión correctamente, muestra el Toast
        showToast("Logueado Correctamente");

    }

    /**
     * Método para mostrar un Toast con un mensaje específico.
     *
     * @param message El mensaje a mostrar en el Toast.
     */
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}