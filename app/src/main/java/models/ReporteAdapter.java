package models;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import models.Reporte;
import mx.itson.edu.projectappm.R;

public class ReporteAdapter extends RecyclerView.Adapter<ReporteAdapter.ViewHolder> {

    private final List<Reporte> reportes;

    public ReporteAdapter(List<Reporte> reportes) {
        this.reportes = reportes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reporte, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Reporte reporte = reportes.get(position);
        holder.nombre.setText(reporte.getNombreInteresado());
        holder.tipo.setText(reporte.getTipo());
        holder.descripcion.setText(reporte.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return reportes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, tipo, descripcion;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvNombre);
            tipo = itemView.findViewById(R.id.tvTipo);
            descripcion = itemView.findViewById(R.id.tvDescripcion);
        }
    }
}