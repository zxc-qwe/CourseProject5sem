package DTO;

public class Company {
    String companyName;
    String ownership;
    String workersNum;
    String market_percent;
    String branch;

    public static String comp_name;

    public Company(String companyName, String ownership, String workersNum, String market_percent, String branch){
        this.companyName = companyName;
        this.ownership = ownership;
        this.workersNum = workersNum;
        this.market_percent = market_percent;
        this.branch = branch;
    }

//    public Company(){
//
//    }

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
}
