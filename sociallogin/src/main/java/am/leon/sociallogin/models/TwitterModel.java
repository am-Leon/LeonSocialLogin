package am.leon.sociallogin.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class TwitterModel implements Parcelable {

    /**
     * The UTC datetime that the user account was created on Twitter.
     */
    @SerializedName("created_at")
    private String createdAt;

    /**
     * Nullable. The user-defined UTF-8 string describing their account.
     */
    @SerializedName("description")
    private String description;

    /**
     * Nullable. The logged in user email address if available. Must have permission to access email
     * address.
     */
    @SerializedName("email")
    private String email;

    /**
     * The number of tweets this user has favorited in the account's lifetime. British spelling used
     * in the field name for historical reasons.
     */
    @SerializedName("favourites_count")
    private int favouritesCount;

    /**
     * The number of followers this account currently has. Under certain conditions of duress, this
     * field will temporarily indicate "0."
     */
    @SerializedName("followers_count")
    private int followersCount;

    /**
     * The number of users this account is following (AKA their "followings"). Under certain
     * conditions of duress, this field will temporarily indicate "0."
     */
    @SerializedName("friends_count")
    private int friendsCount;

    /**
     * The string representation of the unique identifier for this User. Implementations should use
     * this rather than the large, possibly un-consumable integer in id
     */
    @SerializedName("id_str")
    private String id;

    /**
     * Nullable. The user-defined location for this account's profile. Not necessarily a location
     * nor parseable. This field will occasionally be fuzzily interpreted by the Search service.
     */
    @SerializedName("location")
    private String location;

    /**
     * The name of the user, as they've defined it. Not necessarily a person's name. Typically
     * capped at 20 characters, but subject to change.
     */
    @SerializedName("name")
    private String fullName;

    /**
     * A HTTPS-based URL pointing to the user's avatar image.
     */
    @SerializedName("profile_image_url_https")
    private String profileImageUrl;

    /**
     * The number of tweets (including retweets) issued by the user.
     */
    @SerializedName("statuses_count")
    private int tweetsCount;

    /**
     * When true, indicates that the user has a verified account. See Verified Accounts.
     */
    @SerializedName("verified")
    private boolean verified;


    private TwitterModel(Parcel in) {
        createdAt = in.readString();
        description = in.readString();
        email = in.readString();
        favouritesCount = in.readInt();
        followersCount = in.readInt();
        friendsCount = in.readInt();
        id = in.readString();
        location = in.readString();
        fullName = in.readString();
        profileImageUrl = in.readString();
        tweetsCount = in.readInt();
        verified = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(createdAt);
        dest.writeString(description);
        dest.writeString(email);
        dest.writeInt(favouritesCount);
        dest.writeInt(followersCount);
        dest.writeInt(friendsCount);
        dest.writeString(id);
        dest.writeString(location);
        dest.writeString(fullName);
        dest.writeString(profileImageUrl);
        dest.writeInt(tweetsCount);
        dest.writeByte((byte) (verified ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TwitterModel> CREATOR = new Creator<TwitterModel>() {
        @Override
        public TwitterModel createFromParcel(Parcel in) {
            return new TwitterModel(in);
        }

        @Override
        public TwitterModel[] newArray(int size) {
            return new TwitterModel[size];
        }
    };

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFavouritesCount() {
        return favouritesCount;
    }

    public void setFavouritesCount(int favouritesCount) {
        this.favouritesCount = favouritesCount;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFriendsCount() {
        return friendsCount;
    }

    public void setFriendsCount(int friendsCount) {
        this.friendsCount = friendsCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfileImageUrl() {
        if (profileImageUrl.contains("_normal"))
            return profileImageUrl.replace("_normal", "");
        else
            return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrlHttps) {
        this.profileImageUrl = profileImageUrlHttps;
    }

    public int getTweetsCount() {
        return tweetsCount;
    }

    public void setTweetsCount(int tweetsCount) {
        this.tweetsCount = tweetsCount;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    @NonNull
    @Override
    public String toString() {
        return "Twitter Login Result: \n"
                + "createdAt: " + getCreatedAt() + "\n"
                + "description: " + getDescription() + "\n"
                + "email: " + getEmail() + "\n"
                + "favouritesCount: " + getFavouritesCount() + "\n"
                + "followersCount: " + getFollowersCount() + "\n"
                + "friendsCount: " + getFriendsCount() + "\n"
                + "id: " + getId() + "\n"
                + "Location: " + getLocation() + "\n"
                + "fullName: " + getFullName() + "\n"
                + "profileImageUrl: " + getProfileImageUrl() + "\n"
                + "tweetsCount: " + getTweetsCount() + "\n"
                + "verified: " + isVerified() + "\n";
    }

}