package com.example.backend_assignment.service;

public interface DataStorageInterface <E> {
	public E retrieveStorageData(String codeName);
	public void storeStorageData(String codeName, E data);
}
