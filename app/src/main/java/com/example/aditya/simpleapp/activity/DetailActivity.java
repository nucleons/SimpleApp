package com.example.aditya.simpleapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aditya.simpleapp.R;
import com.example.aditya.simpleapp.base.BaseActivity;
import com.example.aditya.simpleapp.helper.ImageLoader;
import com.example.aditya.simpleapp.helper.IntentHelper;
import com.example.aditya.simpleapp.model.Content;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends BaseActivity {

    private Content content;

    @Bind(R.id.ad_im_image)
    ImageView vImage;

    @Bind(R.id.ad_tv_detail)
    TextView vDetailText;

    @Bind(R.id.ad_tv_title)
    TextView vTitleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setToolbar(getString(R.string.detail));
        getDataFromIntent();
        setUpViews();
    }

    private void getDataFromIntent(){
        this.content = IntentHelper.getContentFromIntent(getIntent());
    }

    private void setUpViews(){
        if(content!=null){
            if(content.getTitle()!=null){
                vTitleText.setText(content.getTitle());
            }
            if(content.getDescription()!=null){
                vDetailText.setText(content.getDescription());
            }
            if(content.getImageUrl()!=null && !content.getImageUrl().isEmpty()){
                ImageLoader.getInstance().loadImage(content.getImageUrl(),vImage);
            }
        }
    }
}
