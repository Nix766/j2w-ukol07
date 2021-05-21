package cz.czechitas.java2webapps.ukol7.reporitory;

import cz.czechitas.java2webapps.ukol7.entity.Vizitka;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VizitkaRepository extends CrudRepository<Vizitka, Long> {
}
