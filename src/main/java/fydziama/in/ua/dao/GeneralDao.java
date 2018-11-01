package fydziama.in.ua.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface GeneralDao<T> {

    boolean isVisibility(List<T> obj, int count);

    boolean isVisibility(Page<T> obj, int count);

    // получение всех записей (без постраничности)
    List<T> getAll();

    // поиск записей с любым количествомм параметров
    List<T> search(String... searchString);

    // получение объекта по id
    T get(long id);

    // save - обновляет или добавляет объект (один метод на 2 действия)
    T save(T obj);

    // удаление объекта
    void delete(T object);

    // получение всех записей с сортировкой результата
    List<T> getAll(Sort sort);

    // получение всех записей с постраничностью
    Page<T> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection);

    // поиск записей с постраничностью
    Page<T> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString);

}
