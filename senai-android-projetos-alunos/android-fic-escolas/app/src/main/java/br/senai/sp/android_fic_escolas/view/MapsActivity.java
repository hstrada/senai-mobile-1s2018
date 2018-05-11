package br.senai.sp.android_fic_escolas.view;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import br.senai.sp.android_fic_escolas.R;
import br.senai.sp.android_fic_escolas.dao.AlunoDao;
import br.senai.sp.android_fic_escolas.model.Aluno;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private AlunoDao dao = AlunoDao.manager;
    private String enderecoDoAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // verifica as informações do aluno
        final Bundle extras = getIntent().getExtras();
        Long idDoAlunoCarregado = (extras != null) ? extras.getLong("idAluno") : null;
        Aluno alunoCarregado = new Aluno();
        alunoCarregado = dao.localizar(idDoAlunoCarregado);
        enderecoDoAluno = alunoCarregado.getEndereco();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng localizacaoDoAluno = getLocationFromAddress(getApplicationContext(), enderecoDoAluno);

        LatLng localizacao = new LatLng(localizacaoDoAluno.latitude, localizacaoDoAluno.longitude);
        mMap.addMarker(new MarkerOptions().position(localizacao).title("Casa do Aluno"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(localizacao));
    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException e) {
            e.printStackTrace();
        }

        return p1;
    }
}
