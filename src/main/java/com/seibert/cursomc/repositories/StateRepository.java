package com.seibert.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seibert.cursomc.domain.City;
import com.seibert.cursomc.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer>{

}
