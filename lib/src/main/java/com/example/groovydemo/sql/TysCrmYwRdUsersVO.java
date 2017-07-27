package com.ddoctor.server.vo.crm;
 /**
 * <p>Title: Value code</p>
 * <p>Description: data view</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: ddoctor</p>
 * @author xiao
 * @version 1.0
 */ 
public class TysCrmYwRdUsersVO implements java.io.Serializable {
    /**
     * 定义本类的私有变量
     */
    private Integer id;

    private String name;

    private String email;

    private String password;

    private String rememberToken;

    private Integer store;

    private String level;

    private Integer sex;

    private String phone;

    private java.util.Date createdAt;

    private java.util.Date updatedAt;

    private Integer isDel;

  public TysCrmYwRdUsersVO(){
  }
public TysCrmYwRdUsersVO(Integer id, String name, String email, String password, String rememberToken, Integer store, String level, Integer sex, String phone, java.util.Date createdAt, java.util.Date updatedAt, Integer isDel)
{
this.id = id;
this.name = name;
this.email = email;
this.password = password;
this.rememberToken = rememberToken;
this.store = store;
this.level = level;
this.sex = sex;
this.phone = phone;
this.createdAt = createdAt;
this.updatedAt = updatedAt;
this.isDel = isDel;
}
/**
*
*id set 方法
*/
public void setId(Integer id){
this.id = id;
}
/**
*
*id get 方法
*/
public Integer getId(){
return id;
}
/**
*
*name set 方法
*/
public void setName(String name){
this.name = name;
}
/**
*
*name get 方法
*/
public String getName(){
return name;
}
/**
*
*email set 方法
*/
public void setEmail(String email){
this.email = email;
}
/**
*
*email get 方法
*/
public String getEmail(){
return email;
}
/**
*
*password set 方法
*/
public void setPassword(String password){
this.password = password;
}
/**
*
*password get 方法
*/
public String getPassword(){
return password;
}
/**
*
*rememberToken set 方法
*/
public void setRememberToken(String rememberToken){
this.rememberToken = rememberToken;
}
/**
*
*rememberToken get 方法
*/
public String getRememberToken(){
return rememberToken;
}
/**
*
*store set 方法
*/
public void setStore(Integer store){
this.store = store;
}
/**
*
*store get 方法
*/
public Integer getStore(){
return store;
}
/**
*
*level set 方法
*/
public void setLevel(String level){
this.level = level;
}
/**
*
*level get 方法
*/
public String getLevel(){
return level;
}
/**
*
*sex set 方法
*/
public void setSex(Integer sex){
this.sex = sex;
}
/**
*
*sex get 方法
*/
public Integer getSex(){
return sex;
}
/**
*
*phone set 方法
*/
public void setPhone(String phone){
this.phone = phone;
}
/**
*
*phone get 方法
*/
public String getPhone(){
return phone;
}
/**
*
*createdAt set 方法
*/
public void setCreatedAt(java.util.Date createdAt){
this.createdAt = createdAt;
}
/**
*
*createdAt get 方法
*/
public java.util.Date getCreatedAt(){
return createdAt;
}
/**
*
*updatedAt set 方法
*/
public void setUpdatedAt(java.util.Date updatedAt){
this.updatedAt = updatedAt;
}
/**
*
*updatedAt get 方法
*/
public java.util.Date getUpdatedAt(){
return updatedAt;
}
/**
*
*isDel set 方法
*/
public void setIsDel(Integer isDel){
this.isDel = isDel;
}
/**
*
*isDel get 方法
*/
public Integer getIsDel(){
return isDel;
}
}