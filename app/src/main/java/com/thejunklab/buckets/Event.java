package com.thejunklab.buckets;

public class Event {
    String id;
    float amount;
    String memo;
    String bucketId;
    float bucketBalance;
    String dateCreated;
    String dateModified;

    public Event() {
    }

    public Event(String id, float amount, String memo, String bucketId, float bucketBalance,
                String dateCreated, String dateModified) {
        this.id = id;
        this.amount = amount;
        this.memo = memo;
        this.bucketId = bucketId;
        this.bucketBalance = bucketBalance;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getBucketId() {
        return bucketId;
    }

    public void setBucketId(String bucketId) {
        this.bucketId = bucketId;
    }

    public float getBucketBalance() {
        return bucketBalance;
    }

    public void setBucketBalance(float bucketBalance) {
        this.bucketBalance = bucketBalance;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }
}
