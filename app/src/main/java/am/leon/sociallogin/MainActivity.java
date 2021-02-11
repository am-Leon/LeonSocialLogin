package am.leon.sociallogin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import am.leon.sociallogin.models.FacebookModel;
import am.leon.sociallogin.models.GoogleModel;
import am.leon.sociallogin.models.SnapChatModel;
import am.leon.sociallogin.models.TwitterModel;
import am.leon.sociallogin.response.SocialResponse;

public class MainActivity extends AppCompatActivity implements SocialLogin.SocialLoginCallback {

    private SocialLogin socialLogin;
    private static final String TAG = "MainActivity";
    private AppCompatButton socialLogin_faceBook, socialLogin_twitter, socialLogin_snapChat, socialLogin_google;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        socialLogin = new SocialLogin(this, this);

        socialLogin_faceBook = findViewById(R.id.socialLogin_faceBook);
        socialLogin_twitter = findViewById(R.id.socialLogin_twitter);
        socialLogin_snapChat = findViewById(R.id.socialLogin_snapChat);
        socialLogin_google = findViewById(R.id.socialLogin_google);

        socialLogin_faceBook.setOnClickListener(v -> socialLogin.facebookLogin());

        socialLogin_twitter.setOnClickListener(v -> socialLogin.twitterLogin());

        socialLogin_snapChat.setOnClickListener(v -> socialLogin.snapChatLogin());

        socialLogin_google.setOnClickListener(v -> socialLogin.googleLogin());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        socialLogin.onResult(requestCode, resultCode, data);
    }

    @Override
    public void socialLoginResponse(SocialResponse<?> social) {
        switch (social.getProviderType()) {
            case TWITTER:
                TwitterModel twitterModel = (TwitterModel) social.getResponse();
                Log.e(TAG, twitterModel.toString());
                break;

            case FACEBOOK:
                FacebookModel facebookModel = (FacebookModel) social.getResponse();
                Log.e(TAG, facebookModel.toString());
                break;

            case SNAPCHAT:
                SnapChatModel snapChatModel = (SnapChatModel) social.getResponse();
                Log.e(TAG, snapChatModel.toString());
                break;

            case GOOGLE:
                GoogleModel googleModel = (GoogleModel) social.getResponse();
                Log.e(TAG, googleModel.toString());
                break;
        }
    }

}