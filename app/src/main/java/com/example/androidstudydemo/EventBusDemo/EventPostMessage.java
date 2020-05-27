package com.example.androidstudydemo.EventBusDemo;

import org.greenrobot.eventbus.EventBus;

class EventPostMessage {
    private static final String TAG = EventPostMessage.class.getName();

    void postMessage(){
        try {
            Thread.sleep(1000);
            //发送事件
            EventMessage msg = new EventMessage("1","Hello MainActivity");
            EventBus.getDefault().post(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
