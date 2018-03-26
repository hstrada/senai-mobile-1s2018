package sp.senai.br.android_todo_minimal.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import sp.senai.br.android_todo_minimal.R;
import sp.senai.br.android_todo_minimal.util.Constants;

public class PreferencesActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        /*
        *
        * Buscando as preferencias e trocando o valor dependendo
        *     da selecao nas preferencias.
        *
        * */
        String theme = getSharedPreferences
                (Constants.THEME_PREFERENCES, MODE_PRIVATE).getString(Constants.THEMESAVED, Constants.LIGHTTHEME);

        if(theme.equals(Constants.LIGHTTHEME)) {
            setTheme(R.style.CustomStyle_LightTheme);
        } else {
            setTheme(R.style.CustomStyle_DarkTheme);
        }

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_preferences);

    }
}
