package br.senai.sp.android_fic_escolas.view;

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

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import br.senai.sp.android_fic_escolas.R;
import br.senai.sp.android_fic_escolas.dao.AlunoDao;
import br.senai.sp.android_fic_escolas.model.Aluno;

public class EditActivity extends AppCompatActivity {

    private TextInputLayout tilNomeAluno;
    private TextInputLayout tilDataNascimentoAluno;
    private TextInputLayout tilEnderecoAluno;

    private EditText etDataNascimentoAluno;

    private Button btnSalvarAluno;

    private int mYear, mMonth, mDay;

    private byte[] b;

    private ImageView ivFotoAluno;

    private String temp;

    private AlunoDao dao = AlunoDao.manager;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // Referências das views
        tilNomeAluno = findViewById(R.id.tilNomeAluno);
        tilDataNascimentoAluno = findViewById(R.id.tilDataNascimentoAluno);
        tilEnderecoAluno = findViewById(R.id.tilEnderecoAluno);
        etDataNascimentoAluno = findViewById(R.id.etDataNascimentoAluno);
        btnSalvarAluno = findViewById(R.id.btnSalvarAluno);
        ivFotoAluno = findViewById(R.id.ivFotoAluno);

        // preciso carregar os dados do aluno, caso ele já exista
        carregarDadosDoAluno();

        // ao clicar no datepicker
        etDataNascimentoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // calendário
                final Calendar calendar = Calendar.getInstance();
                mYear = calendar.get(Calendar.YEAR);
                mMonth = calendar.get(Calendar.MONTH);
                mDay = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(EditActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                etDataNascimentoAluno.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnSalvarAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrarAluno();
            }
        });

        ivFotoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

//        btnSalvarAluno.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Aluno alunoParaSalvar = new Aluno(tilNomeAluno.getEditText().getText().toString(), new Date(tilDataNascimentoAluno.getEditText().getText().toString()), tilEnderecoAluno.getEditText().getText().toString());
//                dao.salvar(alunoParaSalvar);
//                Log.d("Lista: ", dao.getLista().toString());
//                Intent returnIntent = new Intent();
//                returnIntent.putExtra("result", 1);
//                setResult(Activity.RESULT_OK,returnIntent);
//                finish();
//            }
//        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivFotoAluno.setImageBitmap(imageBitmap);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            b = baos.toByteArray();
            temp = Base64.encodeToString(b, Base64.DEFAULT);

        }
    }

    private void carregarDadosDoAluno() {

        final Bundle extras = getIntent().getExtras();
        Long idDoAlunoCarregado = (extras != null) ? extras.getLong("idAluno") : null;

        if (idDoAlunoCarregado == null) {
            // se não tiver um aluno, eu irei realizar um novo cadastro
            Aluno aluno = new Aluno();
        } else {
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
                ivFotoAluno.setImageResource(R.drawable.logo);
            }

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

    private void retornarParaTelaAnteriorAposSalvar() {
        Log.d("Lista: ", dao.getLista().toString());
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", 1);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
