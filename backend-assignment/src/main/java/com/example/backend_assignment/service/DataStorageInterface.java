package com.example.backend_assignment.service;

import java.util.Optional;

public interface DataStorageInterface <E> {
	public Optional<E> retrieveStorageData(String codeName);
	public void storeStorageData(String codeName, E data);
}
