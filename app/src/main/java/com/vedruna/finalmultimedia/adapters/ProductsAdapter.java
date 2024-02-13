package com.vedruna.finalmultimedia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vedruna.finalmultimedia.R;
import com.vedruna.finalmultimedia.model.Product;

import java.util.List;

/**
 * Adaptador para mostrar una lista de productos en una ListView.
 */
public class ProductsAdapter extends BaseAdapter {

    List<Product> products;
    Context context;
    TextView nameText;
    TextView descText;



    /**
     * Constructor del adaptador.
     *
     * @param products La lista de productos a mostrar.
     * @param context  El contexto de la aplicación.
     */
    public ProductsAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return products.get(i).getId();
    }


    /**
     * Devuelve una vista que representa un elemento de la lista en la posición especificada.
     *
     * @param position  La posición del elemento en la lista de datos.
     * @param view      La vista actualmente en uso o null si no hay una vista disponible.
     * @param viewGroup El grupo al que pertenece la vista.
     * @return La vista que representa el elemento en la posición especificada.
     */
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            // Inflar la vista si no está disponible
            view = LayoutInflater.from(context).inflate(R.layout.product_list, viewGroup, false);
        }

        // Obtener referencias a los elementos de la vista
        TextView idText = view.findViewById(R.id.idText);
        nameText = view.findViewById(R.id.nameText);
        descText = view.findViewById(R.id.descText);
        ImageView imgView = view.findViewById(R.id.imgView);

        // Configurar los valores de los elementos de la vista con los datos del producto en la posición actual
        idText.setText(products.get(position).getId() + ", "); // Mostrar el ID
        nameText.setText(products.get(position).getProductname());
        descText.setText(products.get(position).getDesc());

        // Cargar la imagen con Picasso si la URL de la imagen está disponible
        String imgUrl = products.get(position).getImg();
        if (imgUrl != null && !imgUrl.isEmpty()) {
            float borderRadius = 25f; // Ajusta el radio según tus preferencias
            Picasso.get().load(imgUrl)
                    .placeholder(R.drawable.ic_home)
                    .error(R.drawable.ic_delete)
                    .transform(new RoundRectTransformation(borderRadius)) // Aplicar la transformación de bordes redondos
                    .into(imgView);
        } else {
            // Si no hay URL de imagen disponible, mostrar una imagen predeterminada
            imgView.setImageResource(R.drawable.ic_delete);
        }

        return view;
    }
}
