package com.example.faltei;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_add;
    private Button btn_panorama;
    private Button btn_edita;
    private Button btn_apaga;
    private Button btn_internet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_add = (Button) findViewById(R.id.addXML);
        btn_panorama = (Button) findViewById(R.id.panoramaXML);
        btn_edita = (Button) findViewById(R.id.editarXML);
        btn_apaga = (Button) findViewById(R.id.apagarXML);
        btn_internet = (Button) findViewById(R.id.internetXML);

        btn_add.setOnClickListener(v -> {
            Intent it = new Intent(MainActivity.this, Add.class);
            startActivity(it);
        });

        btn_panorama.setOnClickListener(view -> {
            Intent it = new Intent(MainActivity.this, Panorama.class);
            startActivity(it);
        });

        btn_edita.setOnClickListener(view -> {
            Intent it = new Intent(MainActivity.this, Edit.class);
            startActivity(it);
        });

        btn_apaga.setOnClickListener(view -> {
            Intent it = new Intent(MainActivity.this, Delete.class);
            startActivity(it);
        });

        btn_internet.setOnClickListener(view -> goLink("https://www.dac.unicamp.br/portal/"));
    }

    private void goLink(String dac){
        Uri uri = Uri.parse(dac);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    private void onClick(View view) {
        Intent it = new Intent(MainActivity.this, Add.class);
        startActivity(it);
    }
}