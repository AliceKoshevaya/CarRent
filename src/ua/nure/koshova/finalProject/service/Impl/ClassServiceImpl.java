package ua.nure.koshova.finalProject.service.Impl;
import java.util.List;
import ua.nure.koshova.finalProject.db.dao.impl.ClassDaoImpl;
import ua.nure.koshova.finalProject.db.entity.ClassCar;
import ua.nure.koshova.finalProject.service.ClassService;

public class ClassServiceImpl implements ClassService{
    private ClassDaoImpl classDaoImpl = ClassDaoImpl.getInstance();

    public List<ClassCar> getClassList() {
        return classDaoImpl.findAllClasses();
    }

    public ClassCar getClassById(Long id){
        return classDaoImpl.getClassById(id);
    }
    public Long getClassByName(String name){
        return classDaoImpl.getClassByName(name);
    }
}
