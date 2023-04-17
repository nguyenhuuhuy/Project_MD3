package project.controller;

import project.model.oderList.Oder;
import project.service.oderService.OderServiceIMPL;

import java.util.List;

public class OderController {
    OderServiceIMPL oderServiceIMPL = new OderServiceIMPL();

    public List<Oder> showListOder() {
        return oderServiceIMPL.findAll();
    }

    public void saveListOder(Oder oder) {
        oderServiceIMPL.save(oder);
    }

    public Oder detailOder(int id) {
        return oderServiceIMPL.findById(id);
    }

    public void updateOder(Oder oder){
        oderServiceIMPL.save(oder);
    }
}
