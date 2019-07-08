package du.com.myapplication.JsonData;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Helper {

    final static String CONTACT = "CONTACT";
    final static String EMAIL = "EMAIL";
    final static String MESSAGE = "MES";
    final static String TEXT = "TEXT";
    final static String URL = "URL";
    final static String WIFI = "WIFI";

    public String ZxingToJson(String mZxingInput){
        String result = "";
        if(mZxingInput.contains("MECARD")){
            Contact mContact = new Contact(mZxingInput);
            Gson mGson = new Gson();
            result = mGson.toJson(mContact);
        }
        else if(mZxingInput.contains("smsto")){
            Message mMessage = new Message(mZxingInput);
            Gson mGson = new Gson();
            result = mGson.toJson(mMessage);
        }
        else if(mZxingInput.contains("http")){
            Url mUrl = new Url(mZxingInput);
            Gson mGson = new Gson();
            result = mGson.toJson(mUrl);
        }
        else if(mZxingInput.contains("WIFI")){
            Wifi mWifi = new Wifi(mZxingInput);
            Gson mGson = new Gson();
            result = mGson.toJson(mWifi);
        }
        else if(mZxingInput.contains("MATMSG")){
            Email mEmail = new Email(mZxingInput);
            Gson mGson = new Gson();
            result = mGson.toJson(mEmail);
        }
        else {
            Text mText = new Text(mZxingInput);
            Gson mGson = new Gson();
            result = mGson.toJson(mText);
        }
        return result;
    }

    public String JsonToZxing(String mJsonInput){
        String result = "";
        Gson mGson = new Gson();

        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(mJsonInput);
        JsonObject rootElement = root.getAsJsonObject();

        String mType = rootElement.get("mType").getAsString();
        if(mType.equals(Helper.CONTACT)){
            Contact contact = mGson.fromJson(mJsonInput, Contact.class);
            result = contact.getInfoZxing();
        }
        else if(mType.equals(Helper.EMAIL)){
            Email email = mGson.fromJson(mJsonInput, Email.class);
            result = email.getInfoZxing();
        }
        else if(mType.equals(Helper.MESSAGE)){
            Message message = mGson.fromJson(mJsonInput, Message.class);
            result = message.getInfoZxing();
        }
        else if(mType.equals(Helper.TEXT)){
            Text text = mGson.fromJson(mJsonInput, Text.class);
            result = text.getInfoZxing();
        }
        else if(mType.equals(Helper.URL)){
            Url mUrl = mGson.fromJson(mJsonInput, Url.class);
            result = mUrl.getInfoZxing();
        }
        else if(mType.equals(Helper.WIFI)){
            Wifi mWifi = mGson.fromJson(mJsonInput, Wifi.class);
            result = mWifi.getInfoZxing();
        }
        return result;
    }
}
