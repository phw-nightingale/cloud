package xyz.frt.servercommon.common;

/**
 * @author phw
 * create by 93785602@qq.com
 * github on https://github.com/phw-nightingale
 * on 2020/4/28 下午5:18
 */
public class Pager {

    private Integer page;

    private Integer size;

    private String desc;

    private String order;

    public Pager() { }

    public Pager(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public Pager(Integer page, Integer size, String desc, String order) {
        this.page = page;
        this.size = size;
        this.desc = desc;
        this.order = order;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
