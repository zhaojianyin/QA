package com.nuc.zjy.qa.bean;

/**
 * @项目名称：QA
 * @类名称：User @类描述：用户实体类
 *
 * @author 赵建银
 * @date 2017年11月7日
 * @time 上午8:11:46
 * @version 1.0
 */
public class User {
	private Integer id;
	private String name;
	private String password;
	private String salt;
	private String headUrl;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public User() {
		super();
	}

	public User(Integer id, String name, String password, String salt, String headUrl) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.salt = salt;
		this.headUrl = headUrl;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", salt=" + salt + ", headUrl="
				+ headUrl + "]";
	}

}
