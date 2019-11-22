package com.elliott.searchactivity;

// 검색 기록 각 아이템을 눌렀을 때 반응할 수 있는 이벤트를 구현하기 위한 인터페이스
// Adapter의 setOnClickListener를 통해 해당 인터페이스를 지정할 수 있다.
public interface ClickListener {

    void onPositionClicked(int position);

    void onDeleteClicked(int position);
}
