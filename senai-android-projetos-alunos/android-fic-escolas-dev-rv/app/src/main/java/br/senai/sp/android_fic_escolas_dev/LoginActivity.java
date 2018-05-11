package br.senai.sp.android_fic_escolas_dev;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.senai.sp.android_fic_escolas_dev.dao.UsuarioDao;
import br.senai.sp.android_fic_escolas_dev.model.Usuario;
import br.senai.sp.android_fic_escolas_dev.views.MainActivity;

public class LoginActivity extends AppCompatActivity {

    // views
    private TextInputLayout tilLoginEmail;
    private TextInputLayout tilLoginSenha;
    private Button btLoginUsuario;

    // variáveis do usuário
    private String loginDigitado;
    private String senhaDigitada;

    private UsuarioDao dao = UsuarioDao.manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // fazendo as referências das nossas views
        tilLoginEmail = findViewById(R.id.tilLoginEmail);
        tilLoginSenha = findViewById(R.id.tilLoginSenha);
        btLoginUsuario = findViewById(R.id.btLoginUsuario);

        tilLoginEmail.getEditText().setText("administrador");
        tilLoginSenha.getEditText().setText("administrador");

        btLoginUsuario.setOnClickListener(new verificarUsuarioDigitado());

    }

    private class verificarUsuarioDigitado implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            loginDigitado = tilLoginSenha.getEditText().getText().toString();
            senhaDigitada = tilLoginSenha.getEditText().getText().toString();
            Usuario usuarioConferirNaLista = new Usuario(loginDigitado, senhaDigitada);

            if (dao.localizar(usuarioConferirNaLista) != null) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                LoginActivity.this.finish();
                // Toast.makeText(getApplicationContext(), "Login confere.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Usuário ou senha incorretos.", Toast.LENGTH_SHORT).show();
            }

//            Toast.makeText(getApplicationContext(), loginDigitado + senhaDigitada, Toast.LENGTH_LONG).show();
//            Log.d("Usuario: ", loginDigitado + senhaDigitada);

        }
    }
}
