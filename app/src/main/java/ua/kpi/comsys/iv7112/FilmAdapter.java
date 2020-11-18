package ua.kpi.comsys.iv7112;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FilmAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> years = new ArrayList<>();
    ArrayList<String> types = new ArrayList<>();
    ArrayList<Integer> posters = new ArrayList<>();

    FilmAdapter(Context context, ArrayList<String> titles, ArrayList<String> years, ArrayList<String> types, ArrayList<Integer> posters){
        this.titles = titles;
        this.years = years;
        this.types = types;
        this.posters = posters;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = mLayoutInflater.inflate(R.layout.list_item, null);

        ImageView image = (ImageView) convertView.findViewById(R.id.imageViewIcon);
        image.setImageResource(posters.get(position));

        TextView titleTextView = (TextView) convertView.findViewById(R.id.textViewTitle);
        titleTextView.setText(titles.get(position));

        TextView dateTextView = (TextView) convertView.findViewById(R.id.textViewDate);
        dateTextView.setText(years.get(position));

        TextView typeTextView = (TextView) convertView.findViewById(R.id.textViewType);
        typeTextView.setText(types.get(position));

        return convertView;
    }


}
