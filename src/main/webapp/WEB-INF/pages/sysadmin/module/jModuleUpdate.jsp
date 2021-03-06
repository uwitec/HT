<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>模块新增</title>
</head>

<body>
<form name="icform" method="post">

<div id="menubar">
<div id="middleMenubar">
<div id="innerMenubar">
  <div id="navMenubar">
<ul>
	<li id="save"><a href="#" onclick="formSubmit('update','_self');this.blur();">保存</a></li>
	<li id="back"><a href="#" onclick="window.history.go(-1)">返回</a></li>

</ul>
  </div>
</div>
</div>
</div>
   
  <div class="textbox-title">
	<img src="../../staticfile/skin/default/images/icon/currency_yen.png"/>
    模块新增
  </div> 
  
<div>


<div class="eXtremeTable" >
<table id="ec_table" class="tableRegion" width="98%" >

	
	
	
	<tbody>
	<tr>
		<input type="hidden" name="moduleId" value="${module.moduleId }"/>
	</tr>
	
	<tr class="odd">
		<td>模块名称</td>
		<td><input type="text" name="name" id="name" value="${module.name }"></td>
	</tr>
	<tr>
		<td>上级模块</td>
		<td>
			<select name="parentModule.moduleId">
				<option value=" ">没有上级</option>
				<c:forEach items="${moduleList }" var="m">
					<option value="${m.moduleId }"
						<c:if test="${m.moduleId==module.parentModule.moduleId }" >selected="selected"</c:if>
					>${m.name }</option>
				</c:forEach>
			</select>
		</td>
	</tr>
	
	<tr>
		<td>模块类型</td>
		<td>
			<select name="ctype" style="width:120px">
				<option>---请选择---</option>
				<option value="1" <c:if test="module.ctype==1">selected="selected"</c:if> >主菜单</option>
				<option value="2" <c:if test="module.ctype==2">selected="selected"</c:if> >左侧菜单</option>
				<option value="3" <c:if test="module.ctype==3">selected="selected"</c:if> >按钮</option>
			</select>
		</td>
	</tr>
	
	<tr class="odd">
		<td>排序号</td>
		<td><input type="text" name="orderNo" id="orderNo" value="${module.orderNo }"></td>
	</tr>
	
	<tr class="odd">
		<td>备注信息</td>
		<td><textarea style="width: 1050px; height: 345px;" name="remark">${module.remark }</textarea></td>
	</tr>
	
	</tbody>
</table>
</div>
 
</div>
 
 
</form>
</body>
</html>

