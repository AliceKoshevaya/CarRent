package ua.nure.koshova.finalProject.service.Impl;

import ua.nure.koshova.finalProject.db.dao.impl.BrandDaoImpl;
import ua.nure.koshova.finalProject.db.entity.Brand;
import ua.nure.koshova.finalProject.service.BrandService;

import java.util.List;

public class BrandServiceImpl implements BrandService{

    private BrandDaoImpl brandDaoImpl = BrandDaoImpl.getInstance();

    public List<Brand> getBrandList() {
        return brandDaoImpl.findAllBrands();
    }

    public Brand getBrandById(Long id){
        return brandDaoImpl.getBrandById(id);
    }
    public Long getBrandByName(String name){
        return brandDaoImpl.getBrandByName(name);
    }
}
