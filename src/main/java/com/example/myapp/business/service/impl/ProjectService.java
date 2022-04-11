package com.example.myapp.business.service.impl;

import com.example.myapp.business.service.IProjectService;
import com.example.myapp.persistence.model.Project;
import com.example.myapp.persistence.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
@AllArgsConstructor
@Service
public class ProjectService implements IProjectService {
    public final ProjectRepository projectRepository;

    @Override
    public List<Project> getListProjet() {
        try {
            return projectRepository.findAll();
        } catch (Exception e){
            throw new IllegalStateException("Error ProjetService in method getListProjet :: " + e.toString());

        }
    }

    @Override
    public Project getProjetById(Long id){
        try {
            if (id == null)
                return new Project();
            Project d = projectRepository.findProjectById(id);
            if (d == null)
                return new Project();
            return d;
        } catch (Exception e){
            throw new IllegalStateException("Error ProjetService in method getProjetById :: " + e.toString());
        }

    }


    @Override
    public Project addProjet(Project projet) {
        try {
            Project objNomUnique = projectRepository.findProjectByName(projet.getName());

            if ( objNomUnique != null)
                throw new IllegalStateException("Projet name token");

            projet.setCreationdate(new Timestamp(new Date().getTime()));

            return projectRepository.save(projet);
        } catch (Exception e) {
            throw new IllegalStateException("Error ProjetService in method addProjet :: " + e.toString());
        }
    }


    @Transactional
    @Override
    public Project updateProjet(Project project, Long id) {
        try {
            Project upproj = projectRepository.findProjectById(id);
            upproj.setName(project.getName());
            upproj.setStartdate(project.getStartdate());
            upproj.setEnddate(project.getEnddate());
            upproj.setDescription(project.getDescription());
            upproj.setPriority(project.getPriority());
            upproj.setUpdatedate(new Timestamp(new Date().getTime()));
            upproj.setId(id);

            return projectRepository.save(upproj);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error ProjetService in method updateProjet :: " + e.toString());
        }
    }

    @Transactional
    @Override
    public void deleteProjet(Long id) {
        try {
            projectRepository.deleteById(id);
        }
        catch (Exception e) {
            throw new IllegalStateException("Error ProjetService in method deleteProjet :: " + e.toString());
        }
    }



}
