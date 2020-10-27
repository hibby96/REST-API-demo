package com.revature.Database;

import com.revature.Objects.Person;

public interface DAO {
    public Person getPerson(int id);
    public boolean addPerson(Person person);
    public boolean deletePerson(int id);
    public boolean updatePerson(Person person, int id);
    
}
