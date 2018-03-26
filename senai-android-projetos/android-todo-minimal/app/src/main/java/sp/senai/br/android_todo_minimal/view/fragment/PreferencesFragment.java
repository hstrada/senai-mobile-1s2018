package sp.senai.br.android_todo_minimal.view.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import sp.senai.br.android_todo_minimal.R;
import sp.senai.br.android_todo_minimal.util.Constants;

/**
 * Created by helena.strada on 15/01/18.
 */

public class PreferencesFragment extends PreferenceFragment
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    private SwitchPreference nightMode;
    private SharedPreferences themePreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        nightMode = (SwitchPreference) findPreference("nightMode");

    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        boolean isChecked = sharedPreferences.getBoolean("nightMode", false);

        themePreferences = getActivity().getSharedPreferences(Constants.THEME_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor themeEditor = themePreferences.edit();

        themeEditor.putBoolean(Constants.RECREATE_ACTIVITY, true);

        // se o SwitchPreference estiver marcado, o tema será escuro
        if (isChecked == true) {
            themeEditor.putString(Constants.THEMESAVED, Constants.DARKTHEME);
        }
        // caso contrario, sera claro
        else {
            themeEditor.putString(Constants.THEMESAVED, Constants.LIGHTTHEME);
        }

        Toast.makeText(getActivity(), "NightMode : " + isChecked, Toast.LENGTH_LONG).show();

        themeEditor.apply();

        getActivity().recreate();

    }

    @Override
    public void onResume() {
        super.onResume();
        // Lendo as preferencias que foram salvas
        android.preference.SwitchPreference preference = (android.preference.SwitchPreference) findPreference("nightMode");
        preference.setSummaryOff("Night Mode desligado.");
        preference.setSummaryOn("Night Mode ligado.");

        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}
