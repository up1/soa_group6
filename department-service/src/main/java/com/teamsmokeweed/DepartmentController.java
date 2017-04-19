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

    @GetMapping("/dep/{dep_id}")
    public GetDepNameResponse GetDepName(@PathVariable (value = "dep_id") String dep_id){
        return this.departmentRepository.GetDepName(Integer.parseInt(dep_id));
    }

    @GetMapping("/dep")
    public List<GetAllDepResponse> GetAllDep(){
        return this.departmentRepository.GetAllDep();
    }
}
