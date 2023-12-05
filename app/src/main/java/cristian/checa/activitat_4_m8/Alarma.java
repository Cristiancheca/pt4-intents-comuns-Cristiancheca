package cristian.checa.activitat_4_m8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Alarma extends AppCompatActivity {
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarma);

        Button Poner_alarma = findViewById(R.id.Poner_alarma);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(Alarma.this, RecibirAlarma.class);
        alarmIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        Poner_alarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText Hores = findViewById(R.id.Hores);
                EditText minuts = findViewById(R.id.minuts);

                String hora = Hores.getText().toString();
                String minuto = minuts.getText().toString();

                String mensaje = "Alarma programada para las " + hora + ":" + minuto;


                programarAlarma(Integer.parseInt(hora), Integer.parseInt(minuto));
            }
        });
    }


    private void programarAlarma(int hora, int minuto) {
        long tiempoAlarma = calcularTiempoAlarma(hora, minuto);

        // Verificar permisos
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(Alarma.this, android.Manifest.permission.SET_ALARM) == PackageManager.PERMISSION_GRANTED) {
                // Permiso otorgado programar la alarma
                alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, tiempoAlarma, alarmIntent);

                // Utilizar Intent implicito para abrir la aplicacion de reloj/alarma del sistema
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                        .putExtra(AlarmClock.EXTRA_HOUR, hora)
                        .putExtra(AlarmClock.EXTRA_MINUTES, minuto)
                        .putExtra(AlarmClock.EXTRA_SKIP_UI, true);

                startActivity(intent);
            } else {
                // Permiso no otorgado, solicitar permisos al usuario
                ActivityCompat.requestPermissions(Alarma.this, new String[]{Manifest.permission.SET_ALARM}, 1);
            }
        }
        // Si la versión de Android es anterior no es necesario verificar permisos
        else {
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, tiempoAlarma, alarmIntent);

            // Utilizar Intent implicito para abrir la aplicacion de reloj/alarma del sistema
            Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                    .putExtra(AlarmClock.EXTRA_HOUR, hora)
                    .putExtra(AlarmClock.EXTRA_MINUTES, minuto)
                    .putExtra(AlarmClock.EXTRA_SKIP_UI, true);

            startActivity(intent);
        }
    }

    private long calcularTiempoAlarma(int hora, int minuto) {
        long tiempoActual = SystemClock.elapsedRealtime();
        return tiempoActual + (hora * 60 * 60 * 1000) + (minuto * 60 * 1000);
    }
}