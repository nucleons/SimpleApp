package com.example.aditya.simpleapp.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.aditya.simpleapp.activity.MainActivity;
import com.example.aditya.simpleapp.R;

/**
 * Created by aditya on 13/9/16.
 */
public class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private Toolbar toolbar;

    TextView titleText;

    public static final int REQUEST_CODE_NO_INTERNET_CONNECTION = 1;
    public static final int REQUEST_CODE_INTERNET_CONNECTION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ProgressDialog showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(true);
            try {
                progressDialog.show();
            } catch (WindowManager.BadTokenException e) {

            }
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            progressDialog.setContentView(R.layout.progress_dialog);
        }
        return progressDialog;
    }

    public void closeProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    protected void setToolbar(String title){
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //No need of back button in home activity
        if((this.getClass().getName().toString()).equals(MainActivity.class.getName().toString())){
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }else {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        titleText = (TextView)toolbar.findViewById(R.id.toolbar_title);
        if(titleText!=null)
            titleText.setText(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) // Press Back Icon
        {
            finish();
        }
        //implement logic here to get selected item
        return super.onOptionsItemSelected(menuItem);
    }

    public boolean isConnectedToInternet() {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void showNoInternetAccessAlert(final AlertCallBack callback) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), getString(R.string.NoInterNetMsg),
                Snackbar.LENGTH_INDEFINITE)
                .setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isConnectedToInternet())
                            callback.alertButtonClicked(REQUEST_CODE_INTERNET_CONNECTION);
                        else
                            callback.alertButtonClicked(REQUEST_CODE_NO_INTERNET_CONNECTION);


                    }
                });
        snackbar.show();

    }

    public interface AlertCallBack {
        void alertButtonClicked(int flag);
    }
}
