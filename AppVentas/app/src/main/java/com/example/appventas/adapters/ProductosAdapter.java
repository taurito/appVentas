package com.example.appventas.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appventas.R;
import com.example.appventas.model.Producto;

import java.util.List;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ProductoViewHolder>{

    private List<Producto> productoList;

    public ProductosAdapter(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        Producto producto = productoList.get(position);
        holder.nombreTextView.setText(producto.getNombre());
        // Puedes agregar más detalles como el precio si es necesario
    }

    @Override
    public int getItemCount() {
        return productoList.size();
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView;


        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
        }
    }
}
