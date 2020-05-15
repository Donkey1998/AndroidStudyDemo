package com.example.androidstudydemo.JsonDemo;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import java.util.List;

public class FastJsonDemo {
    private Context mContext;
    public  FastJsonDemo(Context context){
        this.mContext = context;
    }

    public void  fastJsonObject(){
        String json = "{\n" +
                "\"id\":2, \"name\":\"大虾\",\n" +
                "\"price\":12.3, \"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"\n" +
                "}";
        JsonObjectBean jsonObjectBean = JSON.parseObject(json,JsonObjectBean.class);
        Toast.makeText(mContext,jsonObjectBean.toString(),Toast.LENGTH_LONG).show();

    }

    public void fastJsonArr(){
        String json = "[\n" +
                "{\n" +
                "\"id\":1, \"name\":\"大虾1\",\n" +
                "\"price\":12.3, \"imagePath\":\"http://192.168.10.165:8080/f1.jpg\"\n" +
                "}, {\n" +
                "\"id\":2, \"name\":\"大虾2\",\n" +
                "\"price\":12.5, \"imagePath\":\"http://192.168.10.165:8080/f2.jpg\"\n" +
                "} ]";
        List<JsonObjectBean> jsonArr = JSON.parseArray(json,JsonObjectBean.class);
        Toast.makeText(mContext,jsonArr.toString(),Toast.LENGTH_LONG).show();
    }

    public void fastJsonComplex(){
        String json  = "{\"data\":{\"count\":5,\"items\":[{\"id\":45,\"title\":\"坚果\"},{\"id\":132,\"title\":\"炒货\"},{\"id\":166,\"title\":\"蜜饯\"},{\"id\":195,\"title\":\"果脯\"},{\"id\":196,\"title\":\"礼盒\"}]},\"rs_code\":\"1000\",\"rs_msg\":\"success\"}";
        DataInfo dataInfo = JSON.parseObject(json,DataInfo.class);
        Toast.makeText(mContext,"获取第一个数据"+dataInfo.getData().getItems().get(0).getTitle(),Toast.LENGTH_SHORT).show();
    }


    static class JsonObjectBean{
        private  int id;
        private  String name;
        private  String imagePath;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public String getName() {
            return name;
        }

        public String getImagePath() {
            return imagePath;
        }

        @Override
        public String toString() {
            return "JsonBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", imagePath='" + imagePath + '\'' +
                    '}';
        }
        public JsonObjectBean(){

        }

        public JsonObjectBean(int id, String name, String imagePath) {
            this.id = id;
            this.name = name;
            this.imagePath = imagePath;
        }
    }


}
