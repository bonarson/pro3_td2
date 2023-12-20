package model;

public class CategorySumResult {
    private String categoryName;
    private float montantCategory1;
    private float montantCategory2;
    private float montantCategory3;

    public CategorySumResult(String categoryName, float montantCategory1, float montantCategory2, float montantCategory3) {
        this.categoryName = categoryName;
        this.montantCategory1 = montantCategory1;
        this.montantCategory2 = montantCategory2;
        this.montantCategory3 = montantCategory3;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public float getMontantCategory1() {
        return montantCategory1;
    }

    public void setMontantCategory1(float montantCategory1) {
        this.montantCategory1 = montantCategory1;
    }

    public float getMontantCategory2() {
        return montantCategory2;
    }

    public void setMontantCategory2(float montantCategory2) {
        this.montantCategory2 = montantCategory2;
    }

    public float getMontantCategory3() {
        return montantCategory3;
    }

    public void setMontantCategory3(float montantCategory3) {
        this.montantCategory3 = montantCategory3;
    }

}
