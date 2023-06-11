package tw.edu.yuntech.b10917025.interactive_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.google.ar.sceneform.ux.ArFragment;
import java.util.ArrayList;

public class ArActivity extends AppCompatActivity {

    ArFragment arFragment;
    Button btnBack;
    TextView textName_ar, textElement, textIntro, textPrice_ar;
    private ArrayList<String> arrayList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);

        arrayList = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();

        textName_ar = (TextView) findViewById(R.id.textName_ar);
        textElement = (TextView) findViewById(R.id.textElement);
        textIntro = (TextView) findViewById(R.id.textIntro);
        textPrice_ar = (TextView) findViewById(R.id.textPrice_ar);

        arrayList = bundle.getStringArrayList("meal");

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);

        textName_ar.setText(arrayList.get(0));
        textPrice_ar.setText("Price\n" + arrayList.get(1));
        textElement.setText("Element\n" + arrayList.get(2));
        textIntro.setText("Introduction\n" + arrayList.get(3));
        arFragment.setOnTapPlaneGlbModel(arrayList.get(4));
    }
}