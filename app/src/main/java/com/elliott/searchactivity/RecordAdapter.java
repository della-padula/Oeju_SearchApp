package com.elliott.searchactivity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

// RecyclerView Adapter 구현 부분
public class RecordAdapter extends RecyclerView.Adapter {
    private List<Record> winnerList = new ArrayList<>();
    private ClickListener listener;
    private Activity activity;

    // Constructor
    public RecordAdapter(Activity activity, List<Record> list) {
        this.winnerList = list;
        this.activity = activity;
    }

    // 클릭 리스너 설정
    public void setOnClickListener(ClickListener listener) {
        this.listener = listener;
    }

    // ViewHolder 초기화 (RecyclerView는 ViewHolder 패턴을 사용합니다.)
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView searchKeyword;
        TextView searchTime;
        RelativeLayout layout;

        ViewHolder(@NonNull View view) {
            super(view);

            layout = view.findViewById(R.id.layout_party_list_item);
            searchKeyword = view.findViewById(R.id.tv_detail_con_title);
            searchTime= view.findViewById(R.id.tv_detail_item_1);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_record_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder partyHolder = (ViewHolder) holder;
        Record record = winnerList.get(position);

        // 기록을 받아온 후 해당 기록 아이템 정보를 리스트의 각 아이템에 설정해주는 과정
        // 제목, 검색 시간을 설정하는 부분
        partyHolder.searchKeyword.setText(record.getKeyword());
        partyHolder.searchTime.setText("검색 시간 : " + record.getTime());
        partyHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onPositionClicked(position);
            }
        });


    }

    // 총 검색 기록 개수를 반환 ( 사용하지는 않음, 리스트 구현 시 Android 시스템이 사용 )
    @Override
    public int getItemCount() {
        return winnerList.size();
    }
}
