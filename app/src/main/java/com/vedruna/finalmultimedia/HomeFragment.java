package com.vedruna.finalmultimedia;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.vedruna.finalmultimedia.adapters.ProductsAdapter;
import com.vedruna.finalmultimedia.interfaces.CRUDInterface;
import com.vedruna.finalmultimedia.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



/**
 * Fragmento que muestra la lista de productos.
 */
public class HomeFragment extends Fragment {
    List<Product> products;
    CRUDInterface crudInterface;

    ListView listView;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * Método llamado cuando se crea la vista del fragmento.
     *
     * @param inflater           El LayoutInflater utilizado para inflar la vista.
     * @param container          El ViewGroup en el que se debe inflar la vista.
     * @param savedInstanceState El estado previamente guardado del fragmento.
     * @return La vista asociada con el fragmento.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listView = view.findViewById(R.id.listView);
        getAll();
        return view;
    }


    /**
     * Método para obtener todos los productos del servidor y mostrarlos en el ListView.
     */
    private void getAll() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.42.1:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        crudInterface = retrofit.create(CRUDInterface.class);

        Call<List<Product>> call = crudInterface.getAll();
        call.enqueue(new Callback<List<Product>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (!response.isSuccessful()){
                    Log.e("Response err: ",response.message());

                    return;
                }
                products = response.body();
                ProductsAdapter productsAdapter= new ProductsAdapter(products, getActivity().getApplicationContext());
                listView.setAdapter(productsAdapter);
                // Imprimir los productos en el log
                products.forEach(p -> Log.i("Prods: ",p.toString()));
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                // Manejar errores en la solicitud
                Log.e("Throw err: ",t.getMessage());

            }
        });

    }
}