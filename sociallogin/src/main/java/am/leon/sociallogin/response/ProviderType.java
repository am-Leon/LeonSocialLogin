package am.leon.sociallogin.response;

public enum ProviderType {

    FACEBOOK(1),
    TWITTER(2),
    GOOGLE(3),
    SNAPCHAT(4);

    private int providerType;

    ProviderType(int providerType) {
        this.providerType = providerType;
    }

    public int getProviderType() {
        return providerType;
    }

    public void setProviderType(int providerType) {
        this.providerType = providerType;
    }

}