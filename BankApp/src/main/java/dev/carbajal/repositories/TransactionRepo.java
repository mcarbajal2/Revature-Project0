package dev.carbajal.repositories;

import java.sql.Connection;
import java.util.List;

import dev.carbajal.utils.JDBCConnection;

public class TransactionRepo implements GenericRepo<TransactionRepo> {
	
	private Connection conn = JDBCConnection.getConnection();

	@Override
	public TransactionRepo add(TransactionRepo t) {
		
		return null;
	}

	@Override
	public TransactionRepo getById(Integer id) {
		
		return null;
	}

	@Override
	public List<TransactionRepo> getAll() {
		
		return null;
	}

	@Override
	public boolean update(TransactionRepo change) {
		
		return false;
	}

	@Override
	public boolean delete(TransactionRepo t) {
		
		return false;
	}

}
