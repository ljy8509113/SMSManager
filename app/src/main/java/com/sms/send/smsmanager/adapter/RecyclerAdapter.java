package com.sms.send.smsmanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sms.send.smsmanager.R;
import com.sms.send.smsmanager.model.MyContacts;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {

    // adapter에 들어갈 list 입니다.
    private ArrayList<MyContacts> listData = new ArrayList<>();

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }

    public void addItem(MyContacts data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }

    public void addItems(ArrayList<MyContacts> array){
        listData.addAll(array);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {

        private CheckBox check;
        private TextView tvNo;
        private TextView tvName;
        private TextView tvPhone;


        ItemViewHolder(View itemView) {
            super(itemView);

            tvName= itemView.findViewById(R.id.text_name);
            tvPhone = itemView.findViewById(R.id.text_phone);
            check = itemView.findViewById(R.id.check_item);
            tvNo = itemView.findViewById(R.id.text_no);
        }

        void onBind(MyContacts data) {
            check.setChecked(data.isSelect);
            tvName.setText(data.fullName);
            tvPhone.setText(data.phone);
            tvNo.setText(data.id+1 + "");
        }
    }
}