package common;

import java.io.Serializable;

public class Student
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  String sId;
  String sName;
  String sSex;
  String sAge;
  String sBirthdate;
  String sDormitory;
  String sPlace;//����
  String sDepart;

  public String getsName()
  {
    return this.sName;
  }

  public void setsName(String sName)
  {
    this.sName = sName;
  }

  public String getsId()
  {
    return this.sId;
  }

  public void setsId(String sId)
  {
    this.sId = sId;
  }

  public String getsAge()
  {
    return this.sAge;
  }

  public void setsAge(String sAge)
  {
    this.sAge = sAge;
  }

  public String getsSex()
  {
    return this.sSex;
  }

  public void setsSex(String sSex)
  {
    this.sSex = sSex;
  }


  public String getsPlace()
  {
    return this.sPlace;
  }

  public void setsPlace(String sPlace)
  {
    this.sPlace = sPlace;
  }

  public String getsDepart()
  {
    return this.sDepart;
  }

  public void setsDepart(String sDepart)
  {
    this.sDepart = sDepart;
  }
  
  public String getsDormitory(){
	  return this.sDormitory;
  }
  
  public void setsDormitory(String sDormitory){
	  this.sDormitory = sDormitory;
  }
  
  public String getsBirthdate(){
	  return this.sBirthdate;
  }
  
  public void setsBirthdate(String sBirthdate){
	  this.sBirthdate = sBirthdate;
  }
 
  
}