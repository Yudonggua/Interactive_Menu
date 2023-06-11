package tw.edu.yuntech.b10917025.interactive_menu;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private ArrayList<String> arrayList;
    private ArrayList<ArrayList> arrayLists;
    private ArrayList<NameMapping> mappings;
    private Bundle bundleCreate;
    private ListView listView;
    private String store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        arrayLists = new ArrayList<>();
        bundleCreate = getIntent().getExtras();
        listView = (ListView) findViewById(R.id.listview);
        store = bundleCreate.getString("store");
        mappings = new ArrayList<NameMapping>();

        for (int i = 0; i < 4; i++) {
            arrayList = bundleCreate.getStringArrayList(Integer.toString(i));
            arrayLists.add(arrayList);
            mappings.add(new NameMapping(arrayList.get(0), arrayList.get(1), (int) Integer.parseInt(arrayList.get(5))));
        }

        MenuAdapter menuAdapter = new MenuAdapter(this, mappings);
        listView.setAdapter(menuAdapter);
        listView.setOnItemClickListener(onItemClickListener);
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(MenuActivity.this, ArActivity.class);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("meal", arrayLists.get((int) id));
            intent.putExtras(bundle);
            startActivity(intent);
            onDestroy();
        }
    };

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBundle("menu", bundleCreate);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        bundleCreate = savedInstanceState.getBundle("menu");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}