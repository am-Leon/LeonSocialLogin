package am.leon.sociallogin;

import java.util.ArrayList;
import java.util.List;

public class SocialGlobalConst {

    private String twitter_CONSUMER_KEY;
    private String twitter_CONSUMER_SECRET;

    private String faceBookFields;
    private List<String> faceBookPermissions;

    private static SocialGlobalConst instance = new SocialGlobalConst();

    public static SocialGlobalConst getInstance() {
        return instance;
    }

    public String getTwitter_CONSUMER_KEY() {
        return twitter_CONSUMER_KEY;
    }

    public void setTwitter_CONSUMER_KEY(String twitter_CONSUMER_KEY) {
        this.twitter_CONSUMER_KEY = twitter_CONSUMER_KEY;
    }

    public String getTwitter_CONSUMER_SECRET() {
        return twitter_CONSUMER_SECRET;
    }

    public void setTwitter_CONSUMER_SECRET(String twitter_CONSUMER_SECRET) {
        this.twitter_CONSUMER_SECRET = twitter_CONSUMER_SECRET;
    }

    public String getFaceBookFields() {
        if (faceBookFields.length() != 0)
            return faceBookFields;
        else
            return "first_name,last_name,email,id";
    }

    public void setFaceBookFields(String faceBookFields) {
        this.faceBookFields = faceBookFields;
    }

    public List<String> getFaceBookPermissions() {
        List<String> permissions = new ArrayList<>();
        permissions.add("public_profile");
        permissions.add("email");
        if (faceBookPermissions != null)
            return faceBookPermissions;
        else
            return permissions;
    }

    public void setFaceBookPermissions(List<String> faceBookPermissions) {
        this.faceBookPermissions = faceBookPermissions;
    }

}