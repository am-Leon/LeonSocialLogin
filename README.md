# LeonSocialLogin

Leon Social Login is an Android library written to Integrate (Twitter, Facebook, Google) login.

## Installation

1- Add this library as a dependency in your app's build.project file.

```groovy

allprojects {  
      repositories {  
         maven { url 'https://jitpack.io' }  
      }  
   }  
   
   ```

2- Add the dependency.

```groovy

    implementation 'com.github.am-Leon:LeonSocialLogin:v1.0.6'

```

## Usage

1- open strings file and paste with your (consumerKey & consumerSecret).

```xml
<resources>

    <!--facebook-->
    <string name="facebook_app_id" translatable="false">your_facebook_app_id</string>
    <string name="fb_login_protocol_scheme" translatable="false">your_facebook_login_protocol_schema</string>

    <!--twitter-->
    <string name="twitter_CONSUMER_KEY" translatable="false">your_twitter_consumer_key</string>
    <string name="twitter_CONSUMER_SECRET" translatable="false">your_twitter_consumer_secret</string>

</resources>
```


2- inside your Activity.

```java

public class MainActivity extends AppCompatActivity implements SocialLogin.SocialLoginCallback {

    private SocialLogin socialLogin;
    private AppCompatButton socialLogin_faceBook, socialLogin_twitter, socialLogin_google;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        socialLogin = new SocialLogin(this, this);

        socialLogin_faceBook = findViewById(R.id.socialLogin_faceBook);
        socialLogin_twitter = findViewById(R.id.socialLogin_twitter);

        socialLogin_faceBook.setOnClickListener(v -> socialLogin.facebookLogin());

        socialLogin_twitter.setOnClickListener(v -> socialLogin.twitterLogin());
        
        socialLogin_google.setOnClickListener(v -> socialLogin.googleLogin());

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        socialLogin.onResult(requestCode, resultCode, data);
    }

    @Override
    public void socialLoginResponse(SocialResponse social) {
        switch (social.getProviderType()) {
            case TWITTER:
                TwitterModel twitterModel = (TwitterModel) social.getResponse();
                System.out.println(twitterModel.toString());
                break;

            case FACEBOOK:
                FacebookModel facebookModel = (FacebookModel) social.getResponse();
                System.out.println(facebookModel.toString());
                break;

            case GOOGLE:
                GoogleModel googleModel = (GoogleModel) social.getResponse();
                System.out.println(googleModel.toString());
                break;
        }
    }
}

```


## License

```text
MIT License

Copyright (c) 2020 am-Leon

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```