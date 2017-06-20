package id.arieridwan.jagatcinema.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import id.arieridwan.jagatcinema.R;

/**
 * Created by arieridwan on 17/06/2017.
 */

public class LoadingIndicator extends RelativeLayout {

    private ProgressBar mProgressBar;
    private TextView mTextView;
    private Button mButton;
    private Context mContext;
    private View mView;

    public LoadingIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.loading_indicator, this, true);
        mContext = context;
        initView();
    }

    private void initView() {
        mProgressBar = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        mTextView = (TextView) findViewById(R.id.tv_loading_indicator);
        mButton = (Button) findViewById(R.id.bt_try_again);
        mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startLoading();
            }
        });
        mView = findViewById(R.id.view);
    }

    public void startLoading(){
        mView.setVisibility(VISIBLE);
        mTextView.setText(mContext.getString(R.string.loading_text));
        mProgressBar.setVisibility(VISIBLE);
        mButton.setVisibility(GONE);
    }

    public void stopAndHide(){
        mView.setVisibility(GONE);
        mProgressBar.setVisibility(VISIBLE);
        mButton.setVisibility(GONE);
    }

    public void stopAndError(){
        mView.setVisibility(VISIBLE);
        mTextView.setText(mContext.getString(R.string.error_text));
        mProgressBar.setVisibility(GONE);
        mButton.setVisibility(VISIBLE);
    }

}
