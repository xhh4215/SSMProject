package com.ssm.project.utils;

public class PageCalculator {
    public static int calculatedRowIndex(int pageIndex,int pageSize){
        return (pageIndex>0)?(pageIndex-1)*pageSize:0;
    }
}
