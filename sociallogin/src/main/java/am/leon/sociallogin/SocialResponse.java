package am.leon.sociallogin;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class SocialResponse implements Parcelable {

    public static final int Facebook = 1;
    public static final int Twitter = 2;
    public static final int Google = 3;

    private int providerType;
    private String socialID;
    private String name;
    private String firstName;
    private String lastName;
    private String birthday;
    private String gender;
    private String link;
    private String email;
    private Location location;
    private Location hometown;
    private String profilePic;


    public SocialResponse() {
    }

    private SocialResponse(Parcel in) {
        providerType = in.readInt();
        socialID = in.readString();
        name = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        birthday = in.readString();
        gender = in.readString();
        link = in.readString();
        email = in.readString();
        location = in.readParcelable(Location.class.getClassLoader());
        hometown = in.readParcelable(Location.class.getClassLoader());
        profilePic = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(providerType);
        dest.writeString(socialID);
        dest.writeString(name);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(birthday);
        dest.writeString(gender);
        dest.writeString(link);
        dest.writeString(email);
        dest.writeParcelable(location, flags);
        dest.writeParcelable(hometown, flags);
        dest.writeString(profilePic);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SocialResponse> CREATOR = new Creator<SocialResponse>() {
        @Override
        public SocialResponse createFromParcel(Parcel in) {
            return new SocialResponse(in);
        }

        @Override
        public SocialResponse[] newArray(int size) {
            return new SocialResponse[size];
        }
    };

    public int getProviderType() {
        return providerType;
    }

    public void setProviderType(int providerType) {
        this.providerType = providerType;
    }

    public String getSocialID() {
        return socialID;
    }

    public void setSocialID(String socialID) {
        this.socialID = socialID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getHometown() {
        return hometown;
    }

    public void setHometown(Location hometown) {
        this.hometown = hometown;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    @NonNull
    @Override
    public String toString() {
        return "social Login Result: \n"
                + "ProviderType: " + getProviderType() + "\n"
                + "SocialID: " + getSocialID() + "\n"
                + "FirstName: " + getFirstName() + "\n"
                + "LastName: " + getLastName() + "\n"
                + "Location: " + getLocation().getName() + "\n"
                + "Gender: " + getGender() + "\n"
                + "Birthday: " + getBirthday() + "\n"
                + "ProfileImage: " + getProfilePic() + "\n"
                + "Email: " + getEmail() + "\n";
    }

}