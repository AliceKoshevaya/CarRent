package ua.nure.koshova.finalProject.db.dao;


import ua.nure.koshova.finalProject.db.entity.ClassCar;

import java.util.List;

public interface ClassDao {

    /**
     * Select class by id
     *
     * @param id
     *
     * @return {@link ClassCar}
     */
    ClassCar getClassById(Long id);

    /**
     * Select all classes
     *
     * @return {@link List<ClassCar>}
     */

    List<ClassCar> findAllClasses();

    /**
     * Select class by name
     *
     * @param name
     *
     * @return id class
     */

    Long getClassByName(String name);
}
