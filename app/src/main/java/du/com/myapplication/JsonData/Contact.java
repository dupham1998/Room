package du.com.myapplication.JsonData;

public class Contact {
    private String mType;
    private String mFullName;
    private String mAddress;
    private String mEmail;
    private String mPhone;

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmFullName() {
        return mFullName;
    }

    public void setmFullName(String mFullName) {
        this.mFullName = mFullName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public Contact(String mFullName, String mAddress, String mEmail, String mPhone) {
        this.mType = "CONTACT";
        this.mFullName = mFullName;
        this.mAddress = mAddress;
        this.mEmail = mEmail;
        this.mPhone = mPhone;
    }

    public Contact(String mZxingInput){
        this.mType = "CONTACT"; this.mAddress = ""; this.mEmail = ""; this.mFullName = ""; this.mFullName = "";
        String mInput = mZxingInput.substring(7);
        String[] mSplitInput = mInput.split(";");
        for(int i = 0;i < mSplitInput.length; i ++){
            String[] result = mSplitInput[i].split(":");
            if(result[0].equals("N")) setmFullName(result[1]);
            else if(result[0].equals("TEL")) setmPhone(result[1]);
            else if(result[0].equals("EMAIL")) setmEmail(result[1]);
            else if(result[0].equals("ADR")) setmAddress(result[1]);
        }
    }

    public String getInfoZxing(){
        String result = String.format("MECARD:N:%s;TEL:%s;EMAIL:%s;ADR:%s;",
                this.mFullName, this.mPhone, this.mEmail, this.mAddress);
        return result;
    }
}

