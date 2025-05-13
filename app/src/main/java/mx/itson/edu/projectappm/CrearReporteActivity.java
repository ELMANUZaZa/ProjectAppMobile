package mx.itson.edu.projectappm;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.Manifest;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

    private void enviarReporte(){
        String nombreTexto  = nombre.getText().toString();
        String coloniaSeleccionada  = colonia.getText().toString();
        String direccionTexto  = direccion.getText().toString();
        String celularTexto  = celular.getText().toString();
        String correoTexto  = correo.getText().toString();
        String tipoSeleccionado = spinerTipoReporte.getSelectedItem().toString();
        String descripçionTexto  = descripcion.getText().toString();

        Intent intent = new Intent(this, ConfirmacionActivity.class);
        intent.putExtra("nombre", nombreTexto);
        intent.putExtra("colonia", coloniaSeleccionada);
        intent.putExtra("direccion", direccionTexto);
        intent.putExtra("celular", celularTexto);
        intent.putExtra("correo", correoTexto);
        intent.putExtra("tipoReporte", tipoSeleccionado);
        intent.putExtra("descripcion", descripçionTexto);
        startActivity(intent);
    }


}