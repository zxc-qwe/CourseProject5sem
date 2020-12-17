package DTO;

public class Branch {

    public String name;
    public String vvp_percent;
    public String companiesNum;

    public Branch (String name, String vvp_percent, String companiesNum) {
        this.name = name;
        this.vvp_percent = vvp_percent;
        this.companiesNum = companiesNum;
    }

    public String getName(){
        return name;
    }
    public String getVvp_percent(){
        return vvp_percent;
    }
    public String getCompaniesNum(){
        return companiesNum;
    }

    public Branch(){

    }

    public void setName(String name){
        this.name = name;
    }
    public void setVvp_percent(String vvp_percent){
        this.vvp_percent = vvp_percent;
    }
    public void setCompaniesNum(String companiesNum){
        this.companiesNum = companiesNum;
    }
}