package com.teamsmokeweed;

import com.teamsmokeweed.model.getdepname.getalldep.GetAllDepResponse;
import com.teamsmokeweed.model.getdepname.getdepname.GetDepNameResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Armerino on 14/4/2560.
 */
@CrossOrigin
@Controller
@RestController
public class DepartmentController {
    private final DepartmentRepository departmentRepository;

    public DepartmentController(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


//    id->departmentID
    @GetMapping("/departments/{id}")
    public GetDepNameResponse getDepName(@PathVariable (value = "id") String dep_id){
        return this.departmentRepository.getDepName(Integer.parseInt(dep_id));
    }

    @GetMapping("/departments")
    public List<GetAllDepResponse> getAllDep(){
        return this.departmentRepository.getAllDep();
    }
}
