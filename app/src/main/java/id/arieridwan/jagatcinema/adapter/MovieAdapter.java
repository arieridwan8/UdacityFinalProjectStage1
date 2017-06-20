package id.arieridwan.jagatcinema.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.arieridwan.jagatcinema.R;
import id.arieridwan.jagatcinema.features.detail.DetailActivity;
import id.arieridwan.jagatcinema.models.MovieDao;
import id.arieridwan.jagatcinema.utils.Constants;

/**
 * Created by arieridwan on 16/06/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context mContext;
    private List<MovieDao.ResultsBean> mList;
    private MovieDao.ResultsBean mData;

    public MovieAdapter(List<MovieDao.ResultsBean> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_main, parent, false);
        mContext = parent.getContext();
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        mData = mList.get(position);
        String image_url = Constants.IMG_URL + mData.getPoster_path();
        try {
            Picasso.with(mContext)
                    .load(image_url)
                    .error(ContextCompat.getDrawable(mContext, R.drawable.no_image))
                    .placeholder(ContextCompat.getDrawable(mContext, R.drawable.place_holder))
                    .into(holder.mImageThumbnail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return (mList == null) ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_thumbnail)
        ImageView mImageThumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, DetailActivity.class);
                    i.putExtra(Constants.MOVIE_DAO, mList.get(getAdapterPosition()));
                    mContext.startActivity(i);
                }
            });
        }

    }
}
