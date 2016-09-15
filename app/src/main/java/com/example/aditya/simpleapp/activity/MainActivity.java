package com.example.aditya.simpleapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.aditya.simpleapp.R;
import com.example.aditya.simpleapp.adapter.ListingAdapter;
import com.example.aditya.simpleapp.base.BaseActivity;
import com.example.aditya.simpleapp.helper.IntentHelper;
import com.example.aditya.simpleapp.helper.SpacesItemDecoration;
import com.example.aditya.simpleapp.model.Content;
import com.example.aditya.simpleapp.network.NetworkService;
import com.example.aditya.simpleapp.network.ServiceCallback;
import com.example.aditya.simpleapp.network.SimpleAppException;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements ListingAdapter.ContentItemListener, BaseActivity.AlertCallBack {

    private NetworkService networkService;

    @Bind(R.id.recyclerView)
    RecyclerView vRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar(getString(R.string.listing));
        networkService = new NetworkService();
        fetchData();
    }

    private void fetchData(){
        if(isConnectedToInternet()) {
            showProgressDialog();
            networkService.getListingData(new ServiceCallback<List<Content>>() {
                @Override
                public void onSuccess(List<Content> contents) {
                    if (contents != null) {
                        initialiseContents(contents);
                    }
                    closeProgressDialog();
                }

                @Override
                public void onFailure(SimpleAppException exception) {
                    closeProgressDialog();
                }
            });
        }else {
            showNoInternetAccessAlert(this);
        }
    }

    private void initialiseContents(List<Content> contents){
        ListingAdapter adapter = new ListingAdapter(contents,this);
        vRecyclerView.addItemDecoration(new SpacesItemDecoration(getResources().
                getDimensionPixelSize(R.dimen.size_6)
                ,getResources().getDimensionPixelSize(R.dimen.size_6),
                getResources().getDimensionPixelSize(R.dimen.size_9),
                getResources().getDimensionPixelSize(R.dimen.size_9)));

        vRecyclerView.setLayoutManager(new LinearLayoutManager(vRecyclerView.getContext(),
                LinearLayoutManager.VERTICAL, false));
        vRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Content content) {
        if(content!=null){
            startActivity(IntentHelper.getDetailIntent(content,this));
        }
    }

    @Override
    public void alertButtonClicked(int flag) {
        if(flag == BaseActivity.REQUEST_CODE_INTERNET_CONNECTION){
            fetchData();
        }
    }
}
