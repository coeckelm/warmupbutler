package net.ing.oc.fs1.warmupbutler.repository;

import net.ing.oc.fs1.warmupbutler.domain.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {}

