package am.leon.sociallogin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.snapchat.kit.sdk.SnapLogin;
import com.snapchat.kit.sdk.core.controller.LoginStateController;
import com.snapchat.kit.sdk.login.models.MeData;
import com.snapchat.kit.sdk.login.models.UserDataResponse;
import com.snapchat.kit.sdk.login.networking.FetchUserDataCallback;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.models.User;

import retrofit2.Call;

import static am.leon.sociallogin.Params.FACEBOOK;
import static am.leon.sociallogin.Params.FIELDS;
import static am.leon.sociallogin.Params.GOOGLE;
import static am.leon.sociallogin.Params.RC_SIGN_IN;
import static am.leon.sociallogin.Params.SNAPCHAT;
import static am.leon.sociallogin.Params.TWITTER;

public class SocialLogin {

    private View view;
    private Context context;

    private SocialResponse model;
    private SocialLoginCallback loginCallback;

    // Google
    private GoogleSignInClient mGoogleSignInClient;
    // Facebook Callback
    private static CallbackManager callbackManager;
    // Twitter AuthClient
    private static TwitterAuthClient twitterAuthClient;

    private LoginStateController.OnLoginStateChangedListener mLoginStateChangedListener;

    private MeData snapResult;


    public SocialLogin(Context context, SocialLoginCallback loginCallback) {
        this.view = ((Activity) context).findViewById(android.R.id.content);
        this.context = context;
        this.model = new SocialResponse();
        this.loginCallback = loginCallback;

    }


    //----------------------------------------- Facebook -------------------------------------------

    private void facebookInit() {
        callbackManager = CallbackManager.Factory.create();
    }


    public void facebookLogin() {
        facebookInit();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        getFacebookInfo(loginResult);
                    }

                    @Override
                    public void onCancel() {
                        failureMessage(context.getString(R.string.canceled_by_user));
                    }

                    @Override
                    public void onError(FacebookException e) {
                        System.out.println(e.getLocalizedMessage());
                        failureMessage(context.getString(R.string.failed_authenticate_please));
                    }
                });
        LoginManager.getInstance().logInWithReadPermissions((Activity) context, SocialGlobalConst.getInstance().getFaceBookPermissions());
    }


    private void getFacebookInfo(LoginResult result) {
        GraphRequest request = GraphRequest.newMeRequest(result.getAccessToken(), ((object, response) -> {
            FacebookResponseModel facebookResponse = new Gson().fromJson(response.getJSONObject().toString(), new TypeToken<FacebookResponseModel>() {
            }.getType());

            model.setSocialID(String.valueOf(facebookResponse.getId()));
            model.setName(facebookResponse.getName());
            model.setGender(facebookResponse.getGender());
            model.setBirthday(facebookResponse.getBirthday());
            model.setLink(facebookResponse.getLink());
            model.setFirstName(facebookResponse.getFirst_name());
            model.setLastName(facebookResponse.getLast_name());
            model.setEmail(facebookResponse.getEmail());
            model.setHometown(facebookResponse.getHometown());
            model.setLocation(facebookResponse.getLocation());
            model.setProfilePic(facebookResponse.getProfilePic());
            model.setProviderType(FACEBOOK);

            System.out.println(model.toString());

            if (!model.getSocialID().isEmpty())
                loginCallback.socialLoginResponse(model);
        }));

        Bundle parameters = new Bundle();
        parameters.putString(FIELDS, SocialGlobalConst.getInstance().getFaceBookFields());
        request.setParameters(parameters);
        request.executeAsync();
    }


    //----------------------------------------- Twitter --------------------------------------------

    private void twitterInit() {
        TwitterConfig config = new TwitterConfig.Builder(context)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(context.getString(R.string.twitter_CONSUMER_KEY), context.getString(R.string.twitter_CONSUMER_SECRET)))
                .debug(false)
                .build();
        Twitter.initialize(config);
        twitterAuthClient = new TwitterAuthClient();
    }


    public void twitterLogin() {
        twitterInit();
        PackageManager pkManager = context.getPackageManager();
        try {
            PackageInfo pkgInfo = pkManager.getPackageInfo("com.twitter.android", 0);
            String getPkgInfo = pkgInfo.toString();

            if (!getPkgInfo.equals("com.twitter.android"))
                checkTwitterSession();

        } catch (PackageManager.NameNotFoundException ignored) {
            failureMessage(context.getString(R.string.install_twitter));
        }
    }


    private TwitterSession getTwitterSession() {
        return TwitterCore.getInstance().getSessionManager().getActiveSession();
    }


    private void checkTwitterSession() {
        if (getTwitterSession() == null) {
            twitterAuthClient.authorize((Activity) context, new Callback<TwitterSession>() {
                @Override
                public void success(Result<TwitterSession> result) {
                    TwitterSession twitterSession = result.data;
                    getTwitterData(twitterSession);
                }

                @Override
                public void failure(TwitterException e) {
                    failureMessage(context.getString(R.string.failed_authenticate_please));
                    e.printStackTrace();
                }
            });
        } else
            getTwitterData(getTwitterSession());
    }


    private void getTwitterData(final TwitterSession twitterSession) {
        TwitterApiClient twitterApiClient = new TwitterApiClient(twitterSession);
        final Call<User> getUserCall = twitterApiClient.getAccountService().verifyCredentials(true, false, true);
        getUserCall.enqueue(new Callback<User>() {
            @Override
            public void success(Result<User> result) {

                User user = result.data;
                model.setProviderType(TWITTER);
                model.setSocialID(user.idStr);
                model.setEmail(user.email);
                model.setLocation(new Location(user.location));
                model.setProfilePic(user.profileImageUrl);
                model.setLink(user.url);

                // TODO: 2/3/20 if we need another size of image
                // picture = user.profileImageUrlHttps.replace("_normal", "");

                try {
                    model.setFirstName(user.name.split(" ")[0]);
                    model.setLastName(user.name.split(" ")[1]);
                    model.setName(user.screenName);
                } catch (Exception e) {
                    model.setFirstName(user.name);
                }

                System.out.println(model.toString());

                if (!model.getSocialID().isEmpty())
                    loginCallback.socialLoginResponse(model);
            }

            @Override
            public void failure(TwitterException exception) {
                failureMessage(context.getString(R.string.failed_authenticate_please));
                Log.e("Twitter", "Failed to get user data " + exception.getMessage());
            }
        });
    }


