package com.vedruna.finalmultimedia;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.vedruna.finalmultimedia.interfaces.CRUDInterface;
import com.vedruna.finalmultimedia.model.Product;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Fragmento para eliminar un producto.
 */
public class EliminarFragment extends Fragment {
    private EditText productIdToDeleteEditText;
    private Button deleteProductButton;

    // Declara Retrofit en el ámbito de clase
    private Retrofit retrofit;


    /**
     * Método llamado para crear y devolver la vista asociada con el fragmento.
     *
     * @param inflater           El LayoutInflater utilizado para inflar la vista.
     * @param container          El ViewGroup en el que se debe inflar la vista.
     * @param savedInstanceState El estado previamente guardado del fragmento.
     * @return La vista asociada con el fragmento.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eliminar, container, false);

        // Inicializa las referencias a los elementos de la interfaz
        productIdToDeleteEditText = view.findViewById(R.id.productIdToDeleteEditText);
        deleteProductButton = view.findViewById(R.id.deleteProductButton);

        // Inicializa Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.42.1:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Asigna un listener al botón para iniciar la eliminación del producto
        deleteProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para eliminar un producto
                deleteProduct();
            }
        });

        return view;
    }


    /**
     * Método para eliminar un producto utilizando el ID proporcionado.
     */
    private void deleteProduct() {
        // Obtén el ID del producto a eliminar
        String productIdToDelete = productIdToDeleteEditText.getText().toString().trim();


        // Realiza la llamada para eliminar el producto
        CRUDInterface crudInterface = retrofit.create(CRUDInterface.class);
        Call<Void> call = crudInterface.deleteProduct(Long.parseLong(productIdToDelete));
        call.enqueue(new Callback<Void>()  {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Manejar la eliminación exitosa, si es necesario
                    Toast.makeText(getContext(), "Producto eliminado exitosamente", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("ErrorBody", errorBody);
                        // Otro manejo del error si es necesario
                        Toast.makeText(getContext(), "Error al eliminar producto", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Manejar el error de la solicitud, si es necesario
                Toast.makeText(getContext(), "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
