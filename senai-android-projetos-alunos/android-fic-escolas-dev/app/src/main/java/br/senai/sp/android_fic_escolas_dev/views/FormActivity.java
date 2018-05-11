package br.senai.sp.android_fic_escolas_dev.views;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import br.senai.sp.android_fic_escolas_dev.R;
import br.senai.sp.android_fic_escolas_dev.bd.AlunoDaoDB;
import br.senai.sp.android_fic_escolas_dev.bd.AlunoDbHelper;
import br.senai.sp.android_fic_escolas_dev.dao.AlunoDao;
import br.senai.sp.android_fic_escolas_dev.model.Aluno;

public class FormActivity extends AppCompatActivity {

    private TextInputLayout tilNomeAluno;
    private TextInputLayout tilDataNascimentoAluno;
    private TextInputLayout tilEnderecoAluno;
    private EditText etDataNascimentoAluno;
    private Button btnSalvarAluno;
    private ImageView ivFotoAluno;

    // dados do calendário para montar a data
    private int ano, mes, dia;

    // recursos para salvar a imagem
    private byte[] b;
    private String temp;

    // classe dao para realizar as ações com o usuário
    // private AlunoDao dao = AlunoDao.manager;
    private AlunoDaoDB dao = new AlunoDaoDB(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        getSupportActionBar().setTitle("Formulário");

        // Referências das views
        tilNomeAluno = findViewById(R.id.tilNomeAluno);
        tilDataNascimentoAluno = findViewById(R.id.tilDataNascimentoAluno);
        tilEnderecoAluno = findViewById(R.id.tilEnderecoAluno);
        etDataNascimentoAluno = findViewById(R.id.etDataNascimentoAluno);
        btnSalvarAluno = findViewById(R.id.btnSalvarAluno);
        ivFotoAluno = findViewById(R.id.ivFotoAluno);

        etDataNascimentoAluno.setOnClickListener(new abrirCalendario());
        ivFotoAluno.setOnClickListener(new abrirCamera());
        btnSalvarAluno.setOnClickListener(new salvarDadosDoAluno());

        carregarDadosDoAlunoCasoTenhaId();

    }

    // abrir um calendário
    private class abrirCalendario implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // calendário
            final Calendar calendar = Calendar.getInstance();
            ano = calendar.get(Calendar.YEAR);
            mes = calendar.get(Calendar.MONTH);
            dia = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(FormActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            etDataNascimentoAluno.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
                        }
                    }, ano, mes, dia);
            datePickerDialog.show();
        }
    }

    // abrir a camera
    private class abrirCamera implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            abrirIntentDeCamera();
        }

    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private void abrirIntentDeCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // starto uma nova activity para abrir a camera
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // ao fechar a camera, eu preciso verificar se o status foi ok e além disso, pegar a imagem que foi tirada
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            // com a imagem, eu vou mostrar a imagem na nossa imageview
            ivFotoAluno.setImageBitmap(imageBitmap);
            // uma vez que eu tenho essa imagem, preciso salvá-la
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            b = baos.toByteArray();
            temp = Base64.encodeToString(b, Base64.DEFAULT);

        }
    }

    private class salvarDadosDoAluno implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            cadastrarAluno();
        }
    }

    private void cadastrarAluno() {

        // Preciso verificar se já existi um aluno cadastrado
        final Bundle extras = getIntent().getExtras();
        // bundle.getString("idAluno");
        Long idDoAlunoSelecionado = (extras != null) ? extras.getLong("idAluno") : null;

        // caso eu não tenha nenhum aluno selecionado, estarei criando um, portanto:
        if (idDoAlunoSelecionado == null) {
            Aluno alunoNovo = new Aluno();
            alunoNovo.setNome(tilNomeAluno.getEditText().getText().toString());
            alunoNovo.setDataNascimento(new Date(tilDataNascimentoAluno.getEditText().getText().toString()));
            alunoNovo.setEndereco(tilEnderecoAluno.getEditText().getText().toString());

            alunoNovo.setFotoAluno(temp);
            // dentro do método salvar, eu verifico se o aluno existe
            dao.salvar(alunoNovo);
            Log.d("lista: ", String.valueOf(dao.getLista()));
            Log.d("aluno: ", alunoNovo.toString());
        }
        else {
            Aluno alunoExistente = new Aluno();
            alunoExistente.setId(idDoAlunoSelecionado);
            alunoExistente.setNome(tilNomeAluno.getEditText().getText().toString());
            alunoExistente.setDataNascimento(new Date(tilDataNascimentoAluno.getEditText().getText().toString()));
            alunoExistente.setEndereco(tilEnderecoAluno.getEditText().getText().toString());

            alunoExistente.setFotoAluno(temp);

            dao.salvar(alunoExistente);

        }

        retornarParaTelaAnteriorAposSalvar();

    }

    private void carregarDadosDoAlunoCasoTenhaId() {
        final Bundle extras = getIntent().getExtras();
        Long idDoAlunoCarregado = (extras != null) ? extras.getLong("idAluno") : null;

        if (idDoAlunoCarregado == null) {
            // se não tiver um aluno, eu irei realizar um novo cadastro
            Aluno aluno = new Aluno();
            getSupportActionBar().setTitle("Cadastrar Aluno");
        } else {
            getSupportActionBar().setTitle("Editar Aluno");
            Aluno alunoCarregado = new Aluno();
            alunoCarregado = dao.localizar(idDoAlunoCarregado);
            alunoCarregado.setId(idDoAlunoCarregado);
            tilNomeAluno.getEditText().setText(alunoCarregado.getNome());
            tilDataNascimentoAluno.getEditText().setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(alunoCarregado.getDataNascimento()));
            tilEnderecoAluno.getEditText().setText(alunoCarregado.getEndereco());

            // se o aluno possuir uma foto cadastrada, eu carrego essa imagem
            if (alunoCarregado.getFotoAluno() != null) {
                byte[] encodeByte = Base64.decode(alunoCarregado.getFotoAluno(), Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
                ivFotoAluno.setImageBitmap(bitmap);
                temp = alunoCarregado.getFotoAluno();
            }
            // se o aluno não possuir uma foto cadastrada, eu carrego uma imagem padrão
            else {
                ivFotoAluno.setImageResource(R.drawable.ic_launcher_background);
            }

        }
    }

    private void retornarParaTelaAnteriorAposSalvar() {
        Intent retornarParaActivityHome = new Intent();
        retornarParaActivityHome.putExtra("result", 1);
        setResult(Activity.RESULT_OK, retornarParaActivityHome);
        finish();
    }
}
