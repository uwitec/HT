package cn.tarena.ht.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tarena.ht.pojo.Dept;

public interface DeptMapper {
	
	public List<Dept> findDeptList() ;

	//mybatis中只能接受一个参数，所以多个参数需要封装-----》对象封装、map封装、注解封装（@Param）底层就是map
	public void updateState(@Param("ids") String[] deptIds, @Param("state") int state);

	public void deleteDepts(String[] deptIds);

	public void saveDept(Dept dept);

	public Dept findOneDept(String deptId);

	public void updateDept(Dept dept);

	
}
