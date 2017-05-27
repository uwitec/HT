package cn.tarena.ht.service;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tarena.ht.mapper.DeptMapper;
import cn.tarena.ht.pojo.Dept;

@Service//多个实现类
public class DeptServiceImpl implements DeptService {
	@Resource
	private DeptMapper deptMapper;
	
	@Override
	public List<Dept> findDeptList() {
		return deptMapper.findDeptList();
	}

	//改变部门的状态
	@Override
	public void updateState(String[] deptIds, int state) {
		deptMapper.updateState(deptIds,state) ;
		
	}

	@Override
	public void deleteDepts(String[] deptIds) {
		deptMapper.deleteDepts(deptIds);
		
	}

	
	@Override
	public void saveDept(Dept dept) {
		dept.setState(1);
		dept.setCreateTime(new Date());
		
		deptMapper.saveDept(dept) ;
	}

	//查询部门
	@Override
	public Dept findOneDept(String deptId) {
		return deptMapper.findOneDept(deptId) ;
	}


	@Override
	public void updateDept(Dept dept) {
		
		dept.setCreateTime(new Date());
		
		deptMapper.updateDept(dept);
		
	}

}
