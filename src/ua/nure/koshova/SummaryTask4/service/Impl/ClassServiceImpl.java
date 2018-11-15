package ua.nure.koshova.SummaryTask4.service.Impl;
import java.util.List;

import ua.nure.koshova.SummaryTask4.db.dao.ClassDao;
import ua.nure.koshova.SummaryTask4.db.dao.impl.ClassDaoImpl;
import ua.nure.koshova.SummaryTask4.db.entity.ClassCar;
import ua.nure.koshova.SummaryTask4.service.ClassService;

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
