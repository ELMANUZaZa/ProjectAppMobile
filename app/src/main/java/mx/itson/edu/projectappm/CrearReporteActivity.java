package mx.itson.edu.projectappm;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.Manifest;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;

import api.ReportApi;
import models.Reporte;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrearReporteActivity extends AppCompatActivity {

    private EditText nombre, direccion, celular, correo, descripcion;
    private AutoCompleteTextView colonia;
    private Spinner spinerTipoReporte;
    private ImageView imagen;
    private Button enviar;
    private final String[] colonias = {
            "100 CASAS", "13 DE JULIO", "18 DE NOVIEMBRE", "22 DE NOVIEMBRE", "23 DE MARZO", "23 DE NOVIEMBRE",
            "29 DE NOVIEMBRE", "5 DE MAYO", "ACACIAS", "ADOLFO DE LA HUERTA", "ADOLFO LÓPEZ MATEOS", "AEROCOMANDER",
            "AEROPUERTO", "ALAMO", "AMÉRICAS", "AMPLIACIÓN BURÓCRATA", "AMPLIACIÓN GIL SAMANIEGO", "AMPLIACIÓN GOLFO DE CALIFORNIA",
            "AMPLIACIÓN GUADALUPE", "AMPLIACIÓN INDEPENDENCIA", "AMPLIACIÓN MIGUEL HIDALGO", "ANTENA", "ANTORCHISTA", "ARANJUÉZ",
            "ARCOS", "ARRECIFES", "ATARDECERES", "AURORA", "BÁCUM", "BAHÍA", "BAUGO", "BAUTECAS", "BELEM", "BELLAVISTA",
            "BICENTENARIO", "BRISAS", "BUENOS AIRES", "BUGAMBILIA", "BURÓCRATA", "CALICHI", "CAMPESTRE", "CAMPO DE TIRO",
            "CAMPO NUEVO", "CANTERA", "CARACÓL PENINSULA", "CARACÓL TURÍSTICO", "CARLOS ROMERO D", "CASAS BLANCAS", "CASTILLO",
            "CENTENARIO", "CENTINELA", "CENTRO", "CERRO GANDAREÑO", "CHOYA", "CHUMAMPACO", "COLINAS", "COLINAS DE MIRAMAR",
            "COLINAS DEL SOL", "COLONIA CENTRO", "COSTA AZUL", "COUNTRY CLUB", "CRESTÓN", "CUADRITA", "DELICIAS", "DIAMANTE",
            "DORADO", "EJIDO ÁLVARO OBREGÓN", "EJIDO FELIPE ÁNGELES", "EJIDO GRACIANO SÁNCHEZ", "EJIDO LAZARO CARDENAS",
            "EJIDO MARIANO ESCOBEDO", "EJIDO NUEVO SAN FRANCISCO", "EJIDO PROFESOR GRACIANO SÁNCHEZ", "EJIDO SAN FERNANDO",
            "EJIDO SANTA MARÍA", "EJIDO SONORA", "EMILIANO ZAPATA", "EMPALME", "ESTEBAN BACA CALDERÓN", "FÁTIMA", "FEMOSA",
            "FLORES", "FOVISSSTE", "FRANCISCO MÁRQUEZ", "FUENTE DE PIEDRA", "FUENTES", "FUENTES RODRÍGUEZ", "GIL SAMANIEGO",
            "GIL SAMANIEGO 2", "GOLFO DE CALIFORNIA", "GOLONDRINAS", "GUADALUPE", "GUADALUPE VICTORIA", "GUARIDA DEL TIGRE",
            "GUASIMAS", "GUAYMAS CENTRO", "GUAYMAS NORTE", "HUIRIBIS", "HUIRIVIS", "HUMBERTO GUTIÉRREZ", "INDEPENDENCIA",
            "INFONAVIT", "JACINTO LÓPEZ", "JARDÍNES", "JUAN FRANCISCO PATRÓN MÁRQUEZ", "JUNTAS", "LINDAVISTA", "LOMA BONITA",
            "LOMA DORADA", "LOMA LINDA", "LOMAS DE COLOSIO", "LOMAS DE CORTÉS", "LOMAS DE FÁTIMA", "LOMAS DE MIRAMAR",
            "LOMAS DE SAN CARLOS", "LOMAS DEL GANDAREÑO", "LOMAS DEL MAR", "LOMAS MIRAMAR", "LÓPEZ MATEOS", "LOS LAGOS",
            "LUIS DONALDO COLOSIO", "MALECÓN", "MANGA SAN CARLOS", "MANUEL R BOBADILLA", "MAR DE CORTÉS", "MARIANA",
            "MARSELLA", "MICROONDAS", "MIGUEL HIDALGO", "MIRADOR", "MIRAMAR", "MISA", "MISIÓN DEL SOL", "MISIONEROS",
            "MONTE BELLO", "MONTECARLO", "MONTELOLITA", "MORENO", "MURALLA", "NICOLÁS BRAVO", "NIZA", "NUEVO HORIZONTE",
            "NUEVO PEÑASCO", "OCOTILLO", "OCOTILLO 2", "OROZ", "ORTÍZ", "PALMAS", "PALO VERDE", "PARAJE VIEJO",
            "PARQUE INDUSTRIAL", "PEDREGAL", "PEDREGAL DE QUIROGA", "PENÍNSULA", "PERIODISTA", "PERLA MARINA",
            "PESQUERO", "PETROLERA", "PINOS", "PLAYA DE CORTÉS", "PLAYA DE MIRAMAR", "POPULAR", "POTAM", "PRADERA DORADA",
            "PUEBLO DE BELÉM", "PUNTA ARENA", "QUINTAS", "RANCHITO CAMPESTRE", "RASTRO", "REAL DE CORTÉS", "RENACIMIENTO",
            "RINCÓN DE FÁTIMA", "ROBLE", "SAHUARAL", "SAN CARLOS NUEVO GUAYMAS", "SAN JOSÉ", "SAN MARINO", "SAN VICENTE",
            "SANTA CLARA", "SANTA FE", "SIN DOMICILIO FIJO", "SONORA", "TETABIATE", "VALLE BONITO", "VALLE DEL MAR",
            "VICAM", "VILLA SOFIA", "VILLAS DE MIRAMAR", "VILLAS DEL PARQUE", "VISTA AZUL", "VISTA DORADA", "YAQUI",
            "YUCATÁN"
    };

    private final String[] tiposReporte = {
            "ALUMBRADO PÚBLICO",
            "ANIMALES CALLEJEROS O EN SITUACIÓN DE ABANDONO",
            "BACHES",
            "BASURA O ESCOMBRO",
            "FUMIGACIÓN O PLAGAS",
            "FUGAS O DRENAJE",
            "OTRO ASUNTO"
    };

    private ActivityResultLauncher<Intent> camaraLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_reporte);

        nombre = findViewById(R.id.et_nombre);
        colonia = findViewById(R.id.et_colonia);
        direccion = findViewById(R.id.et_direccion);
        celular = findViewById(R.id.et_celular);
        correo = findViewById(R.id.et_correo);
        spinerTipoReporte = findViewById(R.id.spinner_tipoReporte);
        descripcion = findViewById(R.id.et_descptcion);
        imagen = findViewById(R.id.iv_imagen);
        enviar = findViewById(R.id.btn_enviar);

        ArrayAdapter<String> adapterColonias = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, colonias);
        colonia.setAdapter(adapterColonias);

        ArrayAdapter<String> adapterReporte = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, tiposReporte);
        adapterReporte.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerTipoReporte.setAdapter(adapterReporte);

        solicitarPermisos();

        camaraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK && result.getData() != null){
                        Bitmap foto = (Bitmap) result.getData().getExtras().get("data");
                        imagen.setImageBitmap(foto);

                    }
                });

        imagen.setOnClickListener(v -> abrirCamara());

        enviar.setOnClickListener(v -> enviarReporte());

    }

    private void solicitarPermisos(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 100);

        }
    }

    private void abrirCamara(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        camaraLauncher.launch(intent);
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://mcaconsultores.com.mx")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ReportApi api = retrofit.create(ReportApi.class);


    private void enviarReporte() {
        String nombreTexto  = nombre.getText().toString();
        String coloniaSeleccionada  = colonia.getText().toString();
        String direccionTexto  = direccion.getText().toString();
        String celularTexto  = celular.getText().toString();
        String correoTexto  = correo.getText().toString();
        String tipoSeleccionado = spinerTipoReporte.getSelectedItem().toString();
        String descripcionTexto  = descripcion.getText().toString();
        String imagenUrl = imagen.toString();

        if (nombreTexto.isEmpty() || coloniaSeleccionada.isEmpty() || direccionTexto.isEmpty() ||
                celularTexto.isEmpty() || correoTexto.isEmpty() || descripcionTexto.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }



        Reporte nuevoReporte = new Reporte();
        nuevoReporte.setNombreInteresado(nombreTexto);
        nuevoReporte.setDireccion(direccionTexto);
        nuevoReporte.setColonia(coloniaSeleccionada);
        nuevoReporte.setCelular(celularTexto);
        nuevoReporte.setCorreo(correoTexto);
        nuevoReporte.setTipo(tipoSeleccionado);
        nuevoReporte.setDescripcion(descripcionTexto);
        ImageView imagenView = findViewById(R.id.iv_imagen);
        Bitmap bitmap = ((BitmapDrawable) imagenView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] byteArray = baos.toByteArray();
        String imagenBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);

        nuevoReporte.setImagenUrl(imagenBase64);




        String token = "Bearer a0f4dcad-5903-482f-8982-88ec8bc6156e";
        Call<Reporte> call = api.enviarReporte(token, nuevoReporte);
        call.enqueue(new Callback<Reporte>() {
            @Override
            public void onResponse(Call<Reporte> call, Response<Reporte> response) {
                if (response.code() == 401) {
                    Log.e("API", "Error 401: No autorizado. Revisa el token de autenticación.");
                } else if (response.isSuccessful()) {
                    Log.d("API", "Reporte enviado correctamente.");

                    Log.d("DEBUG", "Texto de dirección antes de asignar: " + direccionTexto);
                    Log.d("DEBUG", "URL de imagen antes de asignar: " + imagenUrl);



                    Toast.makeText(CrearReporteActivity.this, "Reporte enviado", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(CrearReporteActivity.this, ConsultarReporteActivity.class);
                    intent.putExtra("nombre", nuevoReporte.getNombreInteresado());
                    intent.putExtra("direccion", nuevoReporte.getDireccion());
                    intent.putExtra("imagen", nuevoReporte.getImagenUrl());
                    startActivity(intent);
                    finish();
                } else {
                    Log.e("API", "Error al enviar reporte: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Reporte> call, Throwable t) {
                Log.e("API", "Fallo en la conexión: " + t.getMessage());
            }
        });
    }
}