package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/api")
@RestController // html을 따로 반환하지 않고 데이터만 던져주기 때문에
public class MemoController {

    //아직 데이터베이스 연결 전이어서 Map 이용
    private final Map<Long, Memo> memoList = new HashMap<>();
    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
        // RequestDto -> Entity(데이터베이스 교환 객체)
        Memo memo = new Memo(requestDto);

        // Memo Max ID Check
        Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet()) + 1 : 1;
        memo.setId(maxId);

        // DB 저장
        memoList.put(memo.getId(), memo);

        // Entity -> ResponseDto
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

        return memoResponseDto;
    }

    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos() {
        // Map To List
        List<MemoResponseDto> responseList = memoList.values().stream() // 하나씩 객체 튀어나옴
                .map(MemoResponseDto::new).toList(); // Memo 들을 MemoResponseDto에 생성자로 넣어서 리스트로 만듬.
        return responseList;
    }




}
