package ua.nure.koshova.finalProject.service;

import ua.nure.koshova.finalProject.db.entity.ClassCar;

import java.util.List;

/**
 * Interface for common class car actions
 */
public interface ClassService {

    /**
     * Get all class car
     *
     * @return {@Link List<ClassCar>}
     */
    public List<ClassCar> getClassList();

    /**
     * Get class car by id
     *
     * @param id
     * @return {@Link ClassCar}
     */
    public ClassCar getClassById(Long id);

    /**
     * Get class car by name
     *
     * @param name
     * @return id caar class
     */
    public Long getClassByName(String name);
}
