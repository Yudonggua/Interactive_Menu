package tw.edu.yuntech.b10917025.interactive_menu;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

        //找到data，並在View上設定正確的data
        NameMapping currentName = getItem(position);

        //找到ListItem.xml中的兩個TextView(物種學名和中文名)
        TextView tortoises_text_view = listItemView.findViewById(R.id.textName);
        tortoises_text_view.setText(currentName.getName());

        TextView default_text_view = listItemView.findViewById(R.id.textPrice);
        default_text_view.setText(currentName.getPrice());

        return listItemView;
    }
}
