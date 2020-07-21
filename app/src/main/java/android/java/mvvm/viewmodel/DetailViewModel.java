package android.java.mvvm.viewmodel;

import android.java.mvvm.model.UserModel;

public class DetailViewModel {

    private UserModel usermodel;
    public DetailViewModel(UserModel model) {
        this.usermodel = model;
    }

    public String getName(){
        return usermodel.name;
    }

    public String getAddress(){

        return usermodel.address.street;
    }

    public String getPhone(){

        return usermodel.phone;
    }

}
