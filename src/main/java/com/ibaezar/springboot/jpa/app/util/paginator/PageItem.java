package com.ibaezar.springboot.jpa.app.util.paginator;

public class PageItem {
    
    private int number;
    private boolean actualPage;

    public PageItem(int number, boolean actualPage) {
        this.number = number;
        this.actualPage = actualPage;
    }
    public int getNumber() {
        return number;
    }
    public boolean isActualPage() {
        return actualPage;
    }
}
