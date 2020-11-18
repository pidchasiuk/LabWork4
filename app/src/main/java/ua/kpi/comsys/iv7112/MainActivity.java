package ua.kpi.comsys.iv7112;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends Activity {

    private FilmAdapter mAdapter;
    private ListView lv;
    EditText inputSearch;

    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> years = new ArrayList<>();
    ArrayList<String> types = new ArrayList<>();
    ArrayList<Integer> posters = new ArrayList<>();
    ArrayList<String> genres = new ArrayList<>();
    ArrayList<String> directors = new ArrayList<>();
    ArrayList<String> actors = new ArrayList<>();
    ArrayList<String> counties = new ArrayList<>();
    ArrayList<String> languages = new ArrayList<>();
    ArrayList<String> prods = new ArrayList<>();
    ArrayList<String> releases = new ArrayList<>();
    ArrayList<String> runtimes = new ArrayList<>();
    ArrayList<String> awards = new ArrayList<>();
    ArrayList<String> ratings = new ArrayList<>();
    ArrayList<String> plots = new ArrayList<>();
    ArrayList<Movie> movies;
    String title = "Empty";
    String year = "Empty";
    String type = "Empty";
    public static final int IDM_OPEN = 101;
    public static final int IDM_DEL = 102;

    int position_gl = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        {
            try {
                movies = ReadJSON.readMovieJSONFile(this);
                for (int i = 0; i < movies.size(); i++) {
                    titles.add(movies.get(i).getTitle());
                    years.add(movies.get(i).getYear());
                    types.add(movies.get(i).getType());
                    posters.add(getResourceId(this, movies.get(i).getPoster().replace(".jpg", "")));
                    genres.add(movies.get(i).getGenre());
                    directors.add(movies.get(i).getDirector());
                    actors.add(movies.get(i).getActors());
                    counties.add(movies.get(i).getCountry());
                    languages.add(movies.get(i).getLanguage());
                    prods.add(movies.get(i).getProduction());
                    releases.add(movies.get(i).getReleased());
                    runtimes.add(movies.get(i).getRuntime());
                    awards.add(movies.get(i).getAwards());
                    ratings.add(movies.get(i).getRating());
                    plots.add(movies.get(i).getPlot());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        lv = (ListView) findViewById(R.id.list_view);
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        mAdapter = new FilmAdapter(this, titles, years, types, posters);
        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                position_gl = position;
                registerForContextMenu(view);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE, IDM_OPEN, Menu.NONE, "About Film");
        menu.add(Menu.NONE, IDM_DEL, Menu.NONE, "Delete from List");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        CharSequence message;
        switch (item.getItemId()) {
            case IDM_OPEN:
                Intent intent = new Intent(MainActivity.this, AboutFilmActivity.class);
                intent.putExtra("position", position_gl);
                startActivity(intent);
                break;
            case IDM_DEL:
                removeFromList(position_gl);
                message = "Film is deleted from the list";
                Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    public static int getResourceId(Context context, String name) {
        name = name.toLowerCase();
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }

    public void removeFromList(int position) {
        titles.remove(position);
        years.remove(position);
        types.remove(position);
        posters.remove(position);
        mAdapter = new FilmAdapter(this, titles, years, types, posters);
        lv.setAdapter(mAdapter);
    }

    public void onClickSearch(View view) {
        String text = inputSearch.getText().toString().toLowerCase();
        ArrayList<String> titles_search = new ArrayList<>();
        ArrayList<String> years_search = new ArrayList<>();
        ArrayList<String> types_search = new ArrayList<>();
        ArrayList<Integer> posters_search = new ArrayList<>();

        if (text.length() == 0) {
            Toast toast = Toast.makeText(getApplicationContext(), "Please, input text", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            for (int i = 0; i < titles.size(); i++) {
                String temp = titles.get(i).toLowerCase();
                if (temp.contains(text)) {
                    titles_search.add(titles.get(i));
                    years_search.add(years.get(i));
                    types_search.add(types.get(i));
                    posters_search.add(posters.get(i));
                }
            }
            if (titles_search.isEmpty()) {
                Toast toast = Toast.makeText(getApplicationContext(), "There are no results", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            } else {
                lv = (ListView) findViewById(R.id.list_view);
                mAdapter = new FilmAdapter(this, titles_search, years_search, types_search, posters_search);
                lv.setAdapter(mAdapter);
            }
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, AddFilmActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        title = getIntent().getStringExtra("title");
        year = getIntent().getStringExtra("year");
        type = getIntent().getStringExtra("type");
        titles.add(title);
        years.add(year);
        types.add(type);
        posters.add(R.drawable.background);
        lv = (ListView) findViewById(R.id.list_view);
        mAdapter = new FilmAdapter(this, titles, years, types, posters);
        lv.setAdapter(mAdapter);
    }
}