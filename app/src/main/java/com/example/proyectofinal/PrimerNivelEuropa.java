package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PrimerNivelEuropa extends AppCompatActivity {

    private TextView tv_nombre, tv_score, respuesta;
    private RadioButton rb_opcion1, rb_opcion2, rb_opcion3;
    private ImageView iv_banderas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primer_nivel_europa);
        tv_nombre = (TextView) findViewById(R.id.tv_nombre);
        rb_opcion1 = (RadioButton) findViewById(R.id.rb_opcion1);
        rb_opcion2 = (RadioButton) findViewById(R.id.rb_opcion2);
        rb_opcion3 = (RadioButton) findViewById(R.id.rb_opcion3);
        iv_banderas = (ImageView) findViewById(R.id.iv_banderas);
        respuesta = (TextView) findViewById(R.id.tv_invisible);
// traer el nombre de la activity principal
        String visados= "visados";

        String viajero = getIntent().getStringExtra("nombre");
        tv_nombre.setText(viajero);

// hacer muestre imagen aleatoria de banderas

//meter en el mismo orden
        ArrayList<String> europa = new ArrayList<String>();
        int europa_bandera[] = {R.drawable.polonia,R.drawable.monaco,R.drawable.dinamarca,R.drawable.noruega,R.drawable.suiza};
        europa.add("Polonia");
        europa.add("Monaco");
        europa.add("Dinamarca");
        europa.add("Noruega");
        europa.add("Suiza");

        int n_bandera = numeroAleatorio("pais", europa.size());

        ArrayList<Integer> elegido = new ArrayList<Integer>();
        elegido.add(n_bandera);

        //añado las otras opciones

        int i = 1;
        while (i < 3) {
            n_bandera = numeroAleatorio("pais", europa.size());
            if (!elegido.contains(n_bandera)) {
                elegido.add(n_bandera);
                i++;
            }
        }

        iv_banderas.setImageResource(europa_bandera[elegido.get(0)]);
        respuesta.setText(europa.get(0));

        int posicion_rb = numeroAleatorio("rb", 3);

        if (posicion_rb == 1) {
            rb_opcion1.setText(europa.get(elegido.get(0)));
            rb_opcion2.setText(europa.get(elegido.get(1)));
            rb_opcion3.setText(europa.get(elegido.get(2)));

        } else if (posicion_rb == 2) {
            rb_opcion1.setText(europa.get(elegido.get(1)));
            rb_opcion2.setText(europa.get(elegido.get(0)));
            rb_opcion3.setText(europa.get(elegido.get(2)));

        } else if (posicion_rb == 3) {
            rb_opcion1.setText(europa.get(elegido.get(2)));
            rb_opcion2.setText(europa.get(elegido.get(1)));
            rb_opcion3.setText(europa.get(elegido.get(0)));

        }
    }

    public static int numeroAleatorio(String tipo, int size){
        int numero = 0;

        if (tipo.equals("rb")) {
            numero = (int) (Math.random() * size + 1);
        } else {
            numero = (int) ((Math.random()) * size);
        }
        return numero;
    }



    public void comprobar(View view){
        String correcta= respuesta.getText().toString();
        String seleccion="";

        if(rb_opcion1.isChecked()){
            seleccion= rb_opcion1.getText().toString();
        }
        if(rb_opcion2.isChecked()){
            seleccion=rb_opcion2.getText().toString();
        }
        if (rb_opcion3.isChecked()){
            seleccion=rb_opcion3.getText().toString();
        }


        if(correcta.equals(seleccion)){
            Toast.makeText(this, "Respuesta correcta", Toast.LENGTH_SHORT).show();
            Intent next= new Intent(this,PrimerNivelEuropa.class);
            startActivity(next);



        }else{
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }
    }



}