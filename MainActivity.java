package wiadevelopers.com.recyclerviewpart3;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import wiadevelopers.com.recyclerviewpart3.Adapter.MoviesAdapter;
import wiadevelopers.com.recyclerviewpart3.Adapter.OnLoadMoreListener;
import wiadevelopers.com.recyclerviewpart3.Model.Movie;


public class MainActivity extends AppCompatActivity
{

    private List<Movie> movies = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter adapter;
    private int counter = 0;
    private int loadItemIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = new MoviesAdapter(movies, MainActivity.this, recyclerView);

        recyclerView.setAdapter(adapter);

        adapter.setOnLoadMoreListener(new OnLoadMoreListener()
        {
            @Override
            public void onLoadMore()
            {
                //Toast.makeText(getApplicationContext(), "This Is On LoadMore()", Toast.LENGTH_SHORT).show();
                Log.i("wiaDevelopersLog", "onLoadMore()");
                Toast.makeText(getApplicationContext(), "start", Toast.LENGTH_SHORT).show();
                movies.add(null);
                loadItemIndex = movies.size() - 1;
                adapter.notifyItemInserted(loadItemIndex);
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        addNewData();
                    }
                }, 5000);

            }
        });
        setData();
    }

    private void addNewData()
    {

        movies.remove(loadItemIndex);
        adapter.notifyItemRemoved(loadItemIndex);

        counter++;
        movies.add(new Movie(counter + "", counter + "", counter + ""));
        counter++;
        movies.add(new Movie(counter + "", counter + "", counter + ""));
        counter++;
        movies.add(new Movie(counter + "", counter + "", counter + ""));
        counter++;
        movies.add(new Movie(counter + "", counter + "", counter + ""));
        counter++;
        movies.add(new Movie(counter + "", counter + "", counter + ""));
        counter++;
        movies.add(new Movie(counter + "", counter + "", counter + ""));
        counter++;
        movies.add(new Movie(counter + "", counter + "", counter + ""));
        counter++;
        movies.add(new Movie(counter + "", counter + "", counter + ""));
        counter++;
        movies.add(new Movie(counter + "", counter + "", counter + ""));
        counter++;
        movies.add(new Movie(counter + "", counter + "", counter + ""));

        adapter.setLoading(false);
        adapter.notifyDataSetChanged();
    }

    private void setData()
    {
        movies.add(new Movie("Mad Max: Fury Road", "Action & Adventure", "2015"));
        movies.add(new Movie("Inside Out", "Animation, Kids & Family", "2015"));
        movies.add(new Movie("Star Wars: Episode VII - The Force Awakens", "Action", "2015"));
        movies.add(new Movie("Shaun the Sheep", "Animation", "2015"));
        movies.add(new Movie("The Martian", "Science Fiction & Fantasy", "2015"));
        movies.add(new Movie("Mission: Impossible Rogue Nation", "Action", "2015"));
        movies.add(new Movie("Up", "Animation", "2009"));
        movies.add(new Movie("Star Trek", "Science Fiction", "2009"));
        movies.add(new Movie("The LEGO Movie", "Animation", "2014"));
        movies.add(new Movie("Iron Man", "Action & Adventure", "2008"));
        movies.add(new Movie("Aliens", "Science Fiction", "1986"));
        movies.add(new Movie("Chicken Run", "Animation", "2000"));
        movies.add(new Movie("Back to the Future", "Science Fiction", "1985"));
        movies.add(new Movie("Raiders of the Lost Ark", "Action & Adventure", "1981"));
        movies.add(new Movie("Goldfinger", "Action & Adventure", "1965"));
        movies.add(new Movie("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014"));


        adapter.notifyDataSetChanged();
    }
}