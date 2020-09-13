package cn.acyou.scorpio.security.model;

import org.springframework.security.core.GrantedAuthority;

public class Resources implements java.io.Serializable, GrantedAuthority {
    private static final long serialVersionUID = -5716779358068291214L;

    public Resources(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    @Override
    public String getAuthority() {
        return url;
    }

    @Override
    public String toString() {
        return "Resources{" +
                "url='" + url + '\'' +
                '}';
    }
}
