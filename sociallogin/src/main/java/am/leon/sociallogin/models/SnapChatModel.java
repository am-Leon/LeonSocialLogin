package am.leon.sociallogin.models;

import com.google.gson.annotations.SerializedName;

public class SnapChatModel {

    @SerializedName("displayName")
    private String displayName;
    @SerializedName("externalId")
    private String userID;
    @SerializedName("avatar")
    private String avatar;


    public SnapChatModel() {
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getUserID() {
        return this.userID;
    }

}