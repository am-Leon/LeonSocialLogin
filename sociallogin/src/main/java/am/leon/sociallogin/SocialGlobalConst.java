package am.leon.sociallogin;

import java.util.ArrayList;
import java.util.List;

public class SocialGlobalConst {

    private String faceBookFields = "";
    private List<String> faceBookPermissions;

    private static final SocialGlobalConst instance = new SocialGlobalConst();

    public static SocialGlobalConst getInstance() {
        return instance;
    }


    String getBasicFaceBookFields() {
        if (faceBookFields.length() != 0)
            return faceBookFields;
        else
            return "name,first_name,last_name,email,id";
    }

    String getMoreFaceBookFields() {
        return "name,first_name,last_name,email,id,birthday,gender,hometown,location";
    }

    public void setFaceBookFields(String faceBookFields) {
        this.faceBookFields = faceBookFields;
    }

    List<String> getBasicFaceBookPermissions() {
        List<String> permissions = new ArrayList<>();
        permissions.add("email");
        if (faceBookPermissions != null)
            return faceBookPermissions;
        else
            return permissions;
    }

    List<String> getMoreFaceBookPermissions() {
        List<String> permissions = new ArrayList<>();
        permissions.add("user_hometown");
        permissions.add("user_birthday");
        permissions.add("user_location");
        permissions.add("user_gender");
        permissions.add("email");
        if (faceBookPermissions != null)
            return faceBookPermissions;
        else
            return permissions;
    }

    public void setFaceBookPermissions(List<String> faceBookPermissions) {
        this.faceBookPermissions = faceBookPermissions;
    }

    String getSnapChatQueries() {
        return "{me{bitmoji{avatar},displayName,externalId}}";
    }

}