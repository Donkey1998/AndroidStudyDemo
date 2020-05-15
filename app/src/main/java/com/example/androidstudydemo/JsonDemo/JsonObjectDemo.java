package com.example.androidstudydemo.JsonDemo;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonObjectDemo {
    private  Context mContext;




    public JsonObjectDemo(Context context){
        this.mContext = context;
    }

    public void JsonObject(){
        String json = "{\n" +
                "\"id\":2, \"name\":\"大虾\",\n" +
                "\"price\":12.3, \"imagePath\":\"http://192.168.10.165:8080/L05_Server/images/f1.jpg\"\n" +
                "}";
        try {
            JSONObject  jsonObject = new JSONObject(json) ;
            int id = jsonObject.optInt("id");
            String name = jsonObject.optString("name" );
            String imagePath = jsonObject.optString("imagePath");
            JsonObjectBean jsonObjectBean = new JsonObjectBean (id,name,imagePath );
            Toast.makeText(mContext, jsonObjectBean.toString(),Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void JsonObjectArr(){
        String json = "[\n" +
                "{\n" +
                "\"id\":1, \"name\":\"大虾1\",\n" +
                "\"price\":12.3, \"imagePath\":\"http://192.168.10.165:8080/f1.jpg\"\n" +
                "}, {\n" +
                "\"id\":2, \"name\":\"大虾2\",\n" +
                "\"price\":12.5, \"imagePath\":\"http://192.168.10.165:8080/f2.jpg\"\n" +
                "} ]";
        ArrayList <JsonObjectBean> arrayList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for(int i =0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(jsonObject != null){
                    int id = jsonObject.optInt("id");
                    String name = jsonObject.optString("name" );
                    String imagePath = jsonObject.optString("imagePath");
                    JsonObjectBean jsonObjectBean = new JsonObjectBean (id,name,imagePath );
                    arrayList.add(jsonObjectBean);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void JsonObjectComplex(){
        String json  = "{\"data\":{\"count\":5,\"items\":[{\"id\":45,\"title\":\"坚果\"},{\"id\":132,\"title\":\"炒货\"},{\"id\":166,\"title\":\"蜜饯\"},{\"id\":195,\"title\":\"果脯\"},{\"id\":196,\"title\":\"礼盒\"}]},\"rs_code\":\"1000\",\"rs_msg\":\"success\"}";
        try {
            //  第一层解析
            JSONObject jsonObject = new JSONObject(json);
            JSONObject data = jsonObject.optJSONObject("data");
            String rs_code = jsonObject.optString("rs_code");
            String rs_msg = jsonObject.optString("rs_msg");

            // javaBean
            DataInfo dataInfo = new DataInfo();
            dataInfo.setRs_code(rs_code);
            dataInfo.setRs_msg(rs_msg);
            DataInfo.DataBean dataBean = dataInfo.new DataBean();
            dataInfo.setData(dataBean);

            // 第二层解析
            int count = data.optInt("count");
            JSONArray items = data.optJSONArray("items");
            // javaBean
            dataBean.setCount(count);
            List<DataInfo.DataBean.ItemsBean> itemBean = new ArrayList<>();
            dataBean.setItems(itemBean);

            //第三次解析
            for(int i = 0; i < items.length(); i ++){
                JSONObject jsonObject1 = items.optJSONObject(i);
                if( jsonObject1 != null){
                    int id = jsonObject1.optInt("id");
                    String title = jsonObject1.optString("title");

                    //javabean
                    DataInfo.DataBean.ItemsBean bean = dataInfo.new DataBean().new ItemsBean();
                    bean.setId(id);
                    bean.setTitle(title);
                    itemBean.add(bean);
                }
            }

            Toast.makeText(mContext,"获取第一个数据"+dataInfo.getData().getItems().get(0).getTitle(),Toast.LENGTH_SHORT).show();


        } catch (JSONException e) {
            e.printStackTrace();
        }
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
