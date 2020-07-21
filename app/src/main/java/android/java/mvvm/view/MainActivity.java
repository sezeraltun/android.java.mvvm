package android.java.mvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.java.mvvm.R;
import android.java.mvvm.databinding.ActivityMainBinding;
import android.os.Bundle;
import android.java.mvvm.adapters.RecyclerAdapter;
import android.java.mvvm.model.UserModel;
import android.java.mvvm.viewmodel.MainViewModel;
import android.util.Log;


import java.util.List;


public class MainActivity extends AppCompatActivity  implements RecyclerAdapter.AdapterClickListener {

    private MainViewModel viewModel;

    private RecyclerAdapter adapter;
    private ProgressDialog loadingDialog;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        loadingDialog = ProgressDialog.show(this, "",
                "Loading. Please wait...");


        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.init(this);
        setRecyclerView();
        viewModel.getUserData();
        subscribeViewModel();
    }

    private void subscribeViewModel() {

        viewModel.isLoading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    loadingDialog.show();
                }
                else{
                    loadingDialog.dismiss();
                }
            }
        });

        viewModel.userData.observe(this, new Observer<List<UserModel>>() {
            @Override
            public void onChanged(List<UserModel> userModels) {
                Log.e("data changed",":"+userModels);
                if(userModels.size()>0) {
                    viewModel.isLoading.setValue(false);
                    adapter = new RecyclerAdapter(MainActivity.this, viewModel.userData.getValue(), MainActivity.this);
                    binding.recyclerView.setAdapter(adapter);
                }

            }
        });

    }

    private void setRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onClicked(int position) {
        viewModel.openDetailPage(viewModel.userData.getValue().get(position));
    }
}