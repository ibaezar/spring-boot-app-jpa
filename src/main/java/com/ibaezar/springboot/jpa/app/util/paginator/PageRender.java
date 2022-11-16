package com.ibaezar.springboot.jpa.app.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageRender<T> {
    
    private String url;
    private Page<T> page;
    private int totalPages;
    private int numberOfItemsPerPage;
    private int actualPage;
    private List<PageItem> pages;

    public PageRender(String url, Page<T> page){
        this.url = url;
        this.page = page;
        this.pages = new ArrayList<PageItem>();

        numberOfItemsPerPage = page.getSize();
        totalPages = page.getTotalPages();
        actualPage = page.getNumber() + 1;

        int from, to;
        if(totalPages <= numberOfItemsPerPage){
            from = 1;
            to = totalPages;
        }else{
            if (actualPage <= numberOfItemsPerPage / 2) {
                from = 1;
                to = numberOfItemsPerPage;
            }else if (actualPage >= totalPages - numberOfItemsPerPage / 2) {
                from = totalPages - numberOfItemsPerPage + 1;
                to = numberOfItemsPerPage;
            } else {
                from = actualPage - numberOfItemsPerPage / 2;
                to = numberOfItemsPerPage;
            }
        }

        for (int i = 0; i < to; i++) {
            pages.add(new PageItem(from + i, actualPage == from + i));
        }
    }

    public String getUrl() {
        return url;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getActualPage() {
        return actualPage;
    }

    public List<PageItem> getPages() {
        return pages;
    }

    public boolean isFirst(){
        return page.isFirst();
    }

    public boolean isLast(){
        return page.isLast();
    }

    public boolean isHasNext(){
        return page.hasNext();
    }

    public boolean isHasPrevious(){
        return page.hasPrevious();
    }
    
}