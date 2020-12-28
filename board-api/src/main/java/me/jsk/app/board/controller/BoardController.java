package me.jsk.app.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import me.jsk.app.board.domain.BoardVO;
import me.jsk.app.board.service.BoardService;

@CrossOrigin
@Controller
@EnableAutoConfiguration
@RequestMapping("/board")
public class BoardController {

  @Autowired
  private BoardService boardService;
  
  @ResponseBody
  @GetMapping(value="/list")
  public List<BoardVO> selectBoardList(HttpServletRequest request, HttpServletResponse response) throws Exception {

    BoardVO vo = new BoardVO();

    // TODO 검색조건 처리 필요

    List<BoardVO> result = boardService.selectBoardList(vo);

    return result;
  }

  @ResponseBody
  @GetMapping(value = "/detail")
  public BoardVO selectBoardDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    String schDocNo = request.getParameter("schDocNo");

    BoardVO vo = new BoardVO();
    vo.setSchDocNo(Integer.parseInt(schDocNo));

    // 조회수 증가
    boardService.increaseViewCount(vo);

    BoardVO result = boardService.selectBoardDetail(vo);

    return result;
  }

  @ResponseBody
  @PostMapping(value = "/insert")
  public int insertBoard(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String title = request.getParameter("title");
    String content = request.getParameter("content");

    BoardVO vo = new BoardVO();
    vo.setTitle(title);
    vo.setContent(content);
    // TODO 로그인 사용자로 작성자 받기
    vo.setWriter("Test Writer");

    int result = 0; // 신규 게시글 번호
    
    int rows = boardService.insertBoard(vo);

    if(rows > 0) {
      result = vo.getDocNo();
    }

    return result;
  }

  @ResponseBody
  @PostMapping(value = "/update")
  public int updateBoard(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String docNo = request.getParameter("docNo");
    String title = request.getParameter("title");
    String content = request.getParameter("content");

    BoardVO vo = new BoardVO();
    vo.setDocNo(Integer.parseInt(docNo));
    vo.setTitle(title);
    vo.setContent(content);

    int rows = boardService.updateBoard(vo);

    return rows;
  }

  @ResponseBody
  @PostMapping(value = "/delete")
  public int deleteBoard(HttpServletRequest request, HttpServletResponse response) throws Exception{
    String docNo = request.getParameter("docNo");

    BoardVO vo = new BoardVO();
    vo.setDocNo(Integer.parseInt(docNo));

    int rows = boardService.deleteBoard(vo);

    return rows;
  }
  
}
