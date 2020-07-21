package android.java.mvvm.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AddressModel implements Serializable {

    @SerializedName("street")
    @Expose
    public String street;


    @SerializedName("geo")
    @Expose
    public GeoModel geo;

}
