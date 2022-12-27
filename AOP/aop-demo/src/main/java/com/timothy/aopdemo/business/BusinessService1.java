package com.timothy.aopdemo.business;

import com.timothy.aopdemo.data.DataService1;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class BusinessService1 {
    private DataService1 dataService1;

    public BusinessService1(DataService1 dataService1) {
        this.dataService1 = dataService1;
    }

    public int calMaths() throws InterruptedException {
        int[] data = dataService1.retrieveData();
       // throw new RuntimeException("Yea something went wrong");  --Tested for Logging Aspect
        return Arrays.stream(data).max().orElse(0);
    }
}
