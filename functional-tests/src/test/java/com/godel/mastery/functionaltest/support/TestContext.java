package com.godel.mastery.functionaltest.support;

import org.joda.time.DateTime;

import java.util.*;

public class TestContext<Key> {
    private Map<Key, Object> state;

    public TestContext() {
        state = new HashMap<>();
    }

    public void add(final Key key, final Object persist) {
        this.state.put(key, persist);
    }

    public void addToList(final Key control, final Object persist) {
        List list = (List) this.state.get(control);
        if (list == null) {
            list = new ArrayList();
            this.state.put(control, list);
        }
        list.add(persist);
    }

    public Object get(final Key control) {
        return this.state.get(control);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(final Key control, final Class<T> clazz) {
        return (T) this.state.get(control);
    }

    public String getAsString(final Key control) {
        return get(control, String.class);
    }

    public boolean getBoolean(final Key control) {
        return (Boolean) get(control);
    }

    public Integer getInteger(final Key control) {
        return get(control, Integer.class);
    }

    public Date getDate(final Key control) {
        return get(control, Date.class);
    }

    public DateTime getDateTime(final Key control){
        return get(control, DateTime.class);
    }

    public Calendar getCalendar(final Key control){
        return get(control, Calendar.class);
    }

    public <T> List<T> getAsList(final Key control){
        return (List<T>) get(control);
    }

    public void clearExcept(final Key control){
        Object retained = this.state.get(control);
        clear();
        this.state.put(control, retained);
    }

    public void clear() {
        this.state =  new HashMap<>();
    }

    public Boolean containsValue(final Object value){
        return this.state.containsValue(value);
    }

    public Boolean containsKey(final Key control){
        return this.state.containsKey(control);
    }

    @Override
    public String toString() {
        return this.state.toString();
    }
}
