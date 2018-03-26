package sp.senai.br.android_todo_minimal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import sp.senai.br.android_todo_minimal.util.Constants;
import sp.senai.br.android_todo_minimal.view.PreferencesActivity;
import sp.senai.br.android_todo_minimal.view.TodoActivity;

public class MainActivity extends AppCompatActivity {

    private static int ADD_ACTION = 1;
    private static int EDIT_ACTION = 2;
    private static int PREF_ACTION = 3;
    private String theme;
    private int mTheme;
    private RecyclerView rvTodoItens;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        theme = getSharedPreferences(Constants.THEME_PREFERENCES, MODE_PRIVATE)
                .getString(Constants.THEMESAVED, Constants.LIGHTTHEME);

        if (theme.equals(Constants.LIGHTTHEME)) {
            mTheme = R.style.CustomStyle_LightTheme;
        } else {
            mTheme = R.style.CustomStyle_DarkTheme;
        }
        this.setTheme(mTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvTodoItens = findViewById(R.id.rvTodoItens);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TodoActivity.class);
                startActivityForResult(intent, ADD_ACTION);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (getSharedPreferences(Constants.THEME_PREFERENCES, MODE_PRIVATE).getBoolean(Constants.RECREATE_ACTIVITY, false)) {
            SharedPreferences.Editor editor = getSharedPreferences(Constants.THEME_PREFERENCES, MODE_PRIVATE).edit();
            editor.putBoolean(Constants.RECREATE_ACTIVITY, false);
            editor.apply();
            recreate();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                Intent tela = new Intent(getBaseContext(), PreferencesActivity.class);
                startActivity(tela);
                // startActivityForResult(tela, PREF_ACTION);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
