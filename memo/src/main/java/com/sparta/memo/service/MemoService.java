package com.sparta.memo.service;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import com.sparta.memo.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return memoRepository.findAllByOrderByModifiedAtDesc().stream().map(MemoResponseDto::new).toList();
    }

    public List<MemoResponseDto> getMemosByKeyword(String keyword) {
        //DB 조회
        return memoRepository.findAllByContentsContainsOrderByModifiedAtDesc(keyword).stream().map(MemoResponseDto::new).toList();
    }

    @Transactional // 변경감지를 적용하기위해! 다른 save, delete 등은 이미 메서드에 @Transactional이 한번 더 적용되어 있다.(트랜젝션 전파)
    public Long updateMemo(Long id, MemoRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = findMemo(id);
        // memo 내용 수정(영속성 컨텍스트의 변경감지를 통해, 즉, requestDto에 들어온 객체로 memo 객체(entity)를 업데이트 시킴)
        memo.update(requestDto);

        return id;
    }

    public Long deleteMemo(Long id) {

        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = findMemo(id);

        // memo 삭제
        memoRepository.delete(memo);
        return id;
    }

    private Memo findMemo(Long id) {
        return memoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다.")
        );
    }

}
