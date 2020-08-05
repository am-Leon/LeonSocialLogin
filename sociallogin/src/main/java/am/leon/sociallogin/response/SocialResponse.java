package am.leon.sociallogin.response;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class SocialResponse<T> implements Parcelable {

    private T t;
    private ProviderType providerType;

    public SocialResponse() {
    }

    private SocialResponse(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
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

    public T getResponse() {
        return t;
    }

    public void setResponse(T t) {
        this.t = t;
    }

    public ProviderType getProviderType() {
        return providerType;
    }

    public void setProviderType(ProviderType providerType) {
        this.providerType = providerType;
    }

    @NonNull
    @Override
    public String toString() {
        return "SocialResponse{" +
                ", providerType= " + providerType +
                " response= " + t + '}';
    }

}