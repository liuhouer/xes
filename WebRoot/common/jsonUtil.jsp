<%@ page language="java" import="java.util.List,com.bruce.util.*" pageEncoding="utf-8"%>
<%!
/**
 *获得单个对象json,格式：
 {"User":{"getName":"名字","getPassword":"","getEnable":"","getUserType":"","getEmail":"aasf@sa.com","getRoleCode":"","getId":"11"}}
 */

private static final String $json(Object obj) throws Exception{
	return JsonUtil.getJsonObject(obj);
}
/**
*获得对象列表json,格式：
{"UserList":[{"getName":"名字1","getPassword":"","getEnable":"","getUserType":"","getEmail":"aasf@sa.com1","getRoleCode":"","getId":"111"},{"getName":"名字1","getPassword":"","getEnable":"","getUserType":"","getEmail":"aasf@sa.com1","getRoleCode":"","getId":"111"},{"getName":"名字1","getPassword":"","getEnable":"","getUserType":"","getEmail":"aasf@sa.com1","getRoleCode":"","getId":"111"},{"getName":"名字1","getPassword":"","getEnable":"","getUserType":"","getEmail":"aasf@sa.com1","getRoleCode":"","getId":"111"},{"getName":"名字1","getPassword":"","getEnable":"","getUserType":"","getEmail":"aasf@sa.com1","getRoleCode":"","getId":"111"},{"getName":"名字1","getPassword":"","getEnable":"","getUserType":"","getEmail":"aasf@sa.com1","getRoleCode":"","getId":"111"},{"getName":"名字1","getPassword":"","getEnable":"","getUserType":"","getEmail":"aasf@sa.com1","getRoleCode":"","getId":"111"},{"getName":"名字1","getPassword":"","getEnable":"","getUserType":"","getEmail":"aasf@sa.com1","getRoleCode":"","getId":"111"},{"getName":"名字1","getPassword":"","getEnable":"","getUserType":"","getEmail":"aasf@sa.com1","getRoleCode":"","getId":"111"},{"getName":"名字1","getPassword":"","getEnable":"","getUserType":"","getEmail":"aasf@sa.com1","getRoleCode":"","getId":"111"},{"getName":"名字1","getPassword":"","getEnable":"","getUserType":"","getEmail":"aasf@sa.com1","getRoleCode":"","getId":"111"},{"getName":"名字1","getPassword":"","getEnable":"","getUserType":"","getEmail":"aasf@sa.com1","getRoleCode":"","getId":"111"},{"getName":"名字1","getPassword":"","getEnable":"","getUserType":"","getEmail":"aasf@sa.com1","getRoleCode":"","getId":"111"},{"getName":"名字1","getPassword":"","getEnable":"","getUserType":"","getEmail":"aasf@sa.com1","getRoleCode":"","getId":"111"}]}
*/
private static final String $json(List objs) throws Exception{
	return JsonUtil.getJsonObject(objs);
}

/**
*获得对象列表以及page对象json,格式：
{"page":"{\"pagination\":{\"range\":\"0\",\"start\":\"10\",\"totalRecord\":\"0\",\"maxIndexPages\":\"5\",\"totalPage\":\"0\"}}","items":"{\"userList\":[{\"id\":\"111\",\"email\":\"aasf@sa.com1\",\"name\":\"名字1\",\"enable\":\"\",\"roleCode\":\"\",\"password\":\"\",\"userType\":\"\"},{\"id\":\"111\",\"email\":\"aasf@sa.com1\",\"name\":\"名字1\",\"enable\":\"\",\"roleCode\":\"\",\"password\":\"\",\"userType\":\"\"},{\"id\":\"111\",\"email\":\"aasf@sa.com1\",\"name\":\"名字1\",\"enable\":\"\",\"roleCode\":\"\",\"password\":\"\",\"userType\":\"\"},{\"id\":\"111\",\"email\":\"aasf@sa.com1\",\"name\":\"名字1\",\"enable\":\"\",\"roleCode\":\"\",\"password\":\"\",\"userType\":\"\"},{\"id\":\"111\",\"email\":\"aasf@sa.com1\",\"name\":\"名字1\",\"enable\":\"\",\"roleCode\":\"\",\"password\":\"\",\"userType\":\"\"},{\"id\":\"111\",\"email\":\"aasf@sa.com1\",\"name\":\"名字1\",\"enable\":\"\",\"roleCode\":\"\",\"password\":\"\",\"userType\":\"\"},{\"id\":\"111\",\"email\":\"aasf@sa.com1\",\"name\":\"名字1\",\"enable\":\"\",\"roleCode\":\"\",\"password\":\"\",\"userType\":\"\"},{\"id\":\"111\",\"email\":\"aasf@sa.com1\",\"name\":\"名字1\",\"enable\":\"\",\"roleCode\":\"\",\"password\":\"\",\"userType\":\"\"},{\"id\":\"111\",\"email\":\"aasf@sa.com1\",\"name\":\"名字1\",\"enable\":\"\",\"roleCode\":\"\",\"password\":\"\",\"userType\":\"\"},{\"id\":\"111\",\"email\":\"aasf@sa.com1\",\"name\":\"名字1\",\"enable\":\"\",\"roleCode\":\"\",\"password\":\"\",\"userType\":\"\"},{\"id\":\"111\",\"email\":\"aasf@sa.com1\",\"name\":\"名字1\",\"enable\":\"\",\"roleCode\":\"\",\"password\":\"\",\"userType\":\"\"},{\"id\":\"111\",\"email\":\"aasf@sa.com1\",\"name\":\"名字1\",\"enable\":\"\",\"roleCode\":\"\",\"password\":\"\",\"userType\":\"\"},{\"id\":\"111\",\"email\":\"aasf@sa.com1\",\"name\":\"名字1\",\"enable\":\"\",\"roleCode\":\"\",\"password\":\"\",\"userType\":\"\"},{\"id\":\"111\",\"email\":\"aasf@sa.com1\",\"name\":\"名字1\",\"enable\":\"\",\"roleCode\":\"\",\"password\":\"\",\"userType\":\"\"}]}"}
*/
private static final String $json(List objs , Pagination pagination) throws Exception{
	return JsonUtil.getJsonObject(objs,pagination);
}
%>