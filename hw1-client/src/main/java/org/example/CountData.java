package org.example;

import java.util.concurrent.CountDownLatch;

public class CountData {
    private int numOfSuccessfulRequest;
    private long startTime = 0;
    private long endTime = 0;
    private int numOfFailedRequest;
    private long totalRunTime;
    private double throughput;

    public CountData() {
        this.numOfSuccessfulRequest = 0;
        this.numOfFailedRequest = 0;
        this.totalRunTime = 0;
        this.throughput = 0;
        this.startTime = 0;
        this.endTime = 0;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getNumOfSuccessfulRequest() {
        return numOfSuccessfulRequest;
    }

    public void incNumOfSuccessfulRequest() {
        numOfSuccessfulRequest += 1;
    }

    public int getNumOfFailedRequest() {
        return numOfFailedRequest;
    }

    public void incNumOfFailedRequest() {
        numOfFailedRequest += 1;
    }

    public long getTotalRunTime() {
        return totalRunTime;
    }

    public void calculateTotalRunTime() {
        this.totalRunTime = this.endTime - this.startTime;
    }


    public void calculateThroughput() {
        this.throughput = (numOfSuccessfulRequest + numOfFailedRequest) / ((totalRunTime*1.0)/1000);
    }
    public double getThroughput() {
        return throughput;
    }
}
