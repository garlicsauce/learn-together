package portfolio.garlicsauce.learntogether.factory;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface Factory<T, S> {

    T create(S source);

    default List<T> createList(Collection<S> sourceCollection) {
        return sourceCollection.stream()
                .map(this::create)
                .collect(Collectors.toList());
    }
}
