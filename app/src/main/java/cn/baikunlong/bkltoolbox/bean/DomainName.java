package cn.baikunlong.bkltoolbox.bean;

public class DomainName {
    private String name;
    /**
     * -1：获取失败
     * 0：获取中
     * 1：可用
     * 2：不可用
     */
    private int status;

    public DomainName(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
