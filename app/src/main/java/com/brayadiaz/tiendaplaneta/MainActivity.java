package com.brayadiaz.tiendaplaneta;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    EditText eID, eNameBook, eNameAutor, eNameUser, ePhone;
    Button bPrestar, bUpdate, bRead, bDelete;

    ContactosSQLITEHelper contactosSQLITEHelper;
    SQLiteDatabase dbContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.frame);
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);

        //eID = (EditText) findViewById(R.id.eID);
        eNameBook = (EditText) findViewById(R.id.eNameBook);
        eNameAutor = (EditText) findViewById(R.id.eNameAuthor);
        eNameUser = (EditText) findViewById(R.id.eNameUser);
        ePhone = (EditText) findViewById(R.id.ePhone);
        bPrestar = (Button) findViewById(R.id.bPrestar);
        bUpdate = (Button) findViewById(R.id.bUpdate);
        bRead = (Button) findViewById(R.id.bRead);
        bDelete = (Button) findViewById(R.id.bDelete);

        contactosSQLITEHelper = new ContactosSQLITEHelper(this, "Libro", null, 1);
        dbContactos = contactosSQLITEHelper.getWritableDatabase();

    }

    public void onClick(View view) {

        int id = view.getId();
        final String nameBook, nameAutor, nameUser, phone, uuid;

        nameBook = eNameBook.getText().toString();
        nameAutor = eNameAutor.getText().toString();
        nameUser = eNameUser.getText().toString();
        phone = ePhone.getText().toString();

        ContentValues data = new ContentValues();

        switch (id){
            case R.id.bPrestar:

                //ContentValues data = new ContentValues();
                data.put("name",nameBook);
                data.put("autor",nameAutor);
                data.put("presta",nameUser);
                data.put("phone",phone);

                dbContactos.insert("contactos", null,data);
                clean();

                break;

            case R.id.bRead:

                Cursor c = dbContactos.rawQuery("SELECT * FROM contactos WHERE name='"+nameBook+"'",null);
                if (c.moveToFirst()){
                    eNameAutor.setText(c.getString(2));
                    eNameUser.setText(c.getString(3));
                    ePhone.setText(c.getString(4));
                } else {
                    Toast.makeText(this, "No hay reservas", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.bUpdate:

                //ContentValues data = new ContentValues();
                data.put("autor",nameAutor);
                data.put("presta",nameUser);
                data.put("phone",phone);


                dbContactos.update("contactos", data, "name = '"+nameBook+"'",null);
                clean();

                break;

            case R.id.bDelete:

                dbContactos.delete("contactos", "name = '"+nameBook+"'",null);
                clean();

                break;
        }

    }

    private void clean() {

        eNameBook.setText("");
        eNameAutor.setText("");
        eNameUser.setText("");
        ePhone.setText("");

    }

}
