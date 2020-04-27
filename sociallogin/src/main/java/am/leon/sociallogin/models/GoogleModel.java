package am.leon.sociallogin.models;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class GoogleModel implements Parcelable {

    private int id;
    private String tokenId;
    private String email;
    private String displayName;
    private String givenName;
    private String familyName;
    private Uri photoUrl;
    private String serverAuthCode;

    private GoogleModel(Parcel in) {
        id = in.readInt();
        tokenId = in.readString();
        email = in.readString();
        displayName = in.readString();
        givenName = in.readString();
        familyName = in.readString();
        photoUrl = in.readParcelable(Uri.class.getClassLoader());
        serverAuthCode = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(tokenId);
        dest.writeString(email);
        dest.writeString(displayName);
        dest.writeString(givenName);
        dest.writeString(familyName);
        dest.writeParcelable(photoUrl, flags);
        dest.writeString(serverAuthCode);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GoogleModel> CREATOR = new Creator<GoogleModel>() {
        @Override
        public GoogleModel createFromParcel(Parcel in) {
            return new GoogleModel(in);
        }

        @Override
        public GoogleModel[] newArray(int size) {
            return new GoogleModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Uri getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(Uri photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getServerAuthCode() {
        return serverAuthCode;
    }

    public void setServerAuthCode(String serverAuthCode) {
        this.serverAuthCode = serverAuthCode;
    }

    @NonNull
    @Override
    public String toString() {
        return "GoogleModel{" +
                "id=" + id +
                ", tokenId='" + tokenId + '\'' +
                ", email='" + email + '\'' +
                ", displayName='" + displayName + '\'' +
                ", givenName='" + givenName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", photoUrl=" + photoUrl +
                ", serverAuthCode='" + serverAuthCode + '\'' +
                '}';
    }
}