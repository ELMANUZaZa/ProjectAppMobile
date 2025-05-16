package mx.itson.edu.projectappm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ContactoActivity extends AppCompatActivity {

    private Button mapa, correo, llamar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        mapa = findViewById(R.id.btn_mapa);
        correo = findViewById(R.id.btn_correo);
        llamar = findViewById(R.id.btn_llamar);

        mapa.setOnClickListener(v -> {
            Uri uri = Uri.parse("geo:0,0?q=Calle+Niños+Héroes+s/n,+Moderna,+85330+Empalme,+Son.");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            startActivity(intent);
        });

        correo.setOnClickListener(v ->{
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto: atencionciudadanaempalme@gmail.com"));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Consulta");
            startActivity(intent);
        });

        llamar.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel: +52 622 124 3081"));
            startActivity(intent);
        });

    }
}