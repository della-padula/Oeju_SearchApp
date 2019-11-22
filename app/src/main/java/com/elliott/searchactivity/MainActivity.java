package com.elliott.searchactivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.elliott.searchactivity.result.ResultActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button searchButton;
    EditText searchEditText;
    ImageButton recordButton;
    ImageButton delButton;
    Context context = this;

    private final String dbName = "searchDB";
    private final String tableName = "searchTABLE";

    private static final String TAG_KEYWORD = "keyword";
    private static final String TAG_TIME = "time";

    SQLiteDatabase searchDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchButton = findViewById(R.id.btn_search);
        recordButton = findViewById(R.id.ib_record);
        searchEditText = findViewById(R.id.et_search_content);
        delButton = findViewById(R.id.del_edt_content);

        // 검색 어 입력 부분에 Debounce를 적용하여 입력 할 때마다 특정 이벤트 발생
        EditTextDebounce.create(searchEditText).watch(new EditTextDebounce.DebounceCallback() {
            @Override
            public void onFinished(@NonNull String result) {
                if (result.length() > 0) {
                    // 지우기 버튼 활성화
                    // 검색하기 버튼 활성화
                    delButton.setEnabled(true);
                    delButton.setVisibility(View.VISIBLE);
                    searchButton.setEnabled(true);
                    searchButton.setBackgroundColor(getResources().getColor(R.color.btnActive));
                    searchButton.setTextColor(getResources().getColor(R.color.semiBlack));
                } else {
                    // 지우기 버튼 비활성화
                    // 검색하기 버튼 비활성화
                    delButton.setEnabled(false);
                    delButton.setVisibility(View.GONE);
                    searchButton.setEnabled(false);
                    searchButton.setBackgroundColor(getResources().getColor(R.color.inActiveBtn));
                    searchButton.setTextColor(getResources().getColor(R.color.inActiveText));
                }
            }
        });

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchEditText.setText("");
            }
        });

        // 검색하긱 버튼 누를 때 실행되는 함수
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 검색 시간을 데이터베이스에 저장할 때 다음과 같은 형식으로 저장한다.
                // yyyy : 연도, MM : 월, dd : 일, HH : 시, mm : 분 - 이 부분은 변경하지 말 것.
                // 나머지 부분만 커스터마이즈하여 형식 변경 가능.
                SimpleDateFormat format = new SimpleDateFormat ( "yyyy년 MM월 dd일 HH시 mm분");

                Date timeData = new Date();
                String time = format.format(timeData);

                // 데이터베이스 테이블에 튜플을 추가한다.
                final DatabaseHelper db = new DatabaseHelper(context);
                db.add(searchEditText.getText().toString(), time);

                // 추가 후 검색 결과 화면으로 이동한다.
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                BaseData.searchKeyword = searchEditText.getText().toString();
                startActivity(intent);
            }
        });

        // 검색 기록 화면으로 이동한다.
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                startActivity(intent);
            }
        });
    }
}
