package DTO;

public class CompanyDiagr {
    public String branchName;
    public int stable;
    public int all;

    public CompanyDiagr(String branchName, int stable, int all) {
        this.branchName = branchName;
        this.stable = stable;
        this.all = all;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public int getStable() {
        return stable;
    }

    public int getAll() {
        return all;
    }
}