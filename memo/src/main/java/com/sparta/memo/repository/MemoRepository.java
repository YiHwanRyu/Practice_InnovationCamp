package com.sparta.memo.repository;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

//@Component // 빈객체로 등록(생성자로 만들어서 주입해줌!, 옆에 콩모양 생김)
@Repository
public class MemoRepository { // 빈 등록이름: memoRepository
    private final JdbcTemplate jdbcTemplate;
    @Autowired // 생성자 1개 일때는 생략가능
    public MemoRepository(JdbcTemplate jdbcTemplate) { // jdbcTemplates는 이미 자동으로 빈에서 관리되고 있음!!
        this.jdbcTemplate = jdbcTemplate;
    }

    public Memo save(Memo memo) {
        //DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO memo (username, contents) VALUES (?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, memo.getUsername());
            preparedStatement.setString(2, memo.getContents());
            return preparedStatement;
        }, keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        memo.setId(id);
        return memo;
    }

    public List<MemoResponseDto> findAll() {
        String sql = "SELECT * FROM memo";
        return jdbcTemplate.query(sql, new RowMapper<MemoResponseDto>() {

            @Override
            public MemoResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
                Long id = rs.getLong("id");
                String username = rs.getString("username");
                String contents = rs.getString("contents");
                return new MemoResponseDto(id, username, contents);
            }
        });
    }


    public void update(Long id, MemoRequestDto requestDto) {
        String sql = "UPDATE memo SET username = ?, contents = ? WHERE id = ?";
        jdbcTemplate.update(sql, requestDto.getUsername(), requestDto.getContents(), id);
    }

    public void delete(Long id) {
        // memo 삭제
        String sql = "DELETE FROM memo WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Memo findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM memo WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                Memo memo = new Memo();
                memo.setUsername(resultSet.getString("username"));
                memo.setContents(resultSet.getString("contents"));
                return memo;
            } else {
                return null;
            }
        }, id);
    }

    @Transactional //(propagation = Propagation.REQUIRED) 부모메서드에 트랜젝션 존재하면 자식매서드의 트랜젝션을 합류시켜 한번에 함.
    public Memo createMemo(EntityManager em) {
        Memo memo = em.find(Memo.class, 1);
        memo.setUsername("Robbert");
        memo.setContents("@Transactional 전파 테스트 중! 2");

        System.out.println("createMemo 메서드 종료");
        return memo;
    }
}
