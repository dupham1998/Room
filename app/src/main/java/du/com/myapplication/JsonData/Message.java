package du.com.myapplication.JsonData;

public class Message {
    private String mType;
    private String mTelephone;
    private String mContent;

    public String getmTelephone() {
        return mTelephone;
    }

    public void setmTelephone(String mTelephone) {
        this.mTelephone = mTelephone;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public Message(String mTelephone, String mContent) {
        this.mTelephone = mTelephone;
        this.mContent = mContent;
        this.mType = "MES";
    }

    public Message(String mZxingInput){
        this.mType = "MES"; this.mTelephone = ""; this.mContent = "";
        String[] mSplitInput = mZxingInput.split(":");
        this.mTelephone = mSplitInput[1];
        this.mContent = mSplitInput[2];
    }

    public String getInfoZxing(){
        String result = String.format("smsto:%s:%s", this.mTelephone, this.mContent);
        return result;
    }
}
