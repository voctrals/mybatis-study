package voctrals.study.mybatis.mapper;

import voctrals.study.mybatis.entity.Person;

import java.util.List;

public interface PersonMapper {
    List<Person> getAll();
    int insert(Person person);
}
