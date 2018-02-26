package sp.senai.br.android_calculo_juros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnCalcular;
    private Button btnLimpar;

    private EditText edCapital;
    private EditText edMeses;
    private EditText edTaxa;
    private EditText edResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Buscando as referencias dos botoes
        btnCalcular = findViewById(R.id.btnCalcular);
        btnLimpar = findViewById(R.id.btnLimpar);

        // Buscando as referencias do campos de texto
        edCapital = findViewById(R.id.edCapital);
        edMeses = findViewById(R.id.edMeses);
        edTaxa = findViewById(R.id.edTaxa);
        edResultado = findViewById(R.id.edResultado);

        // chamando o próprio método da classe
        btnCalcular.setOnClickListener(this);
        // btnLimpar.setOnClickListener(this);

        // btnCalcular.setOnClickListener(chamandoUmMetodo);

        // btnCalcular.setOnClickListener(new ChamandoUmaClasse());

        // Acao no proprio botao ao clicar
        /* btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Configurar o formatador de Números
                    NumberFormat fmt = NumberFormat.getNumberInstance();
                    fmt.setMaximumFractionDigits(2);
                    fmt.setMinimumFractionDigits(2);

                    // lê e converte os valores informados na tela
                    double capital = fmt.parse(edCapital.getText().toString()).doubleValue();
                    int meses = Integer.parseInt(edMeses.getText().toString());
                    double taxa = fmt.parse(edTaxa.getText().toString()).doubleValue();

                    // efetua o calculo
                    double resultado = capital * taxa / 100 * meses + capital;

                    // apresenta o resultado
                    edResultado.setText(String.format("%,.2f", resultado));

                } catch (Exception ex) {
                    // Caso contrário, informa que há erro nos valores digitados
                    Toast.makeText(getApplicationContext(), "Número(s) inválido(s)!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edCapital.setText(getResources().getText(R.string.num_decimal));
                edMeses.setText(getResources().getText(R.string.num_inteiro));
                edTaxa.setText(getResources().getText(R.string.num_decimal));
                edResultado.setText(getResources().getText(R.string.num_decimal));
            }
        }); */

    }



    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {
            case R.id.btnCalcular:
                try {
                    // Configurar o formatador de Números
                    NumberFormat fmt = NumberFormat.getNumberInstance();
                    fmt.setMaximumFractionDigits(2);
                    fmt.setMinimumFractionDigits(2);

                    // lê e converte os valores informados na tela
                    double capital = fmt.parse(edCapital.getText().toString()).doubleValue();
                    int meses = Integer.parseInt(edMeses.getText().toString());
                    double taxa = fmt.parse(edTaxa.getText().toString()).doubleValue();

                    // efetua o calculo
                    double resultato = capital * taxa / 100 * meses + capital;

                    // apresenta o resultado
                    edResultado.setText(String.format("%,.2f", resultato));

                } catch (Exception ex) {
                    // Caso contrário, informa que há erro nos valores digitados
                    Toast.makeText(this, "Número(s) inválido(s)!", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnLimpar:
                edCapital.setText(getResources().getText(R.string.num_decimal));
                edMeses.setText(getResources().getText(R.string.num_inteiro));
                edTaxa.setText(getResources().getText(R.string.num_decimal));
                edResultado.setText(getResources().getText(R.string.num_decimal));
                break;
        }

    };

    private View.OnClickListener chamandoUmMetodo = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

                try {
                    // Configurar o formatador de Números
                    NumberFormat fmt = NumberFormat.getNumberInstance();
                    fmt.setMaximumFractionDigits(2);
                    fmt.setMinimumFractionDigits(2);

                    // lê e converte os valores informados na tela
                    double capital = fmt.parse(edCapital.getText().toString()).doubleValue();
                    int meses = Integer.parseInt(edMeses.getText().toString());
                    double taxa = fmt.parse(edTaxa.getText().toString()).doubleValue();

                    // efetua o calculo
                    double resultado = capital * taxa / 100 * meses + capital;

                    // apresenta o resultado
                    edResultado.setText(String.format("%,.2f", resultado));

                } catch (Exception ex) {
                    // Caso contrário, informa que há erro nos valores digitados
                    Toast.makeText(getApplicationContext(), "Número(s) inválido(s)!", Toast.LENGTH_LONG).show();
                }

        }
    };

    class ChamandoUmaClasse implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            try {
                // Configurar o formatador de Números
                NumberFormat fmt = NumberFormat.getNumberInstance();
                fmt.setMaximumFractionDigits(2);
                fmt.setMinimumFractionDigits(2);

                // lê e converte os valores informados na tela
                double capital = fmt.parse(edCapital.getText().toString()).doubleValue();
                int meses = Integer.parseInt(edMeses.getText().toString());
                double taxa = fmt.parse(edTaxa.getText().toString()).doubleValue();

                // efetua o calculo
                double resultado = capital * taxa / 100 * meses + capital;

                // apresenta o resultado
                edResultado.setText(String.format("%,.2f", resultado));

            } catch (Exception ex) {
                // Caso contrário, informa que há erro nos valores digitados
                Toast.makeText(getApplicationContext(), "Número(s) inválido(s)!", Toast.LENGTH_LONG).show();
            }
        }
    };

    public void limparCampos(View view) {
        edCapital.setText(getResources().getText(R.string.num_decimal));
        edMeses.setText(getResources().getText(R.string.num_inteiro));
        edTaxa.setText(getResources().getText(R.string.num_decimal));
        edResultado.setText(getResources().getText(R.string.num_decimal));
        Toast.makeText(this, "Campos Limpos", Toast.LENGTH_LONG).show();
    };
}
