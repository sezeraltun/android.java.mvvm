package android.java.mvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.java.mvvm.R;
import android.java.mvvm.databinding.ActivityDetailBinding;
import android.os.Bundle;


import android.java.mvvm.viewmodel.DetailViewModel;

import android.java.mvvm.model.UserModel;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        getExtrasFromIntent();
    }
    public static Intent launchDetail(Context context, UserModel model) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("model", model);
        return intent;
    }
    private void getExtrasFromIntent() {
        UserModel usermodel = (UserModel) getIntent().getSerializableExtra("model");
        DetailViewModel detailViewModel = new DetailViewModel(usermodel);
        binding.setDetailViewModel(detailViewModel);
        setTitle(usermodel.name);
    }

}