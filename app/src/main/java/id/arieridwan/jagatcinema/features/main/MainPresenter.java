package id.arieridwan.jagatcinema.features.main;

import android.util.Log;
import id.arieridwan.jagatcinema.base.BasePresenter;
import id.arieridwan.jagatcinema.models.MovieDao;
import id.arieridwan.jagatcinema.utils.Constants;
import rx.Subscriber;

/**
 * Created by arieridwan on 17/06/2017.
 */

public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(MainView view) {
        super.attachView(view);
    }

    void loadData(final String filter, String apiKey) {
        view.startLoading();
        addSubscribe(apiServices.getPopularMovie(filter,apiKey),
                new Subscriber<MovieDao>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("OnError", e.getMessage());
                view.stopAndError();
            }

            @Override
            public void onNext(MovieDao item) {
                String text;
                if(filter.equals(Constants.popular)){
                    text = "Popular";
                }else {
                    text = "Top Rated";
                }
                view.getDataSuccess(item);
                view.setFilterText(text);
                view.stopAndHide();
            }
        });
    }

}
