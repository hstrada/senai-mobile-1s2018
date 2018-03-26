package br.senai.sp.android_livros_busca.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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

import com.bumptech.glide.Glide;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import br.senai.sp.android_livros_busca.R;
import br.senai.sp.android_livros_busca.dao.LivroDao;
import br.senai.sp.android_livros_busca.model.Livro;

public class EditActivity extends AppCompatActivity {

    private ImageView ivCapaLivro;
    private TextInputLayout tilAutorLivro;
    private TextInputLayout tilNomeLivro;
    private TextInputLayout tilLancamentoLivro;
    private EditText etLancamentoLivro;
    private int mYear, mMonth, mDay;
    private Button btnSalvarLivro;
    private byte[] b;
    private String temp;

    private LivroDao dao = LivroDao.manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ivCapaLivro = findViewById(R.id.ivCapaLivro);
        tilAutorLivro = findViewById(R.id.tilAutorLivro);
        tilNomeLivro = findViewById(R.id.tilNomeLivro);
        tilLancamentoLivro = findViewById(R.id.tilLancamentoLivro);
        etLancamentoLivro = findViewById(R.id.etLancamentoLivro);
        btnSalvarLivro = findViewById(R.id.btnSalvarLivro);

        tilLancamentoLivro.setOnClickListener(new abrirDatePicker());
        ivCapaLivro.setOnClickListener(new abrirGaleria());
        btnSalvarLivro.setOnClickListener(new salvarLivro());

        carregarInformacoesDoLivro();

    }

    private void carregarInformacoesDoLivro() {

        Log.d("Lista: ", dao.getLista().toString());

        final Bundle extras = getIntent().getExtras();
        Long livroId = (extras != null) ? extras.getLong("livroId") : null;

        if (livroId == null) {
            // se não tiver um livro, eu irei realizar um novo cadastro
            Livro livro = new Livro();
        } else {

            Livro livroCarregado = new Livro();
            livroCarregado = dao.localizar(livroId);
            livroCarregado.setId(livroId);
            tilNomeLivro.getEditText().setText(livroCarregado.getNome());
            tilAutorLivro.getEditText().setText(livroCarregado.getAutor());
            tilLancamentoLivro.getEditText().setText(DateFormat.getDateInstance(DateFormat.MEDIUM).format(livroCarregado.getDataLancamento()));

            // se o livro possuir uma capa cadastrada, eu carrego essa imagem
            if (livroCarregado.getCapa() != null) {
                byte[] encodeByte = livroCarregado.getCapa();
                Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

                ivCapaLivro.setImageBitmap(bitmap);
            }
            // se o aluno não possuir uma foto cadastrada, eu carrego uma imagem padrão
            else {
                ivCapaLivro.setImageResource(R.drawable.book_default);
            }
        }
    }

    // Abrir o datepicker para selecionar uma data
    private class abrirDatePicker implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            final Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(EditActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            etLancamentoLivro.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
    }

    // Abrir a galeria para selecionar uma foto
    private class abrirGaleria implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, 1);
        }
    }

    // Resultado da galeria
    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                selectedImage.compress(Bitmap.CompressFormat.PNG, 100, stream);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                selectedImage.compress(Bitmap.CompressFormat.PNG, 100, baos);
                b = baos.toByteArray();

                Glide.with(this)
                        .load(stream.toByteArray())
                        .asBitmap()
                        .centerCrop()
                        .into(ivCapaLivro);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(EditActivity.this, "Algo deu errado.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(EditActivity.this, "Você não selecionou nenhuma imagem.", Toast.LENGTH_LONG).show();
        }
    }

    private class salvarLivro implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            cadastrarLivro();
        }
    }

    private void cadastrarLivro() {
        final Bundle extras = getIntent().getExtras();
        Long idDoLivroSelecionado = (extras != null) ? extras.getLong("livroId") : null;

        if (idDoLivroSelecionado == null) {
            Livro livro = new Livro();
            livro.setNome(tilNomeLivro.getEditText().getText().toString());
            livro.setAutor(tilAutorLivro.getEditText().getText().toString());
            livro.setDataLancamento(new Date(tilLancamentoLivro.getEditText().getText().toString()));
            livro.setCapa(b);

            dao.salvar(livro);
        } else {
            Livro livroExistente = new Livro();
            livroExistente.setId(idDoLivroSelecionado);
            livroExistente.setNome(tilNomeLivro.getEditText().getText().toString());
            livroExistente.setAutor(tilAutorLivro.getEditText().getText().toString());
            livroExistente.setDataLancamento(new Date(tilLancamentoLivro.getEditText().getText().toString()));
            livroExistente.setCapa(b);

            dao.salvar(livroExistente);
        }

        retornarParaTelaAnterior();
    }

    private void retornarParaTelaAnterior() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", 1);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
