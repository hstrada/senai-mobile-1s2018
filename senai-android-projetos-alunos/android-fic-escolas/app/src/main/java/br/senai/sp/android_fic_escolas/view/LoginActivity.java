package br.senai.sp.android_fic_escolas.view;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.senai.sp.android_fic_escolas.MainActivity;
import br.senai.sp.android_fic_escolas.R;
import br.senai.sp.android_fic_escolas.dao.UsuarioDao;
import br.senai.sp.android_fic_escolas.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout tilLoginAdministrador;
    private TextInputLayout tilSenhaAdministrador;
    private Button btnLoginAdministrador;
    private UsuarioDao usuarioDao = UsuarioDao.manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tilLoginAdministrador = findViewById(R.id.tilLoginAdministrador);
        tilSenhaAdministrador = findViewById(R.id.tilSenhaAdministrador);
        btnLoginAdministrador = findViewById(R.id.btnLoginAdministrador);

        btnLoginAdministrador.setOnClickListener(new realizarLogin());

    }

    private class realizarLogin implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Usuario usuarioDigitado = new Usuario(tilLoginAdministrador.getEditText().getText().toString(), tilSenhaAdministrador.getEditText().getText().toString());
            if (usuarioDao.localizar(usuarioDigitado) != null) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
            } else {
                Toast.makeText(getApplicationContext(), "Usuário ou Senha incorreto.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
