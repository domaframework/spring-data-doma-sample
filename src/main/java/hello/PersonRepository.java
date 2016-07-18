package hello;

import java.util.List;

import org.seasar.doma.AnnotateWith;
import org.seasar.doma.Annotation;
import org.seasar.doma.AnnotationTarget;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@Dao
@AnnotateWith(annotations = { @Annotation(target = AnnotationTarget.CONSTRUCTOR, type = Autowired.class) })
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
@RepositoryDefinition(domainClass = Person.class, idClass = Long.class)
public interface PersonRepository {

    @Select
    List<Person> findByLastName(@Param("name") String name);

    @Select
    Person findOne(Long id);

    @Select
    List<Person> findAll();

    @Delete
    int delete(Person person);

}
