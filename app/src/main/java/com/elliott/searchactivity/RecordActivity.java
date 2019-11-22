package com.elliott.searchactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.elliott.searchactivity.result.ResultActivity;

import java.util.ArrayList;

// 검색 기록 화면
public class RecordActivity extends AppCompatActivity implements ClickListener {

    public ImageButton backImageButton;
    public RecyclerView recordListView;
    private RecordAdapter adapter;
    private ArrayList<Record> recordList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        recordListView = findViewById(R.id.rv_party_list);
        backImageButton = findViewById(R.id.ib_exit_add_story);

        // 데이터베이스 테이블로부터 리스트를 받아온다.
        final DatabaseHelper db = new DatabaseHelper(this);
        recordList.addAll(db.getAll());

        // RecyclerView 기본 설정 부분
        adapter = new RecordAdapter(this, recordList);
        adapter.setOnClickListener(this);
        recordListView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        recordListView.setHasFixedSize(true);
        recordListView.setAdapter(adapter);

        // 뒤로 가기 버튼 클릭 시 나가는 부분
        backImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onPositionClicked(int position) {
        // Record Item Click Listener
        Record record = recordList.get(position);
        Intent intent = new Intent(RecordActivity.this, ResultActivity.class);
        BaseData.searchKeyword = record.getKeyword();
        startActivity(intent);
    }

    @Override
    public void onDeleteClicked(int position) {

    }
}
