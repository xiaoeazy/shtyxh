package cn.huan.kindergarten.bean;

import java.util.List;

public class TreeBean {

	private String id;

	private String text;

	private boolean leaf;

	private String cls;
	
	private String iconCls;
	
	//private String icon;
	
	private String href;
	
	private String type;

//	private List<TreeBean> children;
//
//	public List<TreeBean> getChildren() {
//		return children;
//	}
//
//	public void setChildren(List<TreeBean> children) {
//		this.children = children;
//	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

//	public String getIcon() {
//		return icon;
//	}
//
//	public void setIcon(String icon) {
//		this.icon = icon;
//	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
}
