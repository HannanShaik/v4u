package com.mobifever.v4u.network.service;

public interface ServiceCallback <T> {
    void onSuccess(T t);

    void onFailure(V4UException e);
}
