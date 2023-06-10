package tw.edu.yuntech.b10917025.interactive_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.ar.sceneform.ux.ArFragment;

import java.util.ArrayList;

public class ArActivity extends AppCompatActivity {

    ArFragment arFragment;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);


        arrayList = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
        arrayList = bundle.getStringArrayList("meal");

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);
        arFragment.setOnTapPlaneGlbModel(arrayList.get(4));
    }
}