package com.sparta.memo.repository;

import com.sparta.memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Component // 빈객체로 등록(생성자로 만들어서 주입해줌!, 옆에 콩모양 생김)
//@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findAllByOrderByModifiedAtDesc(); // 메서드 이름으로 sql 가능!
//    List<Memo> findAllByUsername(String username); // 특정 username인 것만 찾기
    List<Memo> findAllByContentsContainsOrderByModifiedAtDesc(String keyword); // 특정 키워드 포함 조회, 수정시간 내림차순
}
