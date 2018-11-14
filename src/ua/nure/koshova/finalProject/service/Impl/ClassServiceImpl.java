package ua.nure.koshova.finalProject.service.Impl;
import java.util.List;

import ua.nure.koshova.finalProject.db.dao.ClassDao;
import ua.nure.koshova.finalProject.db.dao.impl.ClassDaoImpl;
import ua.nure.koshova.finalProject.db.entity.ClassCar;
import ua.nure.koshova.finalProject.service.ClassService;

public class ClassServiceImpl implements ClassService{
    private ClassDao classDao = ClassDaoImpl.getInstance();

    public List<ClassCar> getClassList() {
        return classDao.findAllClasses();
    }

    public ClassCar getClassById(Long id){
        return classDao.getClassById(id);
    }
    public Long getClassByName(String name){
        return classDao.getClassByName(name);
    }
}
