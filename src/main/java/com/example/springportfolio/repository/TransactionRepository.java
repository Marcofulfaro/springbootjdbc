package com.example.springportfolio.repository;

import com.example.springportfolio.model.Transaction;
import com.example.springportfolio.model.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class TransactionRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Transaction> getAllTransactions() {
        String sql = "SELECT * FROM transactions";
        return jdbcTemplate.query(sql, new TransactionRowMapper());
    }

    public void addTransaction(Transaction transaction) {
        String sql = "INSERT INTO transactions (type, amount, description, date) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, preparedStatement -> {
            preparedStatement.setObject(1, transaction.getType(), Types.OTHER);
            preparedStatement.setBigDecimal(2, transaction.getAmount());
            preparedStatement.setString(3, transaction.getDescription());
            preparedStatement.setDate(4, Date.valueOf(transaction.getDate()));
        });
    }

    public void deleteTransaction(Long id) {
        String sql = "DELETE FROM transactions WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class TransactionRowMapper implements RowMapper<Transaction> {
        @Override
        public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
            Transaction transaction = new Transaction();
            transaction.setId(rs.getLong("id"));
            transaction.setType(TransactionType.valueOf(rs.getString("type")));
            transaction.setAmount(rs.getBigDecimal("amount"));
            transaction.setDescription(rs.getString("description"));
            transaction.setDate(rs.getDate("date").toLocalDate());
            return transaction;
        }
    }
}
