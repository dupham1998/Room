package du.com.myapplication.JsonData;

public class Wifi {
    private String mType;
    private String mSSID;
    private String mPassWord;
    private String mSecurity;

    public String getmSSID() {
        return mSSID;
    }

    public void setmSSID(String mSSID) {
        this.mSSID = mSSID;
    }

    public String getmPassWord() {
        return mPassWord;
    }

    public void setmPassWord(String mPassWord) {
        this.mPassWord = mPassWord;
    }

    public String getmSecurity() {
        return mSecurity;
    }

    public void setmSecurity(String mSecurity) {
        this.mSecurity = mSecurity;
    }


    public Wifi(String mSSID, String mPassWord, String mSecurity) {
        this.mType = "WIFI";
        this.mSSID = mSSID;
        this.mPassWord = mPassWord;
        this.mSecurity = mSecurity;
    }

    public Wifi(String mZxingInput){
        this.mType = "WIFI";
        String mInput = mZxingInput.substring(5);
        String[] mSplitResult = mInput.split(";");
        for(int i = 0; i < mSplitResult.length; i ++){
            String[] result = mSplitResult[i].split(":");
            if(result[0].equals("S")){
                this.mSSID = result[1];
            }
            if(result[0].equals("T")){
                this.mSecurity = result[1];
            }
            if(result[0].equals("P")){
                this.mPassWord = result[1];
            }
        }
    }

    public String getInfoZxing(){
        String result = String.format("WIFI:S:%s;T:%s;P:%s;;", this.mSSID, this.mSecurity, this.mPassWord);
        return result;
    }
}
