package tw.edu.yuntech.b10917025.interactive_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private ListView listView;
    private String store;
    //    private SQLiteDatabase db = null;
    private Cursor cursor;
    private ArrayList<String> arrayList;

    private ArrayList<NameMapping> mappings;
    private ArrayList<ArrayList> arrayLists;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        arrayLists = new ArrayList<>();
        Bundle bundle = getIntent().getExtras();
//        db = openOrCreateDatabase("menudb.db", MODE_PRIVATE, null);
        listView = (ListView) findViewById(R.id.listview);
        store = bundle.getString("store");
        mappings = new ArrayList<NameMapping>();

        for (int i = 0; i < 2; i++) {
            arrayList = bundle.getStringArrayList(Integer.toString(i));
            arrayLists.add(arrayList);
            mappings.add(new NameMapping(arrayList.get(0), arrayList.get(1)));
        }
        //mappings.add(new NameMapping(arrayList.get(0), arrayList.get(1), arrayList.get(2), arrayList.get(3), arrayList.get(4)));

        MenuAdapter menuAdapter = new MenuAdapter(this, mappings);
        listView.setAdapter(menuAdapter);
        listView.setOnItemClickListener(onItemClickListener);

//        cursor = getInfo();
//        upDataAdapter(cursor);
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
    protected void onDestroy() {
        super.onDestroy();
//        db.close();
    }
}