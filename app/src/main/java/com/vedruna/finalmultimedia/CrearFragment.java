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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Fragmento para crear un nuevo producto.
 */
public class CrearFragment extends Fragment {
    private EditText productNameEditText;
    private EditText productDescEditText;
    private EditText productImgEditText;
    private Button createProductButton;

    private Retrofit retrofit;

    // Otros atributos y métodos según sea necesario


    // Otros atributos y métodos según sea necesario

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
        View view = inflater.inflate(R.layout.fragment_crear, container, false);

        // Inicializa las referencias a los elementos de la interfaz
        productNameEditText = view.findViewById(R.id.productNameEditText);
        productDescEditText = view.findViewById(R.id.productDescEditText);
        productImgEditText = view.findViewById(R.id.productImgEditText);
        createProductButton = view.findViewById(R.id.createProductButton);


        // Inicializa Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.42.1:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Asigna un listener al botón para iniciar la creación del producto
        createProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para crear un nuevo producto
                createProduct();
            }
        });



        return view;
    }


    /**
     * Método para crear un nuevo producto utilizando los datos ingresados por el usuario.
     */
    private void createProduct() {
        // Obtén los valores ingresados por el usuario
        String productName = productNameEditText.getText().toString().trim();
        String productDesc = productDescEditText.getText().toString().trim();
        String productImg = productImgEditText.getText().toString().trim();

        // Crea un nuevo objeto Product con los datos ingresados
        Product newProduct = new Product(null, productName, productDesc, productImg);

        // Crea una instancia de la interfaz CRUDInterface utilizando Retrofit
        CRUDInterface crudInterface = retrofit.create(CRUDInterface.class);

        // Realiza una llamada para crear el producto en el servidor
        Call<Void> call = crudInterface.createProduct(newProduct);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Manejar la creacion exitosa, si es necesario
                    Toast.makeText(getContext(), "Producto creado exitosamente", Toast.LENGTH_SHORT).show();
                } else {
                    // Manejar el error de creación, si es necesario
                    Toast.makeText(getContext(), "Error al crear producto", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Manejar el error de la solicitud, si es necesario
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}