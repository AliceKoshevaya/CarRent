package ua.nure.koshova.finalProject.service;
import java.util.List;
import ua.nure.koshova.finalProject.db.dao.ClassDao;
import ua.nure.koshova.finalProject.db.entity.ClassCar;

public class ClassService {
    private ClassDao classDao  = ClassDao.getInstance();

    public List<ClassCar> getClassList() {
        return classDao.findAllClasses();
    }
}
