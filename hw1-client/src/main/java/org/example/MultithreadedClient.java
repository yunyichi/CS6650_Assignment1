
package org.example;
import io.swagger.client.*;
import io.swagger.client.auth.*;
import io.swagger.client.model.*;
import io.swagger.client.api.SwipeApi;

import javax.xml.crypto.Data;
import java.util.Random;

import java.io.File;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultithreadedClient {
    private final static int NUMREQUESTS = 500000;
    private static final String baseUrl = "http://54.201.34.108:8080/hw1_server_war/";
    private final static int NUMTHREADS = 200;

    public static void main(String[] args) throws InterruptedException{
        CountData countData = new CountData();
        CountDownLatch completed = new CountDownLatch(NUMREQUESTS);
        DataGeneration dataGeneration = new DataGeneration();
        ExecutorService executor = Executors.newFixedThreadPool(NUMTHREADS);
//        long startTime = System.currentTimeMillis();
//        System.out.println(startTime);
//        countData.setStartTime(startTime);
        countData.setStartTime(System.currentTimeMillis());

        for (int i = 0; i < NUMREQUESTS; i++) {
            executor.execute(new SingleThread(dataGeneration, completed, countData));
        }
        completed.await();
        executor.shutdown();
        Long end = System.currentTimeMillis();
//        long endTime = System.currentTimeMillis();
//        System.out.println(endTime);
//        countData.setEndTime(endTime);
        countData.setEndTime(System.currentTimeMillis());
        countData.calculateTotalRunTime();
        countData.calculateThroughput();


        System.out.println("Number of successful requests is: " + countData.getNumOfSuccessfulRequest());
        System.out.println("Number of Unsuccessful requests is: " + countData.getNumOfFailedRequest());
        System.out.println("Total run time is: " + countData.getTotalRunTime() + " ms");
        System.out.println("Throughput is : " + countData.getThroughput());


    }

}
