package com.timothy.aopdemo.data;

import org.springframework.stereotype.Repository;

@Repository
public class DataService2 {
    public int[] retrieveData() throws InterruptedException {
        //Thread.sleep(30); //-- Tested for Performance Aspect
        return new int[]{11,22,33,44};
    }
}