//    private void gettingTwitterEmail(TwitterSession session) {
//        twitterAuthClient.requestEmail(session, new com.twitter.sdk.android.core.Callback<String>() {
//            @Override
//            public void success(Result<String> result) {
//                model.setSocialID(String.valueOf(session.getUserId()));
//                model.setFirstName(session.getUserName());
//                model.setEmail(result.data);
//                model.setProviderType(TWITTER);
//
//                if (!model.getSocialID().isEmpty())
//                    loginCallback.socialLoginResponse(model);
//            }
//
//            @Override
//            public void failure(TwitterException exception) {
//                rbar.make(view, R.string.failed_authenticate_please, Snackbar.LENGTH_SHORT).show();
//            }
//        });
//    }


    //----------------------------------------- Google ---------------------------------------------

    private void googleInit() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(context, gso);
    }


    public void googleLogin() {
        googleInit();
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        ((Activity) context).startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    private void handleGoogleResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            //Signed in successfully , show authenticated UI.
            model.setSocialID(account.getId());
            model.setProviderType(GOOGLE);
            model.setFirstName(account.getDisplayName());
            model.setLastName(account.getFamilyName());
            model.setName(account.getGivenName());
            model.setEmail(account.getEmail());
            model.setProfilePic(account.getPhotoUrl().getPath());
            model.setEmail(account.getEmail());
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCode class reference for more information.
            Log.w("Tag", "sign InResult:failed code =" + e.getStatusCode());
        }
    }


    //----------------------------------------- SnapChat -------------------------------------------

    public void snapChatLogin() {
        mLoginStateChangedListener =
                new LoginStateController.OnLoginStateChangedListener() {
                    @Override
                    public void onLoginSucceeded() {
                        Log.d("SnapkitLogin", "Login was successful");
                        getUserDetails();
                    }

                    @Override
                    public void onLoginFailed() {
                        Log.d("SnapkitLogin", "Login was unsuccessful");
                    }

                    @Override
                    public void onLogout() {
                        // when the user unlinks their account we reset the fields and make the login button visible
                        Log.d("SnapkitLogin", "User logged out");
//                        resetUserInfo();
                    }
                };

        SnapLogin.getLoginStateController(context).addOnLoginStateChangedListener(mLoginStateChangedListener);

    }


    private void getUserDetails() {
        boolean isUserLoggedIn = SnapLogin.isUserLoggedIn(context);
        if (isUserLoggedIn) {
            Log.d("SnapkitLogin", "The user is logged in");

            // set a list of the data the app wants to use - these need to mirror the snap_connect_scopes set in arrays.xml
            String query = "{me{bitmoji{avatar},displayName,externalId}}";

            SnapLogin.fetchUserData(context, query, null, new FetchUserDataCallback() {
                @Override
                public void onSuccess(@Nullable UserDataResponse userDataResponse) {
                    if (userDataResponse == null || userDataResponse.getData() == null) {
                        return;
                    }

                    snapResult = userDataResponse.getData().getMe();
                    if (snapResult == null) {
                        return;
                    }

                    // set the value of the display name
                    model.setName(userDataResponse.getData().getMe().getDisplayName());

                    // set the value of the external id
                    model.setProviderType(SNAPCHAT);
                    model.setSocialID(userDataResponse.getData().getMe().getExternalId());

                    // not all users have a bitmoji connected, if the account has bitmoji connected we load the bitmoji avatar
                    if (snapResult.getBitmojiData() != null) {
                        model.setProfilePic(snapResult.getBitmojiData().getAvatar());
                    }
                }

                @Override
                public void onFailure(boolean isNetworkError, int statusCode) {
                    Log.d("SnapkitLogin", "No user data fetched " + statusCode);
                }
            });

        }
    }


    //----------------------------------------- OnResult -------------------------------------------

    public void onResult(int requestCode, int resultCode, Intent data) {
        System.out.println("from onResult");
        if (FacebookSdk.isFacebookRequestCode(requestCode))
            callbackManager.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TwitterAuthConfig.DEFAULT_AUTH_REQUEST_CODE) {
            // Pass Twitter result to the twitterAuthClient.
            if (twitterAuthClient != null)
                twitterAuthClient.onActivityResult(requestCode, resultCode, data);
        }

        if (requestCode == RC_SIGN_IN) {
            // This Task Returned from this call is always completed , no need to attach a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleGoogleResult(task);
        }
    }


//    private static Bitmap getFacebookProfilePicture(String picLink) {
//        Bitmap bitmap = null;
//
////            String link = "https://graph.facebook.com/" + userID + "/picture?type=large";
//        if (android.os.Build.VERSION.SDK_INT > 8) {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
//                    .permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//            try {
//                URL imageURL = new URL(picLink);
//                bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
//            } catch (IOException ignored) {
//            }
//            return bitmap;
//        } else
//            return null;
//    }

    private void failureMessage(String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }


    public interface SocialLoginCallback {
        void socialLoginResponse(SocialResponse social);
    }

}