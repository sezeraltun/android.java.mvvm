package android.java.mvvm.viewmodel;

import android.content.Context;
import android.java.mvvm.view.DetailActivity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.java.mvvm.model.UserModel;
import android.java.mvvm.repo.UserDataRepo;

import java.util.List;

public class MainViewModel extends ViewModel {

    private UserDataRepo userDataRepo;
    public LiveData<List<UserModel>> userData;
    private Context context;
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);



    public void init(Context context){
        this.context=context;
        if(userData!=null){
            return;
        }

        userDataRepo= UserDataRepo.init();


    }

    public void getUserData(){
        isLoading.setValue(true);
        userData=userDataRepo.getUserData();
    }

    public void openDetailPage(UserModel model){
        context.startActivity(DetailActivity.launchDetail(context,model));
    }



}
