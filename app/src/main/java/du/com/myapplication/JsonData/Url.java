package du.com.myapplication.JsonData;

public class Url {

    private String mType;
    private String mUrl;

    public String getmType() {
        return mType;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public Url(String mType, String mUrl) {
        this.mType = "URL";
        this.mType = mType;
        this.mUrl = mUrl;
    }

    public Url(String mZxingInput){
        this.mType = "URL"; this.mUrl = "";
        this.mUrl = mZxingInput;
    }

    public String getInfoZxing(){
        String result = String.format("%s", this.mUrl);
        return result;
    }
}
