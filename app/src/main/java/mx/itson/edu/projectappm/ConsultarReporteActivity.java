package mx.itson.edu.projectappm;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import api.ReportApi;
import models.Reporte;
import models.ReporteAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConsultarReporteActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_reporte);

        // Obtener datos del Intent
        String nombre = getIntent().getStringExtra("nombre");
        String direccion = getIntent().getStringExtra("direccion");
        String imagenBase64 = getIntent().getStringExtra("imagen");

        // Obtener referencias de la UI
        TextView nombreTextView = findViewById(R.id.tv_nombreTextView);
        TextView direccionTextView = findViewById(R.id.tv_direccionTextView);
        ImageView imagenView = findViewById(R.id.iv_imagenView);

        Log.d("DEBUG", "Nombre: " + nombre);
        Log.d("DEBUG", "Direccion: " + direccion);
        Log.d("DEBUG", "Imagen (Base64): " + imagenBase64);

        nombreTextView.setText(nombre);
        direccionTextView.setText(direccion);


        if (imagenBase64 != null && !imagenBase64.isEmpty()) {
            byte[] decodedString = Base64.decode(imagenBase64, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imagenView.setImageBitmap(decodedByte); // Aquí corregido el método
        }
    }








}