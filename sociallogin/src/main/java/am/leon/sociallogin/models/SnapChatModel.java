package am.leon.sociallogin.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class SnapChatModel implements Parcelable {

    @SerializedName("displayName")
    private String displayName;
    @SerializedName("externalId")
    private String userID;
    @SerializedName("avatar")
    private String avatar;


    public SnapChatModel() {
    }

    private SnapChatModel(Parcel in) {
        displayName = in.readString();
        userID = in.readString();
        avatar = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(displayName);
        dest.writeString(userID);
        dest.writeString(avatar);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SnapChatModel> CREATOR = new Creator<SnapChatModel>() {
        @Override
        public SnapChatModel createFromParcel(Parcel in) {
            return new SnapChatModel(in);
        }

        @Override
        public SnapChatModel[] newArray(int size) {
            return new SnapChatModel[size];
        }
    };

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

    @NonNull
    @Override
    public String toString() {
        return "SnapChatModel{" +
                "displayName='" + displayName + '\'' +
                ", userID='" + userID + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

}