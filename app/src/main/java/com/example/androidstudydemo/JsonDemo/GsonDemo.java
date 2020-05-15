package com.example.androidstudydemo.JsonDemo;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class GsonDemo {
    private Context mcontext;

    public GsonDemo(Context context){
        this.mcontext = context;
    }

    public void GsonObject(){
        String json = "{\n" +
                "\"id\":2, \"name\":\"大虾\",\n" +
                "\"price\":12.3, \"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"\n" +
                "}";
        Gson gson = new Gson();
        JsonObjectBean jsonObjectBean = gson.fromJson(json,JsonObjectBean.class);
        Toast.makeText(mcontext,jsonObjectBean.toString(),Toast.LENGTH_LONG).show();
    }

    public void GsonObjectArr(){
        String json = "[\n" +
                "{\n" +
                "\"id\":1, \"name\":\"大虾1\",\n" +
                "\"price\":12.3, \"imagePath\":\"http://192.168.10.165:8080/f1.jpg\"\n" +
                "}, {\n" +
                "\"id\":2, \"name\":\"大虾2\",\n" +
                "\"price\":12.5, \"imagePath\":\"http://192.168.10.165:8080/f2.jpg\"\n" +
                "} ]";
         Gson gson = new Gson();
        List<JsonObjectBean> gsonArray = gson.fromJson(json,new TypeToken<List<JsonObjectBean>>() {}.getType());
        Toast.makeText(mcontext,gsonArray.toString(),Toast.LENGTH_LONG).show();
    }

    public void GsonObjectComplex(){
        String json  = "{\"data\":{\"count\":5,\"items\":[{\"id\":45,\"title\":\"坚果\"},{\"id\":132,\"title\":\"炒货\"},{\"id\":166,\"title\":\"蜜饯\"},{\"id\":195,\"title\":\"果脯\"},{\"id\":196,\"title\":\"礼盒\"}]},\"rs_code\":\"1000\",\"rs_msg\":\"success\"}";
        Gson gson = new Gson();
        DataInfo dataInfo = gson.fromJson(json,DataInfo.class);
        Toast.makeText(mcontext,"获取第一个数据"+dataInfo.getData().getItems().get(0).getTitle(),Toast.LENGTH_SHORT).show();
    }


    class JsonObjectBean{
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

        public JsonObjectBean(int id, String name, String imagePath) {
            this.id = id;
            this.name = name;
            this.imagePath = imagePath;
        }
    }
}
