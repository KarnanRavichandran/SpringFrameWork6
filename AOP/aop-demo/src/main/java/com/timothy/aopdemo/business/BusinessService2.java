package com.timothy.aopdemo.business;

import com.timothy.aopdemo.data.DataService1;
import com.timothy.aopdemo.data.DataService2;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class BusinessService2 {
    private DataService2 dataService2;

    public BusinessService2(DataService2 dataService2) {
        this.dataService2 = dataService2;
    }

    public int calMaths() throws InterruptedException {
        int[] data = dataService2.retrieveData();
       // throw new RuntimeException("Yea something went wrong");  --Tested for Logging Aspect
        return Arrays.stream(data).min().orElse(0);
    }
}
