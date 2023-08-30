package PojoClasses;

import java.util.List;

public class Classes
{
    private List<WebAutomation> webAutomation;
    private List<Api> api;

    private List<Mobile> mobile;

    public WebAutomation getWebAutomation() {
        return (WebAutomation) webAutomation;
    }

    public void setWebAutomation(WebAutomation webAutomation) {
        this.webAutomation = (List<WebAutomation>) webAutomation;
    }

    public Api getApi() {
        return (Api) api;
    }

    public void setApi(Api api) {
        this.api = (List<Api>) api;
    }

    public Mobile getMobile() {
        return (Mobile) mobile;
    }

    public void setMobile(Mobile mobile) {
        this.mobile = (List<Mobile>) mobile;
    }
}
