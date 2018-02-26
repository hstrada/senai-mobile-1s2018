package sp.senai.br.android_calculadora_binaria;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etNumero;
    private Button btCalcular;
    private String stringEntrada;
    private int numeroDecimal;
    private String numeroSaida;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // As 3 referencias que precisam ser feitas sao: do valor de entrada do usuário e os dois cliques no botao (limpar e calcular)
        etNumero = findViewById(R.id.etNumeroDecimal);
        btCalcular = findViewById(R.id.btCalcular);
        tvResultado = findViewById(R.id.tvResultado);

        // para realizar o calculo de decimal para binario
        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // busco o valor de entrada do usuario
                stringEntrada = etNumero.getText().toString();

                // vejo se o numero digitado é um numero inteiro
                if (stringEntrada.matches("^-?\\d{1,19}$")) {
                    // caso seja, pego esse valor e guardo numa variavel de numero decimal
                    numeroDecimal = Integer.parseInt(stringEntrada);
                    // chamo o metodo de calcularBinario que ira retornar uma String com o valor já calculado para binario
                    numeroSaida = calculaBinario(numeroDecimal);
                    // faco um log apenas para teste
                    Log.d("Resultado: ", numeroSaida);
                    // imprimo o resultado na tela para o usuario
                    tvResultado.setText(numeroSaida);
                } else {
                    // caso o numero digitado nao seja um inteiro, mostro uma mensagem para o usuario informando que o valor digitado não eh um numero decimal
                    Toast.makeText(getApplicationContext(), "Digite um número decimal válido", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    /**
     *
     * @param numeroEntrada
     * @return String resultado
     *
     * */
    private static String calculaBinario(int numeroEntrada) {

       String resultado = "";
       int resto = 0;
       String res = "";

       if (numeroEntrada <= 0) {
           return "0";
       }

       while (numeroEntrada > 0) {
           resto = numeroEntrada % 2;
           res = new StringBuffer(res).append(resto).toString();
           numeroEntrada = numeroEntrada / 2;
       }

       resultado = new StringBuffer(res).reverse().toString();

       return resultado;

    }

}
