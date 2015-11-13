package android.ua.testapp;

import android.content.Context;
import android.ua.testapp.dbmanager.models.Record;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Record> {

    public CustomAdapter(Context context, List<Record> records) {
        super(context, R.layout.custom_row, records);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.custom_row, parent, false);

        Record record = getItem(position);
        ImageView imgIcon = (ImageView)customView.findViewById(R.id.imgIcon);
        TextView txtId = (TextView)customView.findViewById(R.id.txtId);
        TextView txtMessage = (TextView)customView.findViewById(R.id.txtMessage);

        imgIcon.setImageResource(R.drawable.database);
        txtId.setText(String.valueOf(record.getId()));
        try {
            txtMessage.setText(DateParser.unixToFormatedString("dd.MM.yyyy HH:mm:ss", Long.parseLong(record.getMessage())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customView;
    }
}
