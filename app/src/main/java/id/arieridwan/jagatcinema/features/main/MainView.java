package id.arieridwan.jagatcinema.features.main;

import id.arieridwan.jagatcinema.models.MovieDao;

/**
 * Created by arieridwan on 17/06/2017.
 */

public interface MainView {

    void startLoading();

    void stopAndHide();

    void stopAndError();

    void getDataSuccess(MovieDao item);

    void setFilterText(String text);

}
