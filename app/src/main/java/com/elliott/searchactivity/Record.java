package com.elliott.searchactivity;

// 검색 기록 모델
public class Record {
    // id : 기본 키로 튜플을 유일하게 식별할 수 있는 식별자 (자동으로 increment 되는 변수)
    private int id;
    // keyword : 검색어
    private String keyword;
    // time : 검색 시간
    private String time;

    // Getter / Setter 함수 구현
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
