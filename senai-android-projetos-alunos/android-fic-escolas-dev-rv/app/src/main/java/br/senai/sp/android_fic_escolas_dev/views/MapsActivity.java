package br.senai.sp.android_fic_escolas_dev.views;

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

import br.senai.sp.android_fic_escolas_dev.R;
import br.senai.sp.android_fic_escolas_dev.bd.AlunoDaoDB;
import br.senai.sp.android_fic_escolas_dev.dao.AlunoDao;
import br.senai.sp.android_fic_escolas_dev.model.Aluno;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    // private AlunoDao dao = AlunoDao.manager;
    private AlunoDaoDB dao = new AlunoDaoDB(this);
    private String enderecoDoAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // verifica as informações do aluno
        final Bundle extras = getIntent().getExtras();
        Long idDoAlunoCarregado = (extras != null) ? extras.getLong("idAluno") : null;
        // localizo o aluno através do id dele
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

        LatLng localizacaoDoAluno = buscarLocalizacaoAtravesDoEndereco(getApplicationContext(), enderecoDoAluno);

        LatLng localizacao = new LatLng(localizacaoDoAluno.latitude, localizacaoDoAluno.longitude);
        mMap.addMarker(new MarkerOptions().position(localizacao).title("Casa do Aluno"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(localizacao));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localizacao, 10));

    }

    public LatLng buscarLocalizacaoAtravesDoEndereco(Context context, String enderecoRecebido) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(enderecoRecebido, 5);
            if (address == null) {
                return null;
            }
            Address localizacao = address.get(0);
            localizacao.getLatitude();
            localizacao.getLongitude();

            p1 = new LatLng(localizacao.getLatitude(), localizacao.getLongitude() );

        } catch (IOException e) {
            e.printStackTrace();
        }

        return p1;
    }
}
