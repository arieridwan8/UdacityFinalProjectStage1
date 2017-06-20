package id.arieridwan.jagatcinema.features.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.arieridwan.jagatcinema.R;
import id.arieridwan.jagatcinema.base.MvpActivity;
import id.arieridwan.jagatcinema.models.MovieDao;
import id.arieridwan.jagatcinema.utils.Constants;
import id.arieridwan.jagatcinema.utils.LoadingIndicator;

public class DetailActivity extends MvpActivity<DetailPresenter> implements DetailView {

    @BindView(R.id.ivBackdrop)
    ImageView ivBackdrop;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ivPoster)
    ImageView ivPoster;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.tvRate)
    TextView tvRate;
    @BindView(R.id.tvPopular)
    TextView tvPopular;
    @BindView(R.id.tvOverview)
    TextView tvOverview;

    private MovieDao.ResultsBean mData;

    @Override
    protected DetailPresenter onCreatePresenter() {
        return new DetailPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();

        if (i.hasExtra(Constants.MOVIE_DAO)) {
            mData = (MovieDao.ResultsBean) i.getSerializableExtra(Constants.MOVIE_DAO);
            setData();
        } else {
            noData();
        }
    }

    private void setData() {
        String backdrop_url = Constants.IMG_URL + mData.getBackdrop_path();
        String poster_url = Constants.IMG_URL + mData.getPoster_path();
        try {
            Picasso.with(this)
                    .load(backdrop_url)
                    .error(ContextCompat.getDrawable(this, R.drawable.no_image))
                    .placeholder(ContextCompat.getDrawable(this, R.drawable.place_holder))
                    .into(ivBackdrop);
            Picasso.with(this)
                    .load(poster_url)
                    .error(ContextCompat.getDrawable(this, R.drawable.no_image))
                    .placeholder(ContextCompat.getDrawable(this, R.drawable.place_holder))
                    .into(ivPoster);
        } catch (Exception e) {
            Log.e("getDataSuccess: ", e.getMessage());
        }
        tvTitle.setText(mData.getTitle());
        tvDate.setText(mData.getRelease_date());
        tvRate.setText(String.valueOf(mData.getVote_average()));
        tvPopular.setText(String.valueOf(mData.getPopularity()));
        tvOverview.setText(mData.getOverview());
    }

    private void noData() {
        Toast.makeText(activity, getString(R.string.error_text), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }

}
