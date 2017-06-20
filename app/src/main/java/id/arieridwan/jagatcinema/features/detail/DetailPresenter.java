package id.arieridwan.jagatcinema.features.detail;

import id.arieridwan.jagatcinema.base.BasePresenter;

/**
 * Created by arieridwan on 17/06/2017.
 */

public class DetailPresenter extends BasePresenter<DetailView> {

    public DetailPresenter(DetailView view) {
        super.attachView(view);
    }

    public void loadData() {
        //TODO call api review and trailer
    }

}
