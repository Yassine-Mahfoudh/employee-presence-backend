package com.example.myapp.business.service;

import com.example.myapp.persistence.model.Employee;
import com.example.myapp.persistence.model.Utilisateur;

import java.util.List;

public interface IUtilisateurService {
    public List<Utilisateur> getListUtilisateur();
    public Utilisateur addUtilisateur(Utilisateur obj);
    public void deleteUtilisateur(Long id);
    public Utilisateur updateUtilisateur(Utilisateur utilisateur,Long id);
    public Utilisateur getUtilisateurById(Long id);

    Utilisateur getUtilisateurByuserName(String userName);

    Employee getEmpByuserName(String userName);

    public  void initRoleAndUser();
}
