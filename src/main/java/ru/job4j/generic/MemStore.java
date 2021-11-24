package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> mem = new HashMap<>();

    @Override
    public void add(T model) {
        mem.put(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean rsl = mem.containsKey(id);
        if (rsl) {
            mem.remove(id);
            mem.put(model.getId(), model);
        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        boolean rsl = mem.containsKey(id);
        if (rsl) {
            mem.remove(id);
        }
        return false;
    }

    @Override
    public T findById(String id) {
        return mem.get(id);
    }
}
