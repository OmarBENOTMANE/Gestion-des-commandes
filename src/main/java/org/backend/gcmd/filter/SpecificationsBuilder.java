package org.backend.gcmd.filter;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SpecificationsBuilder<T> {

    private final List<SearchCriteria> params;

    public SpecificationsBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public SpecificationsBuilder<T> with(String key, String operation, Object value) {
        if (value != null && value instanceof String && !value.toString().isEmpty())
            params.add(new SearchCriteria(key, operation, value));
        else if (value != null)
            params.add(new SearchCriteria(key, operation, value));
        return this;
    }


    public Specification<T> build() {
        if (!params.isEmpty()) {
            Specification<T> filter = Specification.where(new GenericsSpec<T>(params.get(0)));
            for (SearchCriteria criteria : params) {
                filter = filter.and(new GenericsSpec<T>(criteria));
            }

            return filter;
        }

        return null;
    }
}