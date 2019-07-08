package du.com.myapplication.JsonData;

public class Email {
    private String mType;
    private String mSendTo;
    private String mSubject;
    private String mBody;

    public Email(String mZxingInput){
        this.mType = "EMAIL";
        String mInput = mZxingInput.substring(7);
        String[] mSplitInput = mInput.split(";");
        for(int i = 0;i < mSplitInput.length; i ++){
            String[] result = mSplitInput[i].split(":");
            if(result[0].equals("TO")){
                this.mSendTo = result[1];
            }
            if(result[0].equals("SUB")){
                this.mSubject = result[1];
            }
            if(result[0].equals("BODY")){
                this.mBody = result[1];
            }
        }
    }
    public Email(String mType, String mSendTo, String mSubject, String mBody) {
        this.mType = "EMAIL";
        this.mSendTo = mSendTo;
        this.mSubject = mSubject;
        this.mBody = mBody;
    }

    public String getInfoZxing(){
        String result = String.format("MATMSG:TO:%s;SUB:%s;BODY:%s;",this.mSendTo, this.mSubject, this.mBody);
        return result;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmSendTo() {
        return mSendTo;
    }

    public void setmSendTo(String mSendTo) {
        this.mSendTo = mSendTo;
    }

    public String getmSubject() {
        return mSubject;
    }

    public void setmSubject(String mSubject) {
        this.mSubject = mSubject;
    }

    public String getmBody() {
        return mBody;
    }

    public void setmBody(String mBody) {
        this.mBody = mBody;
    }
}
