package am.leon.sociallogin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements SocialLogin.SocialLoginCallback {

    private AppCompatButton socialLogin_faceBook, socialLogin_twitter;

    private SocialLogin socialLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        socialLogin = new SocialLogin(this, this);

        socialLogin_faceBook = findViewById(R.id.socialLogin_faceBook);
        socialLogin_twitter = findViewById(R.id.socialLogin_twitter);

        socialLogin_faceBook.setOnClickListener(v -> socialLogin.facebookLogin());

        socialLogin_twitter.setOnClickListener(v -> socialLogin.twitterLogin());

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        socialLogin.onResult(requestCode, resultCode, data);
    }

    @Override
    public void socialLoginResponse(SocialResponse social) {
        System.out.println(social.toString());
    }
}