package org.doubleaaexpress.models.dao.iterator;

import java.util.List;

import org.doubleaaexpress.models.Administrator;

public class AdministratorIterator implements Iterator<Administrator> {

    private List<Administrator> administrators;
    private int index;

    public AdministratorIterator(List<Administrator> administrators) {
        this.administrators = administrators;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < administrators.size();
    }

    @Override
    public Administrator next() {
        return administrators.get(index++);
    }   

    @Override
    public void remove() {
        administrators.remove(index - 1);
    }

}
