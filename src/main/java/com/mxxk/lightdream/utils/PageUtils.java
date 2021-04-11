package com.mxxk.lightdream.utils;

import java.util.List;

public class PageUtils<T>{
    private int totalRecord;//数据总条数
    private int pageCount;//总页数
    private int pageIndex;//当前页
    private int pageSize;//每页大小
    private List<T> list;//数据列

    public PageUtils(int totalRecord, int pageIndex, int pageSize,List<T> list) {
        this.totalRecord = totalRecord;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.list=list;
    }

    public int getPageNumber() {
        return totalRecord;
    }

    public void setPageNumber(int pageNumber) {
        this.totalRecord = pageNumber;
    }


    public int getPageCount() {

        if(totalRecord%pageSize==0){
            return totalRecord/pageSize;
        }else{
            return totalRecord/pageSize+1;
        }

    }

    public List<T> showData(){
        int firstIndex=(pageIndex-1)*pageSize;
        if(firstIndex>getPageCount()){
            firstIndex=(getPageCount()-1)*pageSize;
        }
        int lastIndex= pageIndex*pageSize;
        if(lastIndex>totalRecord){
            lastIndex=totalRecord;
        }
        return list.subList(firstIndex,lastIndex);
    }

    /**
     * 获取当前页的索引开始
     * @return
     */
    public int getIndex(){
        return (pageIndex-1)*pageSize;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
