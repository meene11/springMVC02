package kr.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@Controller // anotation 으로 역할 규정
public class BoardController {
	
	@Autowired
	BoardMapper boardMapper;
	
	@RequestMapping("/")
	public String main() {
		return "main";
	}
	
	//  @ResponseBody ->jsckson-databind(객체를->JSON 데이터포맷으로 변환)
	@RequestMapping("/boardList.do")
	public @ResponseBody List<Board> boardList() {
		List<Board> list = boardMapper.getLists();
		// 객체를 json 데이터 형식으로 변환해서 리턴(응답)하겟다 : @ResponseBody ->api 가 동작해야한다.: jackson-databind API
		return list; // return 값이 jsp파일명도, 리다이렉트도 아닌, 객체를 리턴
	}
	
	@RequestMapping("/boardInsert.do")
	public @ResponseBody void boardInsert(Board vo) {
		boardMapper.boardInsert(vo); // 등록성공
	}
	
	@RequestMapping("/boardDelete.do")
	public @ResponseBody void boardDelete(@RequestParam("idx") int idx) {
		boardMapper.boardDelete(idx);
	}
	
	@RequestMapping("/boardUpdate.do")
	public @ResponseBody void boardUpdate(Board vo){
		boardMapper.boardUpdate(vo);
	}
	
	@RequestMapping("/boardContent.do")
	public @ResponseBody Board boardContnt(int idx) {
		Board vo = boardMapper.boardContent(idx);
		return vo; // vo->JSON
	}
	
	@RequestMapping("boardCount.do")
	public @ResponseBody Board boardCount(int idx) {
		boardMapper.boardCount(idx);
		Board vo = boardMapper.boardContent(idx);
		return vo;
	}
	
	
}
