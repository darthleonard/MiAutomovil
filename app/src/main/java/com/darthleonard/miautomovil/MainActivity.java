package com.darthleonard.miautomovil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import java.sql.Date;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvFecha, tvPrecio, tvLitros, tvKm;
    private Tabla tabla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvFecha = (TextView) findViewById(R.id.tvFecha);
        tvFecha.setText(new Date(System.currentTimeMillis()).toString());
        tvPrecio = (TextView) findViewById(R.id.tvPrecio);
        tvLitros = (TextView) findViewById(R.id.tvLtros);
        tvKm = (TextView) findViewById(R.id.tvKm);

        Button btnRegistrar = (Button) findViewById(R.id.btnRegistrarFecha);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence fecha = new Date(System.currentTimeMillis()).toString();
                DBManager r = new DBManager(getApplicationContext());
                Registro registro = new Registro(
                        tvFecha.getText().toString(),
                        tvPrecio.getText().toString(),
                        tvLitros.getText().toString(),
                        tvKm.getText().toString()
                );
                r.insertaRegistro(registro);
                actualizaTabla();
            }
        });

        tabla = new Tabla(this, (TableLayout)findViewById(R.id.tabla), R.array.cabecera_tabla);
        tabla.agregarCabecera();

        actualizaTabla();
    }

    private void actualizaTabla() {
        tabla.eliminarFilasTabla();
        DBManager r = new DBManager(getApplicationContext());

        ArrayList<Registro> registro = r.recuperarRegistros();
        for (int i = 0; i < registro.size(); i++) {
            ArrayList<String> elementos = new ArrayList<String>();
            elementos.add(Integer.toString(registro.get(i).getId()));
            elementos.add(registro.get(i).getFecha());
            elementos.add(registro.get(i).getPrecio());
            elementos.add(registro.get(i).getLitros());
            elementos.add(registro.get(i).getKilometros());
            tabla.agregarFilaTabla(elementos);
        }
    }
}
