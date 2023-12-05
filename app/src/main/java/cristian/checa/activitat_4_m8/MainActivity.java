package cristian.checa.activitat_4_m8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button Crear_alarma;
Button Crear_Contacte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Crear_alarma = findViewById(R.id.Crear_alarma);
        Crear_alarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Alarma = new Intent(MainActivity.this,Alarma.class);
                startActivity(Alarma);
            }
        });
        Crear_Contacte = findViewById(R.id.Crear_Contacte);
        Crear_Contacte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Contactos = new Intent(MainActivity.this,Contactos.class);
                startActivity(Contactos);
            }
        });

    }
}