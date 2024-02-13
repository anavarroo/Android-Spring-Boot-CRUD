package com.vedruna.finalmultimedia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.vedruna.finalmultimedia.interfaces.CRUDInterface;
import com.vedruna.finalmultimedia.model.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Fragmento para modificar un producto.
 */
public class ModificarFragment extends Fragment {
    private EditText productIdToUpdateEditText;
    private EditText updatedProductNameEditText;
    private EditText updatedProductDescEditText;
    private EditText updatedProductImgEditText;
    private Button updateProductButton;

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
        View view = inflater.inflate(R.layout.fragment_modificar, container, false);

        // Inicializa las referencias a los elementos de la interfaz
        productIdToUpdateEditText = view.findViewById(R.id.productIdToUpdateEditText);
        updatedProductNameEditText = view.findViewById(R.id.updatedProductNameEditText);
        updatedProductDescEditText = view.findViewById(R.id.updatedProductDescEditText);
        updatedProductImgEditText = view.findViewById(R.id.updatedProductImgEditText);
        updateProductButton = view.findViewById(R.id.updateProductButton);

        // Inicializa Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.42.1:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Asigna un listener al botón para iniciar la actualización del producto
        updateProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para actualizar un producto
                updateProduct();
            }
        });

        return view;
    }

    /**
     * Método para actualizar un producto utilizando los datos ingresados por el usuario.
     */
    private void updateProduct() {
        // Obtén los valores ingresados por el usuario
        String productIdToUpdate = productIdToUpdateEditText.getText().toString().trim();
        String updatedProductName = updatedProductNameEditText.getText().toString().trim();
        String updatedProductDesc = updatedProductDescEditText.getText().toString().trim();
        String updatedProductImg = updatedProductImgEditText.getText().toString().trim();


        // Crea un nuevo objeto Product con los datos actualizados
        Product updatedProduct = new Product();
        updatedProduct.setId(Long.parseLong(productIdToUpdate));
        updatedProduct.setProductname(updatedProductName);
        updatedProduct.setDesc(updatedProductDesc);
        updatedProduct.setImg(updatedProductImg);


        // Realiza la llamada para actualizar el producto
        CRUDInterface crudInterface = retrofit.create(CRUDInterface.class);
        Call<Void> call = crudInterface.updateProduct(updatedProduct);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Manejar la actualización exitosa, si es necesario
                    Toast.makeText(getContext(), "Producto actualizado exitosamente", Toast.LENGTH_SHORT).show();
                } else {
                    // Manejar el error de actualización, si es necesario
                    Toast.makeText(getContext(), "Error al actualizar producto", Toast.LENGTH_SHORT).show();
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
