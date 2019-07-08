package du.com.myapplication.JsonData;

public class Text {
    private String mType;
    private String mContent;

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public Text(String mContent) {
        this.mType = "TEXT";
        this.mContent = mContent;
    }

    public String getInfoZxing(){
        String result = String.format("%s", this.mContent);
        return result;
    }


}
