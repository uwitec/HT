package cn.tarena.ht.pojo;

public class Module extends BaseEntity{
	private String moduleId ;
	private Module parentModule ;
	private String name ;
	private Integer ctype ;//1:主菜单、2：左侧菜单、3：左侧菜单下的按钮
	private Integer state ;//1：启用、2：停用
	private Integer orderNo ;
	private String remark ; //描述信息
	private String id ;
	private String checked ;
	
	
	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return moduleId;
	}

	//准备pId
	public String getpId(){
		if(parentModule!=null){
			return parentModule.getModuleId() ;
		}else{
			return "0" ;
		}
	}
	
	
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public Module getParentModule() {
		return parentModule;
	}
	public void setParentModule(Module parentModule) {
		this.parentModule = parentModule;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCtype() {
		return ctype;
	}
	public void setCtype(Integer ctype) {
		this.ctype = ctype;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
