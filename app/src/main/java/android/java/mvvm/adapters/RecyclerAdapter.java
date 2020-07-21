package android.java.mvvm.adapters;

import android.content.Context;

import android.java.mvvm.R;
import android.java.mvvm.databinding.RecyclerListItemBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;


import android.java.mvvm.model.UserModel;




import java.util.List;



public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


   public interface AdapterClickListener{

        void onClicked(int position);

    }
    private List<UserModel> dataModelList;
    private Context context;
    private AdapterClickListener listener;
    public RecyclerAdapter(AdapterClickListener listener,List<UserModel> dataModelList, Context context) {
        this.dataModelList = dataModelList;
        this.context = context;
        this.listener=listener;
    }


    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        RecyclerListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recycler_list_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        UserModel userModel = dataModelList.get(position);
        holder.bind(userModel);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClicked(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public RecyclerListItemBinding itemListUserBinding;

        public ViewHolder(RecyclerListItemBinding itemListUserBinding) {
            super(itemListUserBinding.getRoot());
            this.itemListUserBinding = itemListUserBinding;
        }


        public void bind(Object obj) {
            itemListUserBinding.setVariable(BR.data, obj);
            itemListUserBinding.executePendingBindings();
        }
    }
}
