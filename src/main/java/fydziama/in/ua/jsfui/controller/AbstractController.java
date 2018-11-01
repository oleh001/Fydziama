package fydziama.in.ua.jsfui.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.io.Serializable;


public abstract class AbstractController<T> extends PagerController<T> implements Serializable{

    public static final int DEFAULT_PAGE_SIZE = 6;
    public int rowsCount = DEFAULT_PAGE_SIZE;

    public int countPages=1;

    public String visiblePaginator;

    public abstract boolean vizibilityAction();

    // search all
    public abstract Page<T> search(int first, int count, String sortField, Sort.Direction sortDirection);

    // main methods (CRUD)
    public abstract void addAction();
    public abstract void editAction();
    public abstract void deleteAction();

}
