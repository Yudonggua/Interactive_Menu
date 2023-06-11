package tw.edu.yuntech.b10917025.interactive_menu;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuAdapter extends ArrayAdapter<NameMapping> {
    public MenuAdapter(Activity context, ArrayList<NameMapping> menus) {
        super(context, 0, menus);
    }

    @Override
    //改寫getView()方法
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        //listItemView可能會是空的，例如App剛啟動時，沒有預先儲存的view可使用
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.menulayout, parent, false);
        }

        ImageView imageView = listItemView.findViewById(R.id.imageView);
        NameMapping currentName = getItem(position);
        TextView textViewName = listItemView.findViewById(R.id.textName);
        TextView textViewPrice = listItemView.findViewById(R.id.textPrice);

        imageView.setImageResource(currentName.getImag());
        textViewName.setText(currentName.getName());
        textViewPrice.setText("Price: " + currentName.getPrice());

        return listItemView;
    }
}
