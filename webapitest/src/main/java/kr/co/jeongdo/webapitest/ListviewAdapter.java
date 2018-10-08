package kr.co.jeongdo.webapitest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListviewAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<Listviewitem> data;
    private int layout;

    public  ListviewAdapter(Context context, int layout, ArrayList<Listviewitem> data){
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.layout = layout;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position).itemName;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = inflater.inflate(layout, parent, false);
        }
        Listviewitem listviewitem = data.get(position);
        TextView itemCode = (TextView)convertView.findViewById(R.id.itemCode);
        itemCode.setText(listviewitem.itemCode);
        TextView itemName = (TextView)convertView.findViewById(R.id.itemName);
        itemName.setText(listviewitem.itemName);
        return convertView;
    }
}
