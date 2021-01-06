package com.jk.pojo;

import lombok.Data;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: 李旺
 * Date: 2020/12/9
 * Time: 11:17
 */
@Data
public class TreeBean {
    private Integer id;
    private String text;
    private String href;
    private Integer pid;
    private boolean selectable;
    private List<TreeBean> nodes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    public List<TreeBean> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeBean> nodes) {
        this.nodes = nodes;
    }
}
