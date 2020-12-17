package DTO;

public class Company {
    String companyName;
    String ownership;
    String workersNum;
    String market_percent;
    String branch;

    public Company(String companyName, String ownership, String workersNum, String market_percent, String branch){
        this.companyName = companyName;
        this.ownership = ownership;
        this.workersNum = workersNum;
        this.market_percent = market_percent;
        this.branch = branch;
    }

    public String getCompanyName(){
        return companyName;
    }
    public String getOwnership(){
        return ownership;
    }
    public String getWorkersNum(){
        return workersNum;
    }
    public String getMarket_percent(){
        return market_percent;
    }
    public String getBranch(){
        return branch;
    }

    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    public void setOwnership(String ownership){
        this.ownership = ownership;
    }
    public void setWorkersNum(String workersNum){
        this.workersNum = workersNum;
    }
    public void setMarket_percent(String market_percent){
        this.market_percent = market_percent;
    }
    public void setBranch(String branch){
        this.branch = branch;
    }

    public Company(String companyName){
        this.companyName = companyName;
    }

    public Company(){

    }
}
