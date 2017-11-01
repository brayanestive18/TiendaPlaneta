package com.brayadiaz.tiendaplaneta;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class ListSQL extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ContactosSQLITEHelper contactosSQLiteHelper;
    SQLiteDatabase dbContactos;
    RecyclerView recyclerView;
    ArrayList<User> usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list_sqlite);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.frame);
        getLayoutInflater().inflate(R.layout.activity_list_sql, contentFrameLayout);

        contactosSQLiteHelper = new ContactosSQLITEHelper(this, "Libro", null, 1);
        dbContactos = contactosSQLiteHelper.getWritableDatabase();

        usersList = new ArrayList<User>();

        Cursor c = dbContactos.rawQuery("SELECT * FROM contactos",null);
        if (c.moveToFirst()){
            do {
                User user = new User(c.getString(1), c.getString(3),
                        c.getString(2), String.valueOf(c.getInt(0)),
                        c.getString(4));
                usersList.add(user);
            } while(c.moveToNext());
        } else {
            Toast.makeText(this, "No hay contactos", Toast.LENGTH_SHORT).show();
        }

        ContactosAdapter contactosAdapter = new ContactosAdapter(this, usersList);
        recyclerView = (RecyclerView) findViewById(R.id.recicler);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(contactosAdapter);

    }
}
