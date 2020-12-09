package com.global.technolabs.demoapp.SendNotificationPack;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAdM6Bu3g:APA91bF3RluJV9PToR0mIWW6-KrQox8PR2CtiOoa8jIp6dP3Rf5JO7zHbzPOQj8NSMW29sS9yIkjLaz_pZTgGGjh8V0CHjPYlC_3AzO4Jr4VYihGUiECNbkSdY7XPHyKRmKDFgX0BIoB"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotifcation(@Body NotificationSender body);
}

