package cn.tarena.ht.pojo;


public class Role extends BaseEntity{
	private String roleId ;
	private String name ;
	private String remarks;
	private String orderNo ;
	private String checked ;
	
	//为了zTree树回显信息
	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getRoleId() {
		return roleId;
	}
	
	//ztree中需要这一方法，为了满足ztree树的结构
	public String getId() {
		return roleId;
	}
	
	
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", name=" + name + ", remarks=" + remarks + ", orderNo=" + orderNo + "]";
	}
	
}
