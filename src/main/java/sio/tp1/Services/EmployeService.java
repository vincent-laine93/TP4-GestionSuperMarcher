package sio.tp1.Services;

import org.springframework.stereotype.Service;
import sio.tp1.Entity.Employe;
import sio.tp1.Repository.EmployeRepository;

import java.util.List;

@Service
public class EmployeService {
    private EmployeRepository employeRepository;

    public EmployeService(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    public List<Employe> getAllEmploye(){
        return employeRepository.findAll();
    }
}
