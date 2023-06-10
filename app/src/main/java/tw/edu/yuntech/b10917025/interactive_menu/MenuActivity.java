package tw.edu.yuntech.b10917025.interactive_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    private ListView listView;
    private String store;
    private SQLiteDatabase db = null;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Bundle bundle = getIntent().getExtras();
        db = openOrCreateDatabase("menudb.db", MODE_PRIVATE, null);
        listView = (ListView) findViewById(R.id.listview);
        store = bundle.getString("store");

        cursor = getInfo();
        upDataAdapter(cursor);
    }

    public Cursor getInfo() {
        Cursor cursor = db.rawQuery("SELECT name, price FROM DongsCoffee", null);
        return cursor;
    }

    public void upDataAdapter(Cursor cursor) {
        if (cursor != null && cursor.getCount() != 0) {
            SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,
                    R.layout.menulayout,
                    cursor,
                    new String[]{"name", "price"},
                    new int[]{R.id.textName, R.id.textPrice},
                    0
            );
            //FIXME 掛在這裡
            listView.setAdapter(simpleCursorAdapter);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}