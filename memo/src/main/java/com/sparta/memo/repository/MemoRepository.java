package com.sparta.memo.repository;

import com.sparta.memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Component // 빈객체로 등록(생성자로 만들어서 주입해줌!, 옆에 콩모양 생김)
//@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {

}
