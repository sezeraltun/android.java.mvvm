package android.java.mvvm.repo;

import android.java.mvvm.adapters.RecyclerAdapter;
import android.java.mvvm.model.UserModel;
import android.java.mvvm.service.JSONPlaceholderApi;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserDataRepo {

    private String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private MutableLiveData<List<UserModel>> liveData = new MutableLiveData<>();
    Retrofit retrofit;
    RecyclerAdapter recyclerViewAdapter;
    public static UserDataRepo repo;

    public static UserDataRepo init(){

        if(repo==null)
        {
            repo=new UserDataRepo();
        }

        List<UserModel> list = new ArrayList<>();

        repo.liveData.setValue(list);

        return repo;


    }

    public MutableLiveData<List<UserModel>> getUserData() {



        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())//JSON RETROFİTE BİLDİRDİ
                .build();

        JSONPlaceholderApi placeholderApi = retrofit.create(JSONPlaceholderApi.class); //interface çagırdık
        retrofit2.Call<List<UserModel>> call = placeholderApi.getData();

        call.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(retrofit2.Call<List<UserModel>> call, Response<List<UserModel>> response) {
                List<UserModel> reponseList = response.body();
                liveData.setValue(reponseList);

            }

            @Override
            public void onFailure(retrofit2.Call<List<UserModel>> call, Throwable t) {
                Log.e("error","error");
            }

        });

        return liveData;

    }

}
