package am.leon.sociallogin.response;

import androidx.annotation.NonNull;

public class SocialResponse<T> {

    private T t;
    private ProviderType providerType;

    public SocialResponse() {
    }

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
                "response= " + t + '}';
    }

}