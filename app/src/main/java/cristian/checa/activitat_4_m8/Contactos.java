package cristian.checa.activitat_4_m8;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Contactos extends AppCompatActivity {
    EditText Nom_espai;

    EditText Email_espai;
    Button Posar_contacte;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        Nom_espai = findViewById(R.id.nom_espai);
        Email_espai = findViewById(R.id.email_espai);
        Posar_contacte = findViewById(R.id.Posar_contacte);

        Posar_contacte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Nombre = Nom_espai.getText().toString().trim();
                String Email = Email_espai.getText().toString().trim();
                if (!Nombre.isEmpty() && !Email.isEmpty()) {
                    // Crear un intent con la acción ACTION_INSERT
                    Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);

                    // Establecer el tipo de contenido del intent
                    intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

                    // Establecer datos del contacto
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, Nombre);
                    intent.putExtra(ContactsContract.Intents.Insert.EMAIL, Email);

                    // Iniciar la actividad de inserción de contacto
                    startActivity(intent);
                    Toast.makeText(Contactos.this, "Contacto agregado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Contactos.this, "Por favor, completa el nombre y el email", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
