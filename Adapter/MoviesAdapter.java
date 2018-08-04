package wiadevelopers.com.recyclerviewpart3.Adapter;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;
import wiadevelopers.com.recyclerviewpart3.Model.Movie;
import wiadevelopers.com.recyclerviewpart3.R;


public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private List<Movie> movies;
    private OnLoadMoreListener onLoadMoreListener;
    private Context mContext;
    private boolean isLoading = false;
    private int visibleThreshold = 1;

    public MoviesAdapter(List<Movie> movies, final Context mContext, RecyclerView recyclerView)
    {
        this.movies = movies;
        this.mContext = mContext;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                int total = linearLayoutManager.getItemCount();
                int lastVisible = linearLayoutManager.findLastVisibleItemPosition();
                //Log.i("wiaDevelopersLog", "total = " + total + "     lastVisible = " + lastVisible);
                if (isLoading == false && total <= lastVisible + visibleThreshold)
                {
                    if (onLoadMoreListener != null)
                        onLoadMoreListener.onLoadMore();
                    isLoading = true;
                }
            }
        });
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener)
    {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public boolean isLoading()
    {
        return isLoading;
    }

    public void setLoading(boolean loading)
    {
        isLoading = loading;
    }

    @Override
    public int getItemViewType(int position)
    {
        if (movies.get(position) != null)
            return 1; //Item Data
        else
            return 2; //Item Loading
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if (viewType == 1)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_row, parent, false);
            return new MovieViewHolder(view);
        }
        else if (viewType == 2)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_loading, parent, false);
            return new MovieLoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position)
    {
        if (holder instanceof MovieViewHolder)
        {
            MovieViewHolder movieViewHolder = (MovieViewHolder)holder;
            Movie movie = movies.get(position);
            movieViewHolder.txtTitle.setText(movie.getTitle());
            movieViewHolder.txtGenre.setText(movie.getGenre());
            movieViewHolder.txtYear.setText(movie.getYear());

            movieViewHolder.rltv.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(mContext, position + "", Toast.LENGTH_LONG).show();
                }
            });
        }
        else if (holder instanceof MovieLoadingViewHolder)
        {
            MovieLoadingViewHolder viewHolder = (MovieLoadingViewHolder)holder;
            viewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount()
    {
        return movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder
    {
        public TextView txtTitle;
        public TextView txtGenre;
        public TextView txtYear;
        public RelativeLayout rltv;

        public MovieViewHolder(View itemView)
        {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            txtGenre = (TextView) itemView.findViewById(R.id.txtGenre);
            txtYear = (TextView) itemView.findViewById(R.id.txtYear);

            rltv = (RelativeLayout) itemView.findViewById(R.id.rltv);
        }
    }

    public class MovieLoadingViewHolder extends RecyclerView.ViewHolder
    {

        MaterialProgressBar progressBar;

        public MovieLoadingViewHolder(View itemView)
        {
            super(itemView);
            progressBar = (MaterialProgressBar) itemView.findViewById(R.id.progressBar1);
        }
    }
}
