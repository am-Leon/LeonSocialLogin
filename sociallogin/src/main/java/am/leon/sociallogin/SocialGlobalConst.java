package am.leon.sociallogin;

import java.util.ArrayList;
import java.util.List;

public class SocialGlobalConst {

    private String faceBookFields = "";
    private List<String> faceBookPermissions;

    private static SocialGlobalConst instance = new SocialGlobalConst();

    public static SocialGlobalConst getInstance() {
        return instance;
    }


    String getFaceBookFields() {
        if (faceBookFields.length() != 0)
            return faceBookFields;
        else
            return "first_name,last_name,email,id";
    }

    public void setFaceBookFields(String faceBookFields) {
        this.faceBookFields = faceBookFields;
    }

    List<String> getFaceBookPermissions() {
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

    String getSnapChatQueries() {
        return "{me{bitmoji{avatar},displayName,externalId}}";
    }

}