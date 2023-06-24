package com.sparta.memo.service;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import com.sparta.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


//@Component // 빈객체로 등록(생성자로 만들어서 주입해줌!), @Autowired 하려면 빈등록 필수!
//@RequiredArgsConstructor // 롬복으로 생성자 만들어줄 수 있음
@Service
public class MemoService { // 빈 등록이름: memoService
    private final MemoRepository memoRepository;

    @Autowired // 생성자 1개일 때는 생략가능
    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

//    생성자에 직접 수동 등록하는 방법
//    public MemoService(ApplicationContext context) {
//        // 1. 'Bean'이름으로 가져오기
//        MemoRepository memoRepository =  (MemoRepository) context.getBean("memoRepository");
//        // 2. 'Bean'클래스 형식으로 가져오기
//        MemoRepository memoRepository = context.getBean(MemoRepository.class);
//        this.memoRepository = memoRepository;
//    }



    public MemoResponseDto createMemo(MemoRequestDto requestDto) {
        // RequestDto -> Entity(데이터베이스 교환 객체)
        Memo memo = new Memo(requestDto);

        // DB 저장
        Memo saveMemo = memoRepository.save(memo); // 여기서 Entity로 Repository 에서 작업되고 다시 받아옴.

        // Entity -> ResponseDto
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);
        return memoResponseDto;
    }

    public List<MemoResponseDto> getMemos() {
        //DB 조회
        return memoRepository.findAll();
    }

    public Long updateMemo(Long id, MemoRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = memoRepository.findById(id);
        if (memo != null) {
            // memo 내용 수정
            memoRepository.update(id, requestDto);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    public Long deleteMemo(Long id) {

        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = memoRepository.findById(id);
        if (memo != null) {
            memoRepository.delete(id);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }





}
