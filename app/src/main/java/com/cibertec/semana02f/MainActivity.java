package com.cibertec.semana02f;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cibertec.semana02f.entity.User;
import com.cibertec.semana02f.service.UserService;
import com.cibertec.semana02f.util.Connection;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //Necesita Adapter y una lista
    private Spinner spnUsuario;
    private ArrayAdapter<String> adaptador;
    private List<String> lstNombreUsuario = new ArrayList<String>();

    //Una lista de los datos completo de usuario
    private List<User> lstUsuarios;

    private Button btnBuscar;
    private TextView txtSalida;

    //Servicio
    UserService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnBuscar = findViewById(R.id.btnBuscar);
        txtSalida = findViewById(R.id.txtSalida);

        //crea acceso al servicio Rest
        service = Connection.getConnecion().create(UserService.class);

        //Para el adapatador
        adaptador = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, lstNombreUsuario);
        spnUsuario = findViewById(R.id.spnUsarios);
        spnUsuario.setAdapter(adaptador);


        cargaUsuarios();


    }

    public void cargaUsuarios(){
        Call<List<User>> call = service.listaUsuarios();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                  if (response.isSuccessful()){
                        lstUsuarios =  response.body();
                        for(User obj: lstUsuarios){
                            lstNombreUsuario.add(obj.getName());
                        }
                        adaptador.notifyDataSetChanged();
                  }else{
                      mensajeToast("Error al acceder al Servicio Rest >>> ");
                  }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                mensajeToast("Error al acceder al Servicio Rest >>> " + t.getMessage());
            }
        });
    }

    public void mensajeToast(String mensaje){
        Toast toast1 =  Toast.makeText(getApplicationContext(),mensaje, Toast.LENGTH_LONG);
        toast1.show();
    }

    public void mensajeAlert(String msg){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(msg);
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

}