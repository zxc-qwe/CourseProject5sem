package DTO;

public class Branch {

    String name;
    String vvp_percent;
    String companiesNum;

    public Branch (String name, String vvp_percent, String companiesNum) {
        this.name = name;
        this.vvp_percent = vvp_percent;
        this.companiesNum = companiesNum;
    }

//    public Branch(){
//
//    }

    public String getNameBr(){
        return this.name;
    }
    public String getVvp_percent(){
        return this.vvp_percent;
    }
    public String getCompaniesNum(){
        return this.companiesNum;
    }
}
