package am.leon.sociallogin.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class GoogleModel implements Parcelable {

    private String id;
    private String email;
    private String displayName;
    private String givenName;
    private String familyName;
    private String photoUrl;


    private GoogleModel(Parcel in) {
        id = in.readString();
        email = in.readString();
        displayName = in.readString();
        givenName = in.readString();
        familyName = in.readString();
        photoUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(email);
        dest.writeString(displayName);
        dest.writeString(givenName);
        dest.writeString(familyName);
        dest.writeString(photoUrl);
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @NonNull
    @Override
    public String toString() {
        return "GoogleModel{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", displayName='" + displayName + '\'' +
                ", givenName='" + givenName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", photoUrl=" + photoUrl +
                '}';
    }
}